package com.rviannaoliveira.playgroundmvvm

import android.content.Intent
import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.core.AllOf.allOf

fun detailItem(func: RobotDetailTest.() -> Unit) = RobotDetailTest().apply(func)

class RobotDetailTest {

    fun clickButton(): RobotDetailTest {
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.actionMaps))
            .perform(click())
        return this
    }

    fun verifyIntent(): RobotDetailTest {
        SystemClock.sleep(1000)
        intended(allOf(
            hasAction(Intent.ACTION_VIEW),
            toPackage("com.google.android.apps.maps"))
        )
        return this
    }

}