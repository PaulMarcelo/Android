package ec.com.paul.espressoudemy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Paul Yaguachi on 30/11/2019.
 * Paul Local
 */
@RunWith(AndroidJUnit4::class)
class Main2ActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(Main2Activity::class.java)

    @Test
    fun isDisplayedOnTheScreenTest() {
        onView(withText("Mi primer Expresso test"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isDispledOnTheScreenWithIdTest() {
        onView(withId(R.id.activityMain2TvMiFirstTest))
            .check(matches(isDisplayed()))
    }

    @Test
    fun ensureResetWorkTest() {
        onView(withId(R.id.activityMain2BtReset))
            .perform(click())
        onView(withId(R.id.activityMain2TvMiFirstTest))
            .check(matches(withText("Reset Text")))
    }

    @Test
    fun ensureChangeEditTextWorkTest(){
        onView(withId(R.id.activityMain2EtMiFirstEditText))
            .perform(typeText("Prueba")
                , closeSoftKeyboard())

        onView(withId(R.id.activityMain2btChange))
            .perform(click())

        onView(withId(R.id.activityMain2TvMiFirstTest))
            .check(matches(withText("Prueba")))
    }

    @Test
    fun isDisplayedOnTheScreenErrorTest(){
        onView(withText("No me muestra"))
            .check(matches(isDisplayed()))
    }

}