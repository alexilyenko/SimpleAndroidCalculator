package io.github.alexilyenko.sample.pageobject.tests

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import com.squareup.spoon.Spoon
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith


@Ignore("Ignoring BaseTest")
@RunWith(AndroidJUnit4::class)
abstract class BaseTest {
    lateinit var device: UiDevice

    @Before
    fun setUp() {
        device = startDevice()
    }

    private fun startDevice(): UiDevice {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        val pkg = InstrumentationRegistry.getTargetContext().packageName
        val context = InstrumentationRegistry.getContext()
        val intent = getIntent(context, pkg)
        val launchTimeout = 10000L

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(pkg).depth(0)), launchTimeout)
        Spoon.screenshot(activity, "on_start")
        return device
    }

    private fun getIntent(context: Context, pkg: String): Intent = context.packageManager
            .getLaunchIntentForPackage(pkg)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

    @Rule
    @JvmField
    var watcher = object : TestWatcher() {
        override fun failed(e: Throwable, description: Description) {
            Spoon.screenshot(activity, "on_failed", description.testClass.name, description.methodName)
        }

        override fun succeeded(description: Description) {
            Spoon.screenshot(activity, "on_finish", description.testClass.name, description.methodName)
        }
    }

    private val activity: Activity?
        get() {
            var activity: Activity? = null
            InstrumentationRegistry.getInstrumentation().runOnMainSync {
                activity = ActivityLifecycleMonitorRegistry
                        .getInstance()
                        .getActivitiesInStage(Stage.RESUMED)
                        .first()

            }
            return activity
        }
}