package ec.com.paul.oauth2android.api

import ec.com.paul.oauth2android.model.MovimientoBancario
import ec.com.paul.oauth2android.model.Token
import ec.com.paul.oauth2android.model.User
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Paul Yaguachi on 30/6/2019.
 * Paul Local
 */
interface WebServiceOAuthApi {

    @GET("/api/users")
    fun obtenerUsuarios(): Call<List<User>>

    @Headers("Accept: application/json")
    @POST("/api/create_user")
    fun crearUsuario(@Body user: User): Call<Void>


    @FormUrlEncoded
    @POST("/oauth/token")
    fun obtenerToken(
        @Header("Authorization") authorization: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String
    ): Call<Token>


    @FormUrlEncoded
    @POST("/oauth/token")
    fun obtenerTokenconRefreshToken(
        @Header("Authorization") authorization: String,
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String
    ): Call<Token>

    @GET("/api/oauth2/movimiento_bancario")
    fun obtenerMovimientos(@Header("Authorization") accessToken: String): Call<List<MovimientoBancario>>

    @POST("/api/oauth2/movimiento_bancario_user")
    fun obtenerMovimientosUser(@Body user: User): Call<List<MovimientoBancario>>
}
