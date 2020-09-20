package com.rviannaoliveira.playgroundmvvm
import android.content.Intent
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.rviannaoliveira.main.presentation.MainActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection

fun MockWebServer.initMockServer() {
    this.start()
    this.url("/v1/items/")
    this.setDispatcher(dispatcher)
}

val dispatcher: Dispatcher = object : Dispatcher() {
    @Throws(InterruptedException::class)
    override fun dispatch(request: RecordedRequest): MockResponse {
        val nameFile = request.path.removePrefix("/")

        return getMockResponseOK(nameFile)
    }

    private fun getMockResponseOK(nameFile: String): MockResponse {
        return MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(readFileFromAssets(nameFile))
    }
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun readFileFromAssets(fileName: String): String {
    val builder = StringBuilder()

    try {
        val stream =
            InstrumentationRegistry.getInstrumentation().targetContext.assets.open(fileName)
        val bReader = BufferedReader(InputStreamReader(stream, "UTF-8") as Reader?)
        var line = bReader.readLine()

        while (line != null) {
            builder.append(line)
            line = bReader.readLine()
        }
    } catch (e: IOException) {
        Log.e(">>>>>>>", e.message!!)
    }
    return builder.toString()
}