package ec.com.paul.espressoudemy


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_matcher.*


class CustomMatcherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_matcher)
        setUpView()
    }

    private fun setUpView() {
        customMatcherActivityBtLogin.setOnClickListener {

                val userName: String? = customMatcherActivityEtUserName.text.toString()
                val password: String? = customMatcherActivityEtPassword.text.toString()
                if (userName != null && userName.length < 4) {
                    customMatcherActivityEtUserName.error = getString(R.string.login_activity_username_error)
                    return@setOnClickListener
                }
                if (password != null && password.length < 4) {
                    customMatcherActivityEtPassword.error = getString(R.string.login_activity_password_error)
                    return@setOnClickListener
                }
                doLoginBackEnd(userName!!, password!!)

        }
    }

    private fun doLoginBackEnd(userName: String, password: String) {
        if (userName == "Alberto" && password == "1234") {
            val intent = Intent(applicationContext, SpinnerActivity::class.java)
            intent.putExtra("USER", userName)
            startActivity(intent)
        }
    }
}
