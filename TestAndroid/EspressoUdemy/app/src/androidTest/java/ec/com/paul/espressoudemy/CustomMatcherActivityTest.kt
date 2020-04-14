package ec.com.paul.espressoudemy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Paul Yaguachi on 29/12/2019.
 * Paul Local
 */
@RunWith(AndroidJUnit4::class)
class CustomMatcherActivityTest {

//    @get:Rule
//    var activityRule = ActivityScenarioRule(CustomMatcherActivity::class.java)

    @get:Rule
    var  activityRule = IntentsTestRule(CustomMatcherActivity::class.java)

    @Test
    fun checkWithWrongUserNameTest() {
        onView(withId(R.id.customMatcherActivityEtUserName)).perform(typeText("alb"))
        onView(withId(R.id.customMatcherActivityBtLogin)).perform(click())
        onView(withId(R.id.customMatcherActivityEtUserName)).check(
            matches(
                ErrorEditTextMatcher.withError(
                    Matchers.containsString("Username ha de ser más de 4 caracteres")
                )
            )
        )
    }

    @Test
    fun checkWithWrongPasswordTest() {
        onView(withId(R.id.customMatcherActivityEtUserName))
            .perform(typeText("alberto"))
        onView(withId(R.id.customMatcherActivityEtPassword))
            .perform(typeText("123"))
        onView(withId(R.id.customMatcherActivityBtLogin))
            .perform(click())
        onView(withId(R.id.customMatcherActivityEtPassword))
            .check(
                matches(
                    ErrorEditTextMatcher.withError(
                        Matchers.containsString("Password ha de ser más de 4 caracteres")
                    )
                )
            )
    }

    @Test
    fun validUserNameAndPasswordGoToSpinnerActivityTest() {
        onView(withId(R.id.customMatcherActivityEtUserName))
            .perform(typeText("Alberto"))
        onView(withId(R.id.customMatcherActivityEtPassword))
            .perform(typeText("1234"))
        onView(withId(R.id.customMatcherActivityBtLogin))
            .perform(click())
        intended(hasComponent(SpinnerActivity::class.java.name))
    }

}