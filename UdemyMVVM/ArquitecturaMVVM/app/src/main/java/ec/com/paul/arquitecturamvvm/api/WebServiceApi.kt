package ec.com.paul.arquitecturamvvm.api

import androidx.lifecycle.LiveData
import ec.com.paul.arquitecturamvvm.modelo.Contributor
import ec.com.paul.arquitecturamvvm.modelo.Repo
import ec.com.paul.arquitecturamvvm.modelo.RepoSearchResponse
import ec.com.paul.arquitecturamvvm.modelo.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Paul Yaguachi on 17/10/2019.
 * Paul Local
 */
interface WebServiceApi {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repo>>>

    @GET("repos/{owner}/{name}")
    fun getRepo(@Path("owner") owner: String, @Path("name") name: String): LiveData<ApiResponse<Repo>>

    @GET("repos/{owner}/{name}/contributors")
    fun getContributors(@Path("owner") owner: String, @Path("name") name: String): LiveData<ApiResponse<List<Contributor>>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<RepoSearchResponse>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Call<RepoSearchResponse>
}
