package ec.com.paul.viewmodeludemy.viewmodel

import androidx.lifecycle.ViewModel
import ec.com.paul.viewmodeludemy.util.User

/**
 * Created by Paul Yaguachi on 14/9/2019.
 * Paul Local
 */
class UserViewModel : ViewModel() {

    var userList: MutableList<User> = arrayListOf()

    fun addUser(user: User) {
        userList.add(user)
    }
}