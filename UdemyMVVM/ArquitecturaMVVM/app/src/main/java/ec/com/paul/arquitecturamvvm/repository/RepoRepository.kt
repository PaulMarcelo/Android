package ec.com.paul.arquitecturamvvm.repository

import androidx.lifecycle.LiveData
import ec.com.paul.arquitecturamvvm.AppExecutors
import ec.com.paul.arquitecturamvvm.api.ApiResponse
import ec.com.paul.arquitecturamvvm.api.WebServiceApi
import ec.com.paul.arquitecturamvvm.db.GithubDb
import ec.com.paul.arquitecturamvvm.db.RepoDao
import ec.com.paul.arquitecturamvvm.modelo.Contributor
import ec.com.paul.arquitecturamvvm.modelo.Repo
import ec.com.paul.arquitecturamvvm.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import ec.com.paul.arquitecturamvvm.modelo.RepoSearchResponse
import ec.com.paul.arquitecturamvvm.modelo.RepoSearchResult
import ec.com.paul.arquitecturamvvm.utils.AbsentLiveData
import androidx.lifecycle.Transformations
import androidx.arch.core.util.Function





/**
 * Created by Paul Yaguachi on 30/10/2019.
 * Paul Local
 */

@Singleton
class RepoRepository @Inject
internal constructor(
    private val db: GithubDb,
    private val repoDao: RepoDao,
    private val githubService: WebServiceApi,
    private val appExecutors: AppExecutors
) {
    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun loadRepos(owner: String): LiveData<Resource<List<Repo>>> {
        return object : NetworkBoundResource<List<Repo>, List<Repo>>(appExecutors) {
            override fun shouldFetch(data: List<Repo>?): Boolean {
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(owner)
            }

            override fun loadFromDb(): LiveData<List<Repo>> {
                return repoDao.loadRepositories(owner)
            }

            override fun saveCallResult(item: List<Repo>?) {
                repoDao.insertRepos(item!!)
            }

            override fun createCall(): LiveData<ApiResponse<List<Repo>>> {
                return githubService.getRepos(owner)
            }

            override  fun onFetchFailed(){
                repoListRateLimit.reset(owner)
            }
        }.asLiveData()
    }

    fun loadRepo(owner: String, name: String): LiveData<Resource<Repo>>{
        return object : NetworkBoundResource<Repo, Repo>(appExecutors) {
            override fun shouldFetch(data: Repo?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Repo> {
                return repoDao.load(owner, name)
            }

            override fun saveCallResult(item: Repo?) {
                repoDao.insert(item!!)
            }

            override fun createCall(): LiveData<ApiResponse<Repo>> {
                return githubService.getRepo(owner, name)
            }
        }.asLiveData()
    }

    fun loadContributors(owner: String, name: String): LiveData<Resource<List<Contributor>>>{
        return object : NetworkBoundResource<List<Contributor>, List<Contributor>>(appExecutors) {
            override fun shouldFetch(data: List<Contributor>?): Boolean {
                return data == null|| data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Contributor>> {
                return repoDao.loadContributors(owner, name)
            }

            override fun saveCallResult(item: List<Contributor>?) {
                item!!.forEach {
                    it.repoName = name
                    it.repoOwner = owner
                }
                db.beginTransaction()
                try {
                    repoDao.creatreRepoIfNotExists(Repo(Repo.UNKNOWN_ID, name, "$owner/$name", "",
                        0, Repo.Owner(owner, null)))
                    repoDao.insertContributors(item)
                    db.setTransactionSuccessful()
                }finally {
                    db.endTransaction()
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<Contributor>>> {
                return githubService.getContributors(owner, name)
            }
        }.asLiveData()
    }

    fun searchNextPage(query: String): LiveData<Resource<Boolean>> {
        val fetchNextSearchPageTask = FetchNextSearchPageTask(
            query, githubService, db
        )
        appExecutors.networkIO().execute(fetchNextSearchPageTask)
        return fetchNextSearchPageTask.getLiveData()
    }


    fun search(query: String): LiveData<Resource<List<Repo>>> {
        return object : NetworkBoundResource<List<Repo>, RepoSearchResponse>(appExecutors) {

            override fun shouldFetch(data: List<Repo>?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<List<Repo>> {
                return Transformations.switchMap(
                    repoDao.search(query)
                ) { searchData ->
                    if (searchData == null) {
                        AbsentLiveData.create()
                    } else {
                        repoDao.loadOrdered(searchData.repoIds!!)
                    }
                }
            }

            override fun saveCallResult(item: RepoSearchResponse?) {
                val repoIds = item!!.getRepoIds()
                val repoSearchResult = RepoSearchResult(
                    query, repoIds, item.total, item.nextPage
                )
                db.beginTransaction()
                try {
                    repoDao.insertRepos(item.items!!)
                    repoDao.insert(repoSearchResult)
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
            }

            override fun createCall(): LiveData<ApiResponse<RepoSearchResponse>> {
                return githubService.searchRepos(query)
            }


            override fun processResponse(response: ApiResponse<RepoSearchResponse>): RepoSearchResponse? {
                val body = response.body
                if (body != null) {
                    body.nextPage = response.nextPage
                }
                return body
            }
        }.asLiveData()
    }
}
























