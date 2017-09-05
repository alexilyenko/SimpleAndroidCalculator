package io.github.alexilyenko.sample.pageobject.screens

import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice


class CalculatorScreen(device: UiDevice) : BaseScreen(device) {

    private val plusButton = By.res("$id/button_add")
    private val multiplyButton = By.res("$id/button_multi")
    private val equalButton = By.res("$id/button_calc")
    private val resetButton = By.res("$id/button_all_clear")
    private val screen = By.res("$id/field")

    private val numberPattern = "$id/button_%s"
    private val delimiterRegex = Regex("[.,]")

    val numberOnScreen: Int
        get() = find(screen).text
                .replace(delimiterRegex, "")
                .toInt()

    fun enter(number: Int): CalculatorScreen {
        inputNumber(number)
        return this
    }

    fun plus(number: Int): CalculatorScreen {
        click(plusButton)
        inputNumber(number)
        click(equalButton)
        return this
    }

    fun multiply(by: Int): CalculatorScreen {
        click(multiplyButton)
        inputNumber(by)
        click(equalButton)
        return this
    }

    private fun inputNumber(number: Int) = number
            .toString()
            .toCharArray()
            .forEach {
                click(By.res(String.format(numberPattern, it)))
            }

    fun reset(): CalculatorScreen {
        click(resetButton)
        return this
    }
}
