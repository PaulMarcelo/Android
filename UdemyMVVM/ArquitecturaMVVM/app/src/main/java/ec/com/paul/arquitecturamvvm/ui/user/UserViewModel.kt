package ec.com.paul.arquitecturamvvm.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ec.com.paul.arquitecturamvvm.modelo.Repo
import ec.com.paul.arquitecturamvvm.modelo.User
import ec.com.paul.arquitecturamvvm.repository.Resource
import ec.com.paul.arquitecturamvvm.repository.RepoRepository
import ec.com.paul.arquitecturamvvm.repository.UserRepository
import javax.inject.Inject
import ec.com.paul.arquitecturamvvm.utils.AbsentLiveData
import java.util.*


/**
 * Created by Paul Yaguachi on 23/11/2019.
 * Paul Local
 */
class UserViewModel @Inject
constructor(userRepository: UserRepository, repoRepository: RepoRepository) : ViewModel() {

    internal val login = MutableLiveData<String>()

    val repositories: LiveData<Resource<List<Repo>>>
    val user: LiveData<Resource<User>>

    init {
        user = Transformations.switchMap(login) { login ->
            if (login == null) {
                AbsentLiveData.create()
            } else {
                userRepository.loadUser(login)
            }
        }

        repositories = Transformations.switchMap(login) { login ->
            if (login == null) {
                AbsentLiveData.create()
            } else {
                repoRepository.loadRepos(login)
            }
        }
    }

    fun setLogin(login: String) {
        if (Objects.equals(this.login.value, login)) {
            return
        }
        this.login.value = login
    }

    fun retry() {
        if (this.login.value != null) {
            this.login.value = this.login.value
        }
    }
}