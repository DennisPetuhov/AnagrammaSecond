package com.example.anagrammasecond

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyMainScreenViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var myTestViewModel: SharedViewModel

    @Before
    fun setup() {
        myTestViewModel = SharedViewModel()
    }

    @After
    fun cleanup() {
    }

    @Test
    fun settingMainDispatcher() = runTest {
        myTestViewModel.onValueChangedTextInput("Hello World")
        assertEquals("Hello World", myTestViewModel.incomingText.value)
    }

    @Test
    fun `onValueChange updates text state`() = runTest {
        myTestViewModel.onValueChangedTextInput("Hello World")
        assertEquals("Hello World", myTestViewModel.incomingText.value)
    }

    @Test
    fun `onValueChange with empty string updates text state`() {
        myTestViewModel.onValueChangedTextInput("")
        assertEquals("", myTestViewModel.incomingText.value)
    }
}
