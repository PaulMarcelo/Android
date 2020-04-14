package ec.com.paul.jwtandroid.api


import ec.com.paul.jwtandroid.model.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


/**
 * Created by Paul Yaguachi on 13/4/2019.
 * Paul Local
 */
interface WebServiceApi {
    @POST("/token")
    fun obtenerToken(@Body login: Login): Call<List<String>>


    @GET("/api/acceso_solo_con_jwt")
    fun obtenerMovimientoBancario(@Header("Authorization") authHeader: String): Call<List<String>>
}
