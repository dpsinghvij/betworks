package com.davinder.betworks.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.davinder.betworks.utils.getOrAwaitValue
import com.davinder.betworks.views.ViewItem
import junit.framework.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `test ValidateUsernameAndPassword when valid input`() {
        val loginViewModel = LoginViewModel(
            listOf(
                ViewItem.InputViewItem(id = "", value = "abc1", hint = ""),
                ViewItem.InputViewItem(id = "", value = "dfda1", hint = "")
            )
        ).apply { setUp() }

        loginViewModel.validateUsernameAndPassword()

        assert(loginViewModel.validationError.getOrAwaitValue())
    }

    @Test
    fun `test ValidateUsernameAndPassword when one input invalid`() {
        val loginViewModel = LoginViewModel(
            listOf(
                ViewItem.InputViewItem(id = "", value = "abcd", hint = ""),
                ViewItem.InputViewItem(id = "", value = "dfda1", hint = "")
            )
        ).apply { setUp() }

        loginViewModel.validateUsernameAndPassword()

        assertFalse(loginViewModel.validationError.getOrAwaitValue())
    }

    @Test
    fun `test ValidateUsernameAndPassword when both input invalid`() {
        val loginViewModel = LoginViewModel(
            listOf(
                ViewItem.InputViewItem(id = "", value = "abcd", hint = ""),
                ViewItem.InputViewItem(id = "", value = "dfda", hint = "")
            )
        ).apply { setUp() }

        loginViewModel.validateUsernameAndPassword()

        assertFalse(loginViewModel.validationError.getOrAwaitValue())
    }

    @Test
    fun `test ValidateUsernameAndPassword when both input missing`() {
        val loginViewModel = LoginViewModel(
            listOf(
                ViewItem.InputViewItem(id = "", value = "", hint = ""),
                ViewItem.InputViewItem(id = "", value = "", hint = "")
            )
        ).apply { setUp() }

        loginViewModel.validateUsernameAndPassword()

        assertFalse(loginViewModel.validationError.getOrAwaitValue())
    }
}