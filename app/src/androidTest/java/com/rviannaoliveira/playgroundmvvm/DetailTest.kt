package com.rviannaoliveira.playgroundmvvm

import android.content.Intent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.rviannaoliveira.main.presentation.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailTest {
    @Rule
    @JvmField
    val activityRule = IntentsTestRule(MainActivity::class.java, true, false)
    private lateinit var app: CustomApplication
    private lateinit var mockWebServer: MockWebServer
    private lateinit var robotMainTest: RobotMainTest
    private lateinit var robotDetailTest: RobotDetailTest

    @Before
    fun setup() {
        robotMainTest = RobotMainTest()
        robotDetailTest = RobotDetailTest()
        configureDI()
    }

    @Test
    @Throws(Exception::class)
    fun opened_google_maps_when_user_enter_detail_and_click_button() {
        listItems {
            clickItem(2)
        }
        detailItem {
            clickButton()
            verifyIntent()
        }
    }

    private fun configureDI() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        app = instrumentation.targetContext.applicationContext as CustomApplication
        val appInjector = DaggerTestAppComponent.builder()
            .application(app)
            .build()
        appInjector.inject(app)

        mockWebServer = appInjector.getMockWebServer()
        val intent = Intent(
            InstrumentationRegistry.getInstrumentation()
                .targetContext, MainActivity::class.java
        )
        activityRule.launchActivity(intent)
    }
}