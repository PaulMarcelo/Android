package ec.com.paul.espressoudemy


import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Paul Yaguachi on 29/12/2019.
 * Paul Local
 */
@RunWith(AndroidJUnit4::class)
class SpinnerActivityTest{

    @get:Rule
    var activityRule=ActivityScenarioRule(SpinnerActivity::class.java)

    @Test
    fun spinnerTest(){
        onView(withId(R.id.spinnerActivitySp)).perform(click())

        onData(anything()).atPosition(1).perform(click())


        onView(withId(R.id.spinnerActivitySp)).check(matches(withSpinnerText(containsString("Manuel"))))
    }

    @Test
    fun spinnerTest2() {
        onView(withId(R.id.spinnerActivitySp))
            .perform(click())
        onData(
            allOf(
                `is`(instanceOf(String::class.java)),
                `is`("Pablo")
            )
        )
            .perform(click())

        onView(withId(R.id.spinnerActivityTv))
            .check(matches(withText("Pablo")))
    }
}