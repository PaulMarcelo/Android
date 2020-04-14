package ec.com.paul.oauth2android.api

import android.util.Base64
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ec.com.paul.oauth2android.shared_pref.TokenManager
import android.util.Base64.NO_WRAP
import ec.com.paul.oauth2android.model.Token


/**
 * Created by Paul Yaguachi on 30/6/2019.
 * Paul Local
 */
class CustomAuthenticator private constructor(tokenManager: TokenManager) : Authenticator {


    private var tokenManager: TokenManager? = null

    companion object {
        private var INSTANCE: CustomAuthenticator? = null
        @Synchronized
        fun getInstance(tokenManager: TokenManager): CustomAuthenticator {
            if (INSTANCE == null) {
                INSTANCE = CustomAuthenticator(tokenManager)
            }
            return INSTANCE!!
        }
    }

    init {
        this.tokenManager = tokenManager
    }


    override fun authenticate(route: Route?, response: Response): Request? {
        val authHeader = "Basic " + Base64.encodeToString("androidApp:123".toByteArray(), Base64.NO_WRAP)

        val token = tokenManager!!.getToken()

        val call = WebServiceOAuth
            .getInstance()
            .createService(WebServiceOAuthApi::class.java)
            .obtenerTokenconRefreshToken(
                authHeader, token.refreshToken!!, "refresh_token"
            )
        val response1: retrofit2.Response<Token> = call.execute()
        return if(response1.isSuccessful){
            val newToken = response1.body()
            tokenManager!!.saveToken(newToken!!)
            response.request().newBuilder()
                .header("Authorization", "Bearer " + response1.body()!!.accessToken).build()
        }else{
            null
        }
    }
}