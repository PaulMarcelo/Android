package ec.com.paul.espressoudemy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ec.com.paul.espressoudemy.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Paul Yaguachi on 17/12/2019.
 * Paul Local
 */
@RunWith(AndroidJUnit4::class)
class IdlingActivityTest{

    @get:Rule
    var activityRule = ActivityScenarioRule(IdlingActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun textIsDisplayedAfterWebServiceTest() {
        onView(withId(R.id.idlingActivityBt))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.idlingActivityTv))
            .check(matches(isDisplayed()))
            .check(matches(withText("Paul")))
    }




}