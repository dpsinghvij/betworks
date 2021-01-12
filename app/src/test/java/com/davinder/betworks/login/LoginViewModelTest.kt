package com.davinder.betworks.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.davinder.betworks.utils.getOrAwaitValue
import com.davinder.betworks.views.ViewItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test ValidateUsernameAndPassword when valid input`() {
        val loginViewModel = createViewModel("abc1", "dfda1")

        loginViewModel.validateUsernameAndPassword()

        assert(loginViewModel.validationStatus.getOrAwaitValue())
    }

    private fun createViewModel(username: String, password: String): LoginViewModel {
        return LoginViewModel(
            listOf(
                ViewItem.InputViewItem(id = "username", value = username, hint = ""),
                ViewItem.InputViewItem(id = "password", value = password, hint = "")
            ),
            repository
        ).apply { setUp() }
    }

    @Test
    fun `test ValidateUsernameAndPassword when one input invalid`() {
        val loginViewModel = createViewModel("abcd", "dfda1")

        loginViewModel.validateUsernameAndPassword()

        assertFalse(loginViewModel.validationStatus.getOrAwaitValue())
    }

    @Test
    fun `test ValidateUsernameAndPassword when both input invalid`() {
        val loginViewModel = createViewModel("abcd", "dfda")

        loginViewModel.validateUsernameAndPassword()

        assertFalse(loginViewModel.validationStatus.getOrAwaitValue())
    }

    @Test
    fun `test ValidateUsernameAndPassword when both input missing`() {
        val loginViewModel = createViewModel("", "")

        loginViewModel.validateUsernameAndPassword()

        assertFalse(loginViewModel.validationStatus.getOrAwaitValue())
    }

    @Test
    fun `test loginUser when valid input`() {
        val loginViewModel = createViewModel("abcde", "dfda1")

        every { repository.loginUser() } returns Single.just(UserLoginResult(isSuccessful = true,
            username = "abcde"))

        loginViewModel.loginUser()

        assertTrue(loginViewModel.loginStatus.getOrAwaitValue())

        assertEquals("abcde", UserSingleton.username)
    }
}