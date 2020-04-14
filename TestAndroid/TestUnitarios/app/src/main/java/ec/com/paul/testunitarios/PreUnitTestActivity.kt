package ec.com.paul.testunitarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ec.com.paul.testunitarios.calculadora.CalculadoraPreUnitTest
import kotlinx.android.synthetic.main.activity_pre_unit_test.*

class PreUnitTestActivity : AppCompatActivity() {

    var resultado = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_unit_test)

        resultado = CalculadoraPreUnitTest.sumar(10, 20)

        tvResultado.text = "" + resultado


        if (resultado == 30) {
            Log.d("TAG!", "Resultado OK")
        } else {
            Log.d("TAG!", "Resultado Fail")
        }
    }
}
