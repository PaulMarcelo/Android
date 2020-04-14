package ec.com.paul.oauth2android.shared_pref

import android.content.SharedPreferences
import ec.com.paul.oauth2android.model.Token


/**
 * Created by Paul Yaguachi on 29/6/2019.
 * Paul Local
 */
class TokenManager private constructor(sharedPreferences: SharedPreferences) {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null


    companion object {
        val SHARED_PREFERENCES = "SHARED_PREFERENCES"
        private val SHARED_PREFERENCES_ACCESS_TOKEN = "SHARED_PREFERENCES_ACCESS_TOKEN"
        private val SHARED_PREFERENCES_REFRESH_TOKEN = "SHARED_PREFERENCES_REFRESH_TOKEN"
        private var INSTANCE: TokenManager? = null
        @Synchronized
        fun getIntance(sharedPreferences: SharedPreferences): TokenManager {
            if (INSTANCE == null) {
                INSTANCE = TokenManager(sharedPreferences)
            }
            return INSTANCE!!
        }

    }

    init {
        this.sharedPreferences = sharedPreferences
        this.editor = sharedPreferences.edit()
    }


    fun saveToken(token: Token) {
        editor!!.putString(SHARED_PREFERENCES_ACCESS_TOKEN, token.accessToken).commit()
        editor!!.putString(SHARED_PREFERENCES_REFRESH_TOKEN, token.refreshToken).commit()
    }

    fun getToken(): Token {
        val token = Token()
        token.accessToken = sharedPreferences!!.getString(SHARED_PREFERENCES_ACCESS_TOKEN, null)
        token.refreshToken = sharedPreferences!!.getString(SHARED_PREFERENCES_REFRESH_TOKEN, null)
        return token
    }

}