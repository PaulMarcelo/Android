package ec.com.paul.blurimage

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Paul Yaguachi on 12/6/2020.
 * Paul Local
 */
interface ServiceCamera {
    @Multipart
    @POST("isBlurImage")
    fun uploadFile(@Part file: MultipartBody.Part): Call<ResponseBody>

}