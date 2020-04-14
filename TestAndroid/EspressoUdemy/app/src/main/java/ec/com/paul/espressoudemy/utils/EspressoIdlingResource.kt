package ec.com.paul.espressoudemy.utils

import androidx.test.espresso.IdlingResource

/**
 * Created by Paul Yaguachi on 17/12/2019.
 * Paul Local
 */


object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val countingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    val idlingResource: IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}