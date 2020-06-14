package ec.com.paul.blurimage

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Paul Yaguachi on 12/6/2020.
 * Paul Local
 */
class WSConfig {
    companion object{
        private const val BASE_URL = "http://localhost:8080/"
        fun getRetrofit(): Retrofit? {
            val gson: Gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }

}