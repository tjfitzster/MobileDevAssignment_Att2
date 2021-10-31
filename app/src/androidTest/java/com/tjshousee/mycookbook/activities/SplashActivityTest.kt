package com.tjshousee.mycookbook.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.tjshousee.mycookbook.R
import org.junit.Test

import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)

class SplashActivityTest{
    @Test
    fun is_Activity_In_View() {
        val activityScenario = ActivityScenario.launch(SplashActivity::class.java)
        onView(withId(R.id.splash)).check(matches(isDisplayed()))
    }

    fun is_text_visibility_displayed() {
        val activityScenario = ActivityScenario.launch(SplashActivity::class.java)
        onView(withId(R.id.editTextTextPersonName)).check(matches(withText(R.string.titlestring)))
    }
}