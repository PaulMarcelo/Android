package ec.com.paul.arquitecturamvvm.repository

import androidx.lifecycle.MutableLiveData
import ec.com.paul.arquitecturamvvm.api.ApiResponse
import ec.com.paul.arquitecturamvvm.api.WebServiceApi
import ec.com.paul.arquitecturamvvm.db.GithubDb
import ec.com.paul.arquitecturamvvm.modelo.RepoSearchResponse
import ec.com.paul.arquitecturamvvm.modelo.RepoSearchResult
import java.lang.Exception
import androidx.lifecycle.LiveData
import retrofit2.Response


/**
 * Created by Paul Yaguachi on 28/10/2019.
 * Paul Local
 */
class FetchNextSearchPageTask internal constructor(
    private val query: String,
    private val githubService: WebServiceApi,
    private val db: GithubDb
) : Runnable {

    private val liveData = MutableLiveData<Resource<Boolean>>()


    override fun run() {
        val current = db.repoDao().findSearchResult(query)
        if (current == null) {
            liveData.postValue(null)
            return
        }
        val nextPage = current.next
        if (nextPage == null) {
            liveData.postValue(Resource.success(false))
            return
        }
        try {
            val response: Response<RepoSearchResponse> = githubService
                .searchRepos(query, nextPage).execute()
            val apiResponse = ApiResponse(response)
            if (apiResponse.isSuccessful) {
                val ids = arrayListOf<Int>()
                ids.addAll(current.repoIds!!)
                ids.addAll(apiResponse.body!!.getRepoIds())
                val merged = RepoSearchResult(query, ids, apiResponse.body.total, apiResponse.nextPage)

                try {
                    db.beginTransaction()
                    db.repoDao().insert(merged)
                    db.repoDao().insertRepos(apiResponse.body.items!!)
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
                liveData.postValue(Resource.success(apiResponse.nextPage != null))
            } else {
                liveData.postValue(Resource.error(apiResponse.errorMessage, true))
            }
        } catch (e: Exception) {
            liveData.postValue(Resource.error(e.message, true))
        }

    }

    internal fun getLiveData(): LiveData<Resource<Boolean>> {
        return liveData
    }
}