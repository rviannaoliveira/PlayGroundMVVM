package com.rviannaoliveira.shared

import android.content.res.Resources
import com.rviannaoliveira.shared.extensions.getPriceFormat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class StringExtensionsTest {
    @MockK
    private lateinit var resources: Resources

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { resources.getString(R.string.empty) } answers { "" }
    }

    @Test
    fun `should format string when arrive some price`() {
        assert("1200".getPriceFormat(resources) == "BRL1,200.00")

        verify(exactly = 0) {
            resources.getString(R.string.empty)
        }
    }

    @Test
    fun `should return empty`() {
        val string : String? = null

        assert(string.getPriceFormat(resources) == "")

        verify(exactly = 1) {
            resources.getString(R.string.empty)
        }
    }


}