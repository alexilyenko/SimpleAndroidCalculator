package io.github.alexilyenko.sample.pageobject.tests

import io.github.alexilyenko.sample.pageobject.screens.Calculator
import org.junit.Assert.assertEquals
import org.junit.Test


class PageObjectTests : BaseTest() {

    private val randomNumber: Int get() = (Math.random() * 1000).toInt()

    @Test
    fun plus() {
        val firstNumber = randomNumber
        val secondNumber = randomNumber

        val calculator = Calculator(on = device)
                .enter(firstNumber)
                .plus(secondNumber)

        assertEquals(calculator.numberOnScreen, firstNumber + secondNumber)
    }

    @Test
    fun reset() {
        val calculator = Calculator(on = device)
                .enter(randomNumber)
                .reset()

        assertEquals(calculator.numberOnScreen, 0)
    }

    @Test
    fun multiply() {
        val firstNumber = randomNumber
        val secondNumber = randomNumber

        val calculator = Calculator(on = device)
                .enter(firstNumber)
                .multiply(by = secondNumber)

        assertEquals(calculator.numberOnScreen, firstNumber * secondNumber)
    }
}
