package ec.com.paul.arquitecturamvvm.repository

import androidx.annotation.MainThread
import androidx.lifecycle.MediatorLiveData
import ec.com.paul.arquitecturamvvm.AppExecutors
import ec.com.paul.arquitecturamvvm.api.ApiResponse
import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread
import java.util.*


/**
 * Created by Paul Yaguachi on 17/10/2019.
 * Paul Local
 */
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
internal constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (this@NetworkBoundResource.shouldFetch(data)) {
                this@NetworkBoundResource.fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData: ResultType ->
                    this@NetworkBoundResource.setValue(
                        Resource.success(
                            newData
                        )
                    )
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (!Objects.equals(result.value, newValue)) {
            result.value = newValue
        }
    }


    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData -> this@NetworkBoundResource.setValue(Resource.loading(newData)) }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            if (response.isSuccessful) {
                appExecutors.diskIO().execute {
                    this@NetworkBoundResource.saveCallResult(this@NetworkBoundResource.processResponse(response))
                    appExecutors.mainThread().execute {
                        result.addSource(this@NetworkBoundResource.loadFromDb()) { newData ->
                            this@NetworkBoundResource.setValue(Resource.success(newData))
                        }
                    }
                }
            } else {
                onFetchFailed()
                result.addSource(dbSource) { newData -> setValue(Resource.error(response.errorMessage, newData)) }
            }
        }
    }

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected open fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    @WorkerThread
    protected open fun processResponse(response: ApiResponse<RequestType>): RequestType? {
        return response.body
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
