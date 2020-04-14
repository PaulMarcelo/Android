package ec.com.paul.arquitecturamvvm.repository

import ec.com.paul.arquitecturamvvm.AppExecutors
import ec.com.paul.arquitecturamvvm.api.WebServiceApi
import ec.com.paul.arquitecturamvvm.db.UserDao
import javax.inject.Inject
import javax.inject.Singleton
import ec.com.paul.arquitecturamvvm.modelo.User
import ec.com.paul.arquitecturamvvm.api.ApiResponse
import androidx.lifecycle.LiveData


/**
 * Created by Paul Yaguachi on 18/10/2019.
 * Paul Local
 */
@Singleton
class UserRepository @Inject
internal constructor(
    private val appExecutors: AppExecutors,
    private val userDao: UserDao,
    private val githubService: WebServiceApi
) {

    fun loadUser(login: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun shouldFetch(data: User?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<User> {
                return userDao.findByLogin(login)
            }

            override fun saveCallResult(item: User?) {
                userDao.insert(item!!)
            }

            override fun createCall(): LiveData<ApiResponse<User>> {
                return githubService.getUser(login)
            }
        }.asLiveData()
    }
}