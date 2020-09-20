package com.rviannaoliveira.playgroundmvvm

import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.rviannaoliveira.main.presentation.adapter.CharacterListViewHolder
import org.hamcrest.Matchers

fun listItems(func: RobotMainTest.() -> Unit) = RobotMainTest().apply(func)

class RobotMainTest {

    fun checkExist(label: String): RobotMainTest {
        SystemClock.sleep(1500)
        Espresso.onView(ViewMatchers.withText(Matchers.containsString(label)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun clickItem(position: Int): RobotMainTest {
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CharacterListViewHolder>(position,
                    ViewActions.click()
                ))
        return this
    }

    fun backToList(): RobotMainTest {
        SystemClock.sleep(1000)
        Espresso.onView(ViewMatchers.withContentDescription("Navigate up"))
            .perform(ViewActions.click())
        return this
    }

}