package io.github.alexilyenko.sample.beforepageobject

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddTest {

    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        device = startDevice()
    }

    @Test
    fun addition() {
        device.findObject(By.res("io.github.alexilyenko.sample:id/button_2")).click()
        device.findObject(By.res("io.github.alexilyenko.sample:id/button_add")).click()
        device.findObject(By.res("io.github.alexilyenko.sample:id/button_8")).click()
        device.findObject(By.res("io.github.alexilyenko.sample:id/button_calc")).click()
        val result = device.findObject(By.res("io.github.alexilyenko.sample:id/field")).text.toInt()
        assertEquals("Result should be equal to 10", result, 10)
    }


    private fun startDevice(): UiDevice {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        val pkg = InstrumentationRegistry.getTargetContext().packageName
        val context = InstrumentationRegistry.getContext()
        val intent = getIntent(context, pkg)
        val launchTimeout = 10000L

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(pkg).depth(0)), launchTimeout)
        return device
    }

    private fun getIntent(context: Context, pkg: String): Intent = context.packageManager
            .getLaunchIntentForPackage(pkg)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
}
