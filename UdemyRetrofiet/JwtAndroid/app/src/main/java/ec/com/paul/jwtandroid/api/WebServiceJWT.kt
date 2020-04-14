package ec.com.paul.jwtandroid.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Paul Yaguachi on 22/6/2019.
 * Paul Local
 */
class WebServiceJWT {
    private var retrofit: Retrofit
    private  var loggingInterceptor: HttpLoggingInterceptor
    private  var httpClientBuilder: OkHttpClient.Builder
    companion object {
        const val BASE_URL_JWT= "http://192.168.100.25:8046"
        private var instance: WebServiceJWT? = null
        @Synchronized
        fun getInstance(): WebServiceJWT {
            if (this.instance == null) {
                this.instance = WebServiceJWT()
            }
            return this.instance!!
        }
    }
    init {
        this.loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        this.httpClientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        this.retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_JWT)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}
