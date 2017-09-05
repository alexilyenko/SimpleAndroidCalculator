package io.github.alexilyenko.sample.pageobject.screens

import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice


class CalculatorScreen(device: UiDevice) : BaseScreen(device) {
    private val plusButton = By.res("$id/button_add")
    private val equalButton = By.res("$id/button_calc")
    private val resetButton = By.res("$id/button_all_clear")
    private val screen = By.res("$id/field")

    private val numberPattern = "$id/button_%s"

    val numberOnScreen: Int
        get() = find(screen).text
                .replace(Regex("[.,]"), "")
                .toInt()

    fun enter(number: Int): CalculatorScreen {
        inputNumber(number)
        return this
    }

    fun plus(number: Int): CalculatorScreen {
        val plus = find(plusButton)
        plus.click()

        inputNumber(number)

        val equal = find(equalButton)
        equal.click()
        return this
    }

    private fun inputNumber(number: Int) = number
            .toString()
            .toCharArray()
            .forEach {
                val digit = find(By.res(String.format(numberPattern, it)))
                digit.click()
            }

    fun reset(): CalculatorScreen {
        val reset = find(resetButton)
        reset.click()
        return this
    }
}
