package ec.com.paul.espressoudemy

import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.TextView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf.allOf

import org.hamcrest.core.StringStartsWith.startsWith

/**
 * Created by Paul Yaguachi on 24/11/2019.
 * Paul Local
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun myFirstTest(){

        onView(withId(R.id.mainActivityTv))

        onView(withText("Prueba Espresso"))

        onView(withContentDescription("elemento TextView"))

        onView(withHint("texto hint"))

        onView(allOf(withText("Prueba Espresso"), withParent(withId(R.id.mainActivityRl))))

        onView(allOf(withId(R.id.mainActivityTv), isDisplayed()))

        onView(withText(startsWith("Prueba")))

        onView(allOf(instanceOf(TextView::class.java), withId(R.id.mainActivityTv)))

        //View Action

        onView(withId(R.id.mainActivityEt)).perform(typeText("30"))

        onView(withId(R.id.mainActivityEt)).perform(replaceText("34"))

        onView(withId(R.id.mainActivityEt)).perform(clearText())

        onView(withId(R.id.mainActivityBt)).perform(click())

        onView(withId(R.id.mainActivityRl)).perform(swipeLeft())

        onView(withId(R.id.mainActivityEt)).perform(typeText("34")
                , closeSoftKeyboard())

        onView(withId(R.id.mainActivityTv)).perform(openLinkWithText("www.google.es"))


        //View Assertions

        onView(withId(R.id.mainActivityTv)).check(matches(isDisplayed()))

        onView(withId(R.id.mainActivityTv)).check(matches(withText("Prueba Espresso")))

        onView(withId(R.id.mainActivityTv)).check(matches(withText(R.string.app_name)))

        onView(withId(R.id.mainActivityTv)).check(isCompletelyLeftOf(withId(R.id.mainActivityEt)))

        onView(withId(R.id.mainActivityTv)).check(doesNotExist())

    }

}