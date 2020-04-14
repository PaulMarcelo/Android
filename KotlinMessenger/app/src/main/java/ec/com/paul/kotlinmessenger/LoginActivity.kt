package ec.com.paul.kotlinmessenger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Paul Yaguachi on 13/1/2019.
 * Paul Local
 */

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()
            Log.d("Login", "Attempt login with email/pw: $email/***")
        }

        back_to_register_textview.setOnClickListener {
            finish()
        }
    }


}