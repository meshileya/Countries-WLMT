package com.example.countries_wlmt

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.countries_wlmt.ui.FragmentCountry
import com.example.countries_wlmt.ui.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.countries_wlmt", appContext.packageName)
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Launch the fragment in the initial state
        val scenario: FragmentScenario<FragmentCountry> =
            launchFragmentInContainer(themeResId = R.style.Theme_CountriesWLMT)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testFragmentView() {
        // Wait for the view with the specified ID to be available
        onView(withId(R.id.searchInputLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyCountriesDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyFilterWorks() {
        // Type text in search EditText
        onView(withId(R.id.searchEditText)).perform(typeText("Canada"))

        // Verify if filtered item is displayed
        onView(withText("Canada")).check(matches(isDisplayed()))
    }

    @Test
    fun verifyFilterNotWorking() {
        // Type text in search EditText
        onView(withId(R.id.searchEditText)).perform(typeText("Canada"))

        // Attempt to verify if filtered item is displayed
        onView(withText("Elupee################")).check(doesNotExist())
    }
}