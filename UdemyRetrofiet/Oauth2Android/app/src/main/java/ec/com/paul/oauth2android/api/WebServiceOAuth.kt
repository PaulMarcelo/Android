package ec.com.paul.oauth2android.api

import ec.com.paul.oauth2android.shared_pref.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by Paul Yaguachi on 29/6/2019.
 * Paul Local
 */
class WebServiceOAuth private constructor() {
    private var retrofit: Retrofit? = null
    private var httpClientBuilder: OkHttpClient.Builder? = null

    companion object {
        private val BASE_URL = "http://192.168.100.25:8071/"
        private var loggingInterceptor: HttpLoggingInterceptor? = null
        private var instance: WebServiceOAuth? = null

        @Synchronized
        fun getInstance(): WebServiceOAuth {
            if (instance == null) {
                instance = WebServiceOAuth()
            }
            return instance!!
        }
    }

    init {
        httpClientBuilder = OkHttpClient.Builder()
        loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor!!)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClientBuilder!!.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit!!.create(serviceClass)
    }

    fun <S> createServiceWithOAuth2(serviceClass: Class<S>, tokenManager: TokenManager): S {
        val newClient = httpClientBuilder!!.addInterceptor { chain ->
            val requestOriginal = chain.request()
            val builder = requestOriginal.newBuilder()
            if (tokenManager.getToken().accessToken != null) {
                builder.addHeader("Authorization", "Bearer " + tokenManager.getToken().accessToken)
            }
            val request = builder.build()
            chain.proceed(request)
        }.authenticator(CustomAuthenticator.getInstance(tokenManager)).build()
        return retrofit!!.newBuilder().client(newClient).build().create(serviceClass)
    }

}