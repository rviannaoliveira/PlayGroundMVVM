package com.rviannaoliveira.playgroundmvvm

import RoboMain
import android.content.Intent
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.rviannaoliveira.main.presentation.MainActivity
import listItems
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)
    private lateinit var robo: RoboMain
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        robo = RoboMain()
        server.initMockServer()
        activityRule.launchActivity(Intent())
    }

    @Test
    @Throws(Exception::class)
    fun click_item_list_and_back() {
        listItems {
            clickItem(2)
            backToList()
        }
    }
}