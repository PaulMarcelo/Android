package ec.com.paul.testunitarios.mockitomvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ec.com.paul.testunitarios.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), Login.View {


    lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
    }

    override fun usuarioValido() {
//        startActivity(Intent(LoginActivity.this ))
    }

    override fun errror() {
        Log.d("TAG1", "usuario no es valido")
    }

    override fun getUserName(): String {
        return etUser.text.toString()
    }

    override fun getPassword(): String {
        return etPassword.text.toString()
    }

}
