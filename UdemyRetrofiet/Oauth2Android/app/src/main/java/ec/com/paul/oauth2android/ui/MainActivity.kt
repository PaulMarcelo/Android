package ec.com.paul.oauth2android.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import ec.com.paul.oauth2android.R
import ec.com.paul.oauth2android.api.WebServiceOAuth
import ec.com.paul.oauth2android.api.WebServiceOAuthApi
import ec.com.paul.oauth2android.model.Token
import ec.com.paul.oauth2android.model.User
import ec.com.paul.oauth2android.shared_pref.TokenManager
import ec.com.paul.oauth2android.shared_pref.TokenManager.Companion.SHARED_PREFERENCES
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import ec.com.paul.oauth2android.model.MovimientoBancario


class MainActivity : AppCompatActivity() {

    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        this.tokenManager = TokenManager.getIntance(getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE))
        btVerTodosUsuarios.setOnClickListener {
            verTodosUsuarios()
        }
        btCrearUsuario.setOnClickListener {
            crearUsuario()
        }
        btObtenerToken.setOnClickListener {
            obtenerToken()
        }
        btVerTodosLosMovimientosBancarios.setOnClickListener {
            verMovimientosBancarios()
        }
    }

    private fun verTodosUsuarios() {
        val call = WebServiceOAuth
            .getInstance()
            .createService(WebServiceOAuthApi::class.java)
            .obtenerUsuarios()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                //Does nothing
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.code() == 200) {
                    for (i in 0 until response.body()!!.size) {
                        Log.d("TAG1", "Username: " + response.body()!![i].userName)
                    }
                } else {
                    Log.d("TAG1", "Error")
                }
            }
        })
    }

    private fun crearUsuario() {
        val user = User()
        user.userName = etUsername.text.toString()
        user.password = etPassword.text.toString()

        val call = WebServiceOAuth
            .getInstance()
            .createService(WebServiceOAuthApi::class.java)
            .crearUsuario(user)
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                //Does nothing
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 201) {
                    Log.d("TAG1", "Usuario guardado")
                } else {
                    Log.d("TAG1", "Error")
                }
            }
        })

    }

    private fun obtenerToken() {
        val authHeader = "Basic " + Base64.encodeToString(("androidApp:123").toByteArray(), Base64.NO_WRAP)

        val call = WebServiceOAuth
            .getInstance()
            .createService(WebServiceOAuthApi::class.java)
            .obtenerToken(
                authHeader, etUsername.text.toString(), etPassword.text.toString(), "password"
            )
        call.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                //Does nothing
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.code() == 200) {
                    Log.d(
                        "TAG1", "Access Token: " + response.body()!!.accessToken
                                + " Refresh Token: " + response.body()!!.refreshToken
                    )

                    val token = response.body()
                    tokenManager.saveToken(token!!)
                    startActivity(Intent(applicationContext, LogeadoActivity::class.java))
                } else {
                    Log.d("TAG1", "Error")
                }
            }
        })
    }

    private fun verMovimientosBancarios() {
        val call = WebServiceOAuth
            .getInstance()
            .createService(WebServiceOAuthApi::class.java)
            .obtenerMovimientos("Bearer " + tokenManager.getToken().accessToken)
        call.enqueue(object : Callback<List<MovimientoBancario>> {
            override fun onFailure(call: Call<List<MovimientoBancario>>, t: Throwable) {
                //Does nothing
            }

            override fun onResponse(
                call: Call<List<MovimientoBancario>>,
                response: Response<List<MovimientoBancario>>
            ) {
                if (response.code() == 200) {
                    for (i in 0 until response.body()!!.size) {
                        Log.d(
                            "TAG1", "UserID: " + response.body()!![i].userId +
                                    " Importe: " + response.body()!![i].importe +
                                    " Nombre: " + response.body()!![i].name
                        )
                    }
                } else {
                    Log.d("TAG1", "Error")
                }
            }
        })
    }
}
