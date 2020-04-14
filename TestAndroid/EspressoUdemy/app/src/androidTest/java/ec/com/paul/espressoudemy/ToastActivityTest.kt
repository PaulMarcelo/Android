package ec.com.paul.espressoudemy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Paul Yaguachi on 30/12/2019.
 * Paul Local
 */
@RunWith(AndroidJUnit4::class)
class ToastActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ToastActivity::class.java)


    @Test

    fun toastMessageIsDisplayedTest() {
        onView(withId(R.id.btToast)).perform(click())
        onView(withText("Testeando un Toast")).inRoot(ToastMatcher()).check(matches(isDisplayed()))


    }

}