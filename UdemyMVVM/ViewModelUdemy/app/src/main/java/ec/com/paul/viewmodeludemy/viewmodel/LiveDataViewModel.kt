package ec.com.paul.viewmodeludemy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ec.com.paul.viewmodeludemy.util.User

/**
 * Created by Paul Yaguachi on 14/9/2019.
 * Paul Local
 */
class LiveDataViewModel : ViewModel() {
    var userListLiveData: MutableLiveData<List<User>>? = null
    var userList: MutableList<User>? = null

    fun getUserList(): LiveData<List<User>> {
        if (userListLiveData == null) {
            userListLiveData = MutableLiveData()
            userList = arrayListOf()
        }
        return userListLiveData!!
    }

    fun addUser(user:User){
        userList!!.add(user)
        userListLiveData!!.value = userList
        userListLiveData!!.postValue(userList) // si se necesita actualizar algun valor desde un hilo de background
    }

}