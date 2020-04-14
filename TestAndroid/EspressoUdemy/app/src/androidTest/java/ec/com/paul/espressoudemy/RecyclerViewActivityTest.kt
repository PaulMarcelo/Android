package ec.com.paul.espressoudemy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ec.com.paul.espressoudemy.utils.PersonaAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed



/**
 * Created by Paul Yaguachi on 14/12/2019.
 * Paul Local
 */
@RunWith(AndroidJUnit4::class)
class RecyclerViewActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(RecyclerViewActivity::class.java)


    @Test
    fun recyclerViewFirstTest() {
        onView(withText("Alberto2")).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerViewScrollTest() {
        onView(withId(R.id.recyclerActivityRv))
            .perform(RecyclerViewActions.scrollToPosition<PersonaAdapter.PersonaViewHolder>(20))

        onView(withText("Paul")).check(matches(isDisplayed()))
    }


    @Test
    fun recyclerViewScrollClickTest() {
        onView(withId(R.id.recyclerActivityRv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PersonaAdapter.PersonaViewHolder>(20, click()))

        onView(withText("Paul"))
            .check(matches(isDisplayed()))
    }

}