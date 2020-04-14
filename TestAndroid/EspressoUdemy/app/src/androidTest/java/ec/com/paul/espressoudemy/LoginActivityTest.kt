package ec.com.paul.espressoudemy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.rule.IntentsTestRule


/**
 * Created by Paul Yaguachi on 30/11/2019.
 * Paul Local
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

//    @get:Rule
//    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @get:Rule
    var  mLoginActivityActivityTestRule = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun errorMessageIsNotShowInitiallyTest() {
        onView(withId(R.id.loginActivityTvError))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun hintIsDisplayedInEditTestUserNameTest() {
        onView(withId(R.id.loginActivityEtUserName))
            .check(matches(withHint(R.string.login_activity_username_hint)))
    }

    @Test
    fun hintIsDisplayedInEditTestPassworTest() {
        onView(withId(R.id.loginActivityEtPassword))
            .check(matches(withHint(R.string.login_activity_password_hint)))
    }


    @Test
    fun userNameLenghRuleErrorTest() {
        onView(withId(R.id.loginActivityEtUserName))
            .perform(typeText("alb"))

        onView(withId(R.id.loginActivityBtLogin))
            .perform(click())

        onView(withId(R.id.loginActivityTvError))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.login_activity_username_error)))
    }

    @Test
    fun passwordLenghRuleErrorTest() {
        onView(withId(R.id.loginActivityEtUserName))
            .perform(typeText("alberto"))

        onView(withId(R.id.loginActivityEtPassword))
            .perform(typeText("123"))

        onView(withId(R.id.loginActivityBtLogin))
            .perform(click())

        onView(withId(R.id.loginActivityTvError))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.login_activity_password_error)))
    }

    @Test
    fun validUserNameAndPasswordNotShowErrorMessageTest() {
        onView(withId(R.id.loginActivityEtUserName))
            .perform(typeText("alberto"))

        onView(withId(R.id.loginActivityEtPassword))
            .perform(typeText("1234"))

        onView(withId(R.id.loginActivityBtLogin))
            .perform(click())

        onView(withId(R.id.loginActivityTvError))
            .check(matches(not(isDisplayed())))
    }


    @Test
    fun validUserNameAndPasswordGoToRecyclerViewActivityTest() {
        onView(withId(R.id.loginActivityEtUserName))
            .perform(typeText("Alberto"))

        onView(withId(R.id.loginActivityEtPassword))
            .perform(typeText("1234"))

        onView(withId(R.id.loginActivityBtLogin))
            .perform(click())

        intended(hasComponent(RecyclerViewActivity::class.java.name))
        intended(hasExtra("USER", "Alberto"))
//        intended(
//            allOf(
//                hasComponent(RecyclerViewActivity::class.java.name),
//                hasExtra("USER", "Alberto")
//            )
//        )
    }

}