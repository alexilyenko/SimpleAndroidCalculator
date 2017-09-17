package io.github.alexilyenko.sample.pageobject.tests

import io.github.alexilyenko.sample.pageobject.screens.Calculator
import org.junit.Assert.assertEquals
import org.junit.Test


class PageObjectTests : BaseTest() {
    private val randomNumber: Int get() = (Math.random() * 1000).toInt()
    private val firstNumber = randomNumber
    private val secondNumber = randomNumber

    @Test
    fun plus() {
        val calculator = Calculator(on = device)
                .enter(firstNumber)
                .plus(secondNumber)

        assertEquals(firstNumber + secondNumber, calculator.numberOnScreen)
    }

    @Test
    fun reset() {
        val calculator = Calculator(on = device)
                .enter(firstNumber)
                .reset()

        assertEquals(5, calculator.numberOnScreen)
    }

    @Test
    fun multiply() {
        val calculator = Calculator(on = device)
                .enter(firstNumber)
                .multiply(by = secondNumber)

        assertEquals(firstNumber * secondNumber, calculator.numberOnScreen)
    }


    @Test
    fun minus() {
        val calculator = Calculator(on = device)
                .enter(firstNumber)
                .minus(secondNumber)

        assertEquals(firstNumber - secondNumber, calculator.numberOnScreen)
    }

    @Test
    fun minusZero() {
        val calculator = Calculator(on = device)
                .minus(firstNumber)

        assertEquals(-firstNumber, calculator.numberOnScreen)
    }
}
