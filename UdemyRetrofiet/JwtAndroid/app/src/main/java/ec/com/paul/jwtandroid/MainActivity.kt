package ec.com.paul.jwtandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ec.com.paul.jwtandroid.api.WebServiceApi
import ec.com.paul.jwtandroid.api.WebServiceJWT
import ec.com.paul.jwtandroid.model.Login
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private  var token: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setUpView()

    }

    private fun setUpView() {
        this.btToken.setOnClickListener {
            this.obtenerToken()
        }
        this.btObtenerRecurso.setOnClickListener {
            this.obtenerRecursoConToken()
        }

        this.btSolicitudTokenErroneo.setOnClickListener {
            this.obtenerRecursoConTokenErroneo()
        }

    }

    private fun obtenerRecursoConTokenErroneo() {
        val tokenEroneo ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvIiwidXNlcklkIjoiMiIsInJvbGUiOiJBZG1pbiJ9.A6_dKk_GcyzsYIiHzzo8Q7nqeFePjera56KUoFVbNK4"
        val authHeader = "Bearer $tokenEroneo"
        val call = WebServiceJWT.getInstance().createService(WebServiceApi::class.java)
            .obtenerMovimientoBancario(authHeader)
        call.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.i("TAG", "NO HAY PROFESORES A MOSTRASR")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                when (response.code()) {
                    200 -> {
                        for (item in response.body() as List<String>) {
                            Log.i("TAG", "Movimiento Bancario: $item")
                        }
                    }
                    401 -> {
                        Log.i("TAG", "No autorizado")
                    }
                    else -> {
                        Log.i("TAG", "No hay token")
                    }
                }
            }
        })
    }

    private fun obtenerRecursoConToken() {
        val authHeader = "Bearer $token"
        val call = WebServiceJWT.getInstance().createService(WebServiceApi::class.java)
            .obtenerMovimientoBancario(authHeader)
        call.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.i("TAG", "NO HAY PROFESORES A MOSTRASR")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                when (response.code()) {
                    200 -> {
                        for (item in response.body() as List<String>) {
                            Log.i("TAG", "Movimiento Bancario: $item")
                        }
                    }
                    401 -> {
                        Log.i("TAG", "No autorizado")
                    }
                    else -> {
                        Log.i("TAG", "No hay token")
                    }
                }
            }
        })
    }

    private fun obtenerToken() {
        val login = Login()
        login.user = etUserName.text.toString()
        login.password = etPassword.text.toString()
        val call = WebServiceJWT.getInstance().createService(WebServiceApi::class.java)
            .obtenerToken(login)
        call.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                token = ""
                Log.i("TAG", "NO HAY PROFESORES A MOSTRASR")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                when (response.code()) {
                    200 -> {
                        token = response.body()!![0]
                        Log.i("TAG", "El token es: $token")
                    }
                    401 -> {
                        Log.i("TAG", "No autorizado")
                        token = ""
                    }
                    else -> {
                        Log.i("TAG", "No hay token")
                        token = ""
                    }
                }
            }
        })
    }
}
