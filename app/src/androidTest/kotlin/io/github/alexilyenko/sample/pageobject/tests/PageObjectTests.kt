package io.github.alexilyenko.sample.pageobject.tests

import io.github.alexilyenko.sample.pageobject.screens.CalculatorScreen
import org.junit.Assert.assertEquals
import org.junit.Test


class PageObjectTests : BaseTest() {

    private val randomNumber: Int get() = (Math.random() * 1000).toInt()

    @Test
    fun plus() {
        val firstNumber = randomNumber
        val secondNumber = randomNumber

        val calculator = CalculatorScreen(device)
        val result = calculator
                .enter(firstNumber)
                .plus(secondNumber)
                .numberOnScreen

        assertEquals(result, firstNumber + secondNumber)
    }

    @Test
    fun reset() {
        val calculator = CalculatorScreen(device)
        val result = calculator
                .enter(randomNumber)
                .reset()
                .numberOnScreen

        assertEquals(result, 0)
    }

    @Test
    fun multiply() {
        val firstNumber = randomNumber
        val secondNumber = randomNumber

        val calculator = CalculatorScreen(device)
        val result = calculator
                .enter(firstNumber)
                .multiply(by = secondNumber)
                .numberOnScreen

        assertEquals(result, firstNumber * secondNumber)
    }
}
