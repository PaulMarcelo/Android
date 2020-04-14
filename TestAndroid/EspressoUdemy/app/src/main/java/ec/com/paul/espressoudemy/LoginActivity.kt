package ec.com.paul.espressoudemy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.setUpView()
    }

    private fun setUpView() {
        this.loginActivityBtLogin.setOnClickListener {
            this.loginActivityTvError.visibility = View.GONE
            val userName: String? = this.loginActivityEtUserName.text.toString()
            val passwird: String? = this.loginActivityEtPassword.text.toString()
            if (userName != null && userName.length < 4) {
                this.loginActivityTvError.text = getString(R.string.login_activity_username_error)
                this.loginActivityTvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if (passwird != null && passwird.length < 4) {
                this.loginActivityTvError.text = getString(R.string.login_activity_password_error)
                this.loginActivityTvError.visibility = View.VISIBLE
                return@setOnClickListener
            }
            this.doLoginBackEnd(userName ?: "", passwird ?: "")

        }
    }


    fun doLoginBackEnd(userName: String, password: String) {
        if (userName == "Alberto" && password == "1234") {
            val intent = Intent(applicationContext, RecyclerViewActivity::class.java)
            intent.putExtra("USER", userName)
            startActivity(intent)
        }
    }

}
