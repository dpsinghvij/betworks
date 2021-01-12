package com.davinder.betworks

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.davinder.betworks.RecyclerViewMatcher.Companion.withRecyclerView
import com.davinder.betworks.login.LoginFragment
import com.davinder.betworks.login.LoginFragmentDirections
import com.example.android.architecture.blueprints.todoapp.util.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@HiltAndroidTest
class LoginFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testTitleIsDisplayed() {
        launchFragmentInHiltContainer<LoginFragment>(null, R.style.Theme_Betworks)
        onView(withId(R.id.title)).check(matches(withText("Welcome to FireFly!")))
    }

    @Test
    fun testErrorIsThrown_wrongInput() {
        launchFragmentInHiltContainer<LoginFragment>(null, R.style.Theme_Betworks)
        onView(withRecyclerView(R.id.loginViews)
            .atPositionOnView(0, R.id.editText))
            .perform(click(), typeText("Fdfd"), closeSoftKeyboard())
        onView(withRecyclerView(R.id.loginViews)
            .atPositionOnView(1, R.id.editText))
            .perform(click(), typeText("Fdfd"), closeSoftKeyboard())
        onView(withRecyclerView(R.id.loginViews)
            .atPositionOnView(2, R.id.button))
            .perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testNextScreenIntentFired_correctInput() {
        val mockNavController = mock(NavController::class.java)

        launchFragmentInHiltContainer<LoginFragment>(null, R.style.Theme_Betworks) {
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withRecyclerView(R.id.loginViews)
            .atPositionOnView(0, R.id.editText))
            .perform(click(), typeText("Fdfd1"), closeSoftKeyboard())
        onView(withRecyclerView(R.id.loginViews)
            .atPositionOnView(1, R.id.editText))
            .perform(click(), typeText("Fdfd2"), closeSoftKeyboard())
        onView(withRecyclerView(R.id.loginViews)
            .atPositionOnView(2, R.id.button))
            .perform(click())

        verify(mockNavController).navigate(LoginFragmentDirections.actionLoginFragmentToUserScreenFragment())
    }

}