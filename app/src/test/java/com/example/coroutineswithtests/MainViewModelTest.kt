package com.example.coroutineswithtests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.coroutineswithtests.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
//Code lines commented are in a custom rule - CoroutineTestRule.kt
class MainViewModelTest : TestCase() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    //private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var vm: MainViewModel

    @Before
    fun setup() {
        //Dispatchers.setMain(testDispatcher)
        vm = MainViewModel(coroutineTestRule.testDispatcher)
    }

    /*@After
    fun afterTest() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }*/

    @Test
    fun `Success if user and pass are not empty`() {
        //Bloquear hilo principal hasta que las corrutinas acaben
        coroutineTestRule.testDispatcher.runBlockingTest {
            val observer: Observer<Boolean> = mock()
            vm.loginResult.observeForever(observer)
            vm.onSubmitClicked("user", "pass")
            verify(observer).onChanged(true)
        }
    }

    @Test
    fun `Error if user is empty`() {
        //Bloquear hilo principal hasta que las corrutinas acaben
        coroutineTestRule.testDispatcher.runBlockingTest {
            val observer: Observer<Boolean> = mock()
            vm.loginResult.observeForever(observer)
            vm.onSubmitClicked("", "pass")
            verify(observer).onChanged(false)
        }
    }
}