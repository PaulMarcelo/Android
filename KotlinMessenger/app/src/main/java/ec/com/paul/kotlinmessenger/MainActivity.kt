package ec.com.paul.kotlinmessenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val CONSST: String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button.setOnClickListener {
            val email = email_edittext_register.text.toString()
            val password = email_edittext_register.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            Log.d("MainActivity","Email is: "+email)
            Log.d("MainActivity","Password: "+password)

            //Firebase Authentication


        }

        ready_have_account_text_view.setOnClickListener {
            Log.d("MainAc","Password: ")

            // launch th login activity
            val  intent = Intent(this,  LoginActivity::class.java)
            startActivity(intent)

        }


    }
}
