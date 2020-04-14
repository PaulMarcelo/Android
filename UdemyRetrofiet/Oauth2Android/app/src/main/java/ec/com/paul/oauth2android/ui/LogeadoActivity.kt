package ec.com.paul.oauth2android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ec.com.paul.oauth2android.R
import android.app.Activity
import android.content.Context
import android.util.Log
import ec.com.paul.oauth2android.api.WebServiceOAuth
import ec.com.paul.oauth2android.api.WebServiceOAuthApi
import ec.com.paul.oauth2android.model.MovimientoBancario
import ec.com.paul.oauth2android.model.User
import ec.com.paul.oauth2android.shared_pref.TokenManager
import ec.com.paul.oauth2android.shared_pref.TokenManager.Companion.SHARED_PREFERENCES
import kotlinx.android.synthetic.main.activity_logeado.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ec.com.paul.oauth2android.model.Token
import org.json.JSONObject


class LogeadoActivity : AppCompatActivity() {

    private var tokenManager: TokenManager? = null
    private var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logeado)
        setUpView()
        activity = this
    }


    private fun setUpView() {
        tokenManager = TokenManager.getIntance(getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE))
        btVerTodosLosMovimientosBancarios.setOnClickListener {
            verTodosMovimientos()
        }

        btVerTodosLosMovimientosBancariosUser.setOnClickListener {
            verTodosMovimientosUser()
        }
    }

    private fun verTodosMovimientos() {
        val call = WebServiceOAuth
            .getInstance()
            .createService(WebServiceOAuthApi::class.java)
            .obtenerMovimientos("Bearer " + tokenManager!!.getToken().accessToken)
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
                            "TAG1", "ID: ${response.body()!![i].userId} " +
                                    "Importe: ${response.body()!![i].userId}" +
                                    "Nombre: ${response.body()!![i].name}"
                        )
                    }
                } else {
                    Log.d("TAG1", "Error")
                }
            }
        })
    }

    private fun verTodosMovimientosUser() {
        val user = User()
        user.id = 3
        val call = WebServiceOAuth
            .getInstance()
            .createServiceWithOAuth2(WebServiceOAuthApi::class.java, tokenManager!!)
            .obtenerMovimientosUser(user)
        call.enqueue(object : Callback<List<MovimientoBancario>> {
            override fun onFailure(call: Call<List<MovimientoBancario>>, t: Throwable) {
                //Does nothing
            }

            override fun onResponse(
                call: Call<List<MovimientoBancario>>,
                response: Response<List<MovimientoBancario>>
            ) {
                when {
                    response.code() == 200 -> for (i in 0 until response.body()!!.size) {
                        Log.d(
                            "TAG1",
                            "UserID: ${response.body()!![i].userId} " +
                                    "Importe: ${response.body()!![i].importe} " +
                                    "Nombre: ${response.body()!![i].name}"
                        )
                    }
                    response.code() == 404 -> Log.d("TAG1", "No hay movimientos")
                    response.code() == 401 -> {
                        val newToken = Token()
                        newToken.accessToken = ""
                        newToken.refreshToken = ""
                        tokenManager!!.saveToken(newToken)
                        try {
                            val jsonObject = JSONObject(response.errorBody()!!.string())
                            Log.d("TAG1", "Invalid Access Token: " + jsonObject.getString("error"))
                        } catch (e: Exception) {
                            Log.d("TAG1", "Invalid Access Token: " + e.message)
                        }

                        activity!!.finish()
                    }
                    else -> Log.d("TAG1", "Error")
                }
            }
        })

    }

}
