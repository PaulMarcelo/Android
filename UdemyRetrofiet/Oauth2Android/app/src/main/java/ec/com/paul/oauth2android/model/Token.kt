package ec.com.paul.oauth2android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Paul Yaguachi on 29/6/2019.
 * Paul Local
 */
class Token {
    @SerializedName("access_token")
    var  accessToken: String? = null
    @SerializedName("refresh_token")
    var refreshToken: String? = null
}