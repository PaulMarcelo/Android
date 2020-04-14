package ec.com.paul.espressoudemy.utils

import android.os.Handler


/**
 * Created by Paul Yaguachi on 17/12/2019.
 * Paul Local
 */
class WebServiceMock {

    fun login(user: String, password: String, callback: Callback) {
        Handler().postDelayed({
            val check = true
            if (check) {
                callback.onSuccess("Usuario Correcto")
            }else{
                callback.onFailure("Usuario Incorrecto")
            }
        }, 5000)
    }
}