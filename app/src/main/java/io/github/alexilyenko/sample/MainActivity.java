package io.github.alexilyenko.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import io.github.alexilyenko.sample.databinding.ActivityMainBinding;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    private enum Figure {
        ADD, SUB, MULTI, DIV, NONE;

        private BigDecimal calc(BigDecimal arg1, BigDecimal arg2) {
            switch (this) {
                case ADD:
                    return arg1.add(arg2);
                case SUB:
                    return arg1.subtract(arg2);
                case MULTI:
                    return arg1.multiply(arg2);
                case DIV:
                    return arg1.divide(arg2, 8, BigDecimal.ROUND_HALF_UP);
                default:
                    return arg2;
            }
        }
    }

    private static final DecimalFormat FORMATTER = new DecimalFormat("#,###.#") {{
        setMinimumFractionDigits(0);
        setMaximumFractionDigits(8);
    }};

    private BigDecimal field = BigDecimal.ZERO;
    private BigDecimal stack = BigDecimal.ZERO;
    private Figure currentFigure = Figure.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Button[] numbers = new Button[]{
                binding.button0, binding.button1, binding.button2,
                binding.button3, binding.button4, binding.button5,
                binding.button6, binding.button7, binding.button8, binding.button9
        };
        for (final Button button : numbers) {
            RxView.clicks(button).subscribe(new Consumer<Object>() {
                @Override
                public void accept(@NonNull Object o) throws Exception {
                    field = field.multiply(new BigDecimal(10))
                            .add(new BigDecimal(Integer.parseInt(button.getTag().toString())));
                    binding.field.setText(FORMATTER.format(field));
                }
            });
        }

        Button[] symbols = new Button[]{
                binding.buttonAllClear,
                binding.buttonAdd, binding.buttonSub,
                binding.buttonMulti, binding.buttonDivide
        };
        for (final Button button1 : symbols) {
            RxView.clicks(button1).subscribe(new Consumer<Object>() {
                @Override
                public void accept(@NonNull Object o) throws Exception {
                    currentFigure = Figure.valueOf(button1.getTag().toString());
                    stack = (currentFigure != Figure.NONE) ? field : BigDecimal.ZERO;
                    field = BigDecimal.ZERO;
                    if (stack.equals(BigDecimal.ZERO)) {
                        binding.field.setText(FORMATTER.format(field));
                    }
                }
            });
        }

        RxView.clicks(binding.buttonCalc).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                field = currentFigure.calc(stack, field);
                binding.field.setText(FORMATTER.format(field));
            }
        });
    }
}