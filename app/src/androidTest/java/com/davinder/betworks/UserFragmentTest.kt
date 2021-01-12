package com.davinder.betworks

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.davinder.betworks.login.UserSingleton
import com.davinder.betworks.userscreen.UserScreenFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class UserFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()

    }

    @Test
    fun testCorrectUsernameDisplayed_givenUsername() {
        // Given
        UserSingleton.username = "Awesome"

        // When
        launchFragmentInHiltContainer<UserScreenFragment>(null, R.style.Theme_Betworks)

        // Assert
        onView(withId(R.id.username)).check(matches(withText("Awesome")))
    }
}