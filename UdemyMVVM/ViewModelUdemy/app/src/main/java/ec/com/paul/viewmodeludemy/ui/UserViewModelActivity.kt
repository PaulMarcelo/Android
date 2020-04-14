package ec.com.paul.viewmodeludemy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ec.com.paul.viewmodeludemy.R
import ec.com.paul.viewmodeludemy.util.User
import ec.com.paul.viewmodeludemy.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user_view_model.*

class UserViewModelActivity : AppCompatActivity() {

    private lateinit var userList: MutableList<User>
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_view_model)
        setUpView()
    }

    private fun setUpView() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userList = arrayListOf()

        btSalvar.setOnClickListener {
            val user = User(etEdad.text.toString(), etNombre.text.toString())
            userList.add(user)

            userViewModel.addUser(user)
        }

        btVer.setOnClickListener {
            var texto = ""
            userList.forEach {
                texto += it.nombre + " " + it.edad + "\n"
            }
            tvUser.text = texto

            texto = ""
            userViewModel.userList.forEach {
                texto += it.nombre + " " + it.edad + "\n"
            }
            tvUserViewModel.text = texto
        }
    }
}
