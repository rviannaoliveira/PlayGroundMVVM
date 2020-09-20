package com.rviannaoliveira.playgroundmvvm

import android.content.Intent
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
class MainTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)
    private lateinit var app: CustomApplication
    private lateinit var mockWebServer: MockWebServer
    private lateinit var robotMainTest: RobotMainTest

    @Before
    fun setup() {
        robotMainTest = RobotMainTest()
        configureDI()
    }

    @Test
    @Throws(Exception::class)
    fun click_item_list_and_back() {
        listItems {
            checkExist(app.baseContext.getString(R.string.app_name))
            clickItem(2)
            checkExist(app.baseContext.getString(R.string.look_location))
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