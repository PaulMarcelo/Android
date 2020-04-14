package ec.com.paul.viewmodeludemy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import ec.com.paul.viewmodeludemy.R
import ec.com.paul.viewmodeludemy.util.Sumar
import ec.com.paul.viewmodeludemy.viewmodel.SumarViewModel
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {

    private var resultado: Int = 0
    private lateinit var sumarViewModel: SumarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        setUpView()
    }

    private fun setUpView() {
        sumarViewModel = ViewModelProviders.of(this).get(SumarViewModel::class.java)
        btSumar.setOnClickListener {
            resultado = Sumar.sumar(resultado)
            tvSumar.text = "$resultado"

            sumarViewModel.resultado = Sumar.sumar(sumarViewModel.resultado)
            tvSumarViewModel.text = "${sumarViewModel.resultado}"

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG1", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG1", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG1", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG1", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG1", "onDestroy()")
    }

}
