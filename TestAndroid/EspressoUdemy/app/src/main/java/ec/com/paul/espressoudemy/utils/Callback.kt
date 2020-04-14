package ec.com.paul.espressoudemy.utils

/**
 * Created by Paul Yaguachi on 17/12/2019.
 * Paul Local
 */

interface Callback {
    fun onSuccess(response: String)
    fun onFailure(response: String)
}
