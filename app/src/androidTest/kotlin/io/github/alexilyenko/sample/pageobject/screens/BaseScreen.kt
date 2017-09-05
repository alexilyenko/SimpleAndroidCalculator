package io.github.alexilyenko.sample.pageobject.screens

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.BySelector
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObject2

abstract class BaseScreen(private val device: UiDevice) {

    val id = "${InstrumentationRegistry.getTargetContext().packageName}:id"

    fun find(by: BySelector): UiObject2 = device.findObject(by)
}
