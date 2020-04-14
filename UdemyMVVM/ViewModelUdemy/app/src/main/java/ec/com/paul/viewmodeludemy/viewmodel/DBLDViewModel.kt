package ec.com.paul.viewmodeludemy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ec.com.paul.viewmodeludemy.util.User
import androidx.lifecycle.LiveData


/**
 * Created by Paul Yaguachi on 15/9/2019.
 * Paul Local
 */
class DBLDViewModel : ViewModel() {

    private val user: MutableLiveData<User> = MutableLiveData()
    private var visible: MutableLiveData<Boolean> = MutableLiveData()
    private var size: MutableLiveData<Float> = MutableLiveData()

    init {
        visible.value = true
        size.value = 14f
    }

    fun getVisible(): MutableLiveData<Boolean> {
        return visible
    }

    fun setVisible(visible: Boolean) {
        this.visible.value = visible
    }

    fun getUser(): LiveData<User> {
        return user
    }

    fun setUser(user: User) {
        this.user.value = user
    }

    fun getSize(): LiveData<Float> {
        return size
    }

    fun setSize(size: Float) {
        this.size.value = size
    }

    fun updateUser() {
        val user = User()
        user.nombre = "Laura"
        user.edad = "23"
        this.user.value = user
    }


    fun changeVisibility() {
        if (visible.value == true) {
            visible.setValue(false)
        } else {
            visible.setValue(true)
        }
        size.value = size.value!!.toFloat() + 5L
    }

}