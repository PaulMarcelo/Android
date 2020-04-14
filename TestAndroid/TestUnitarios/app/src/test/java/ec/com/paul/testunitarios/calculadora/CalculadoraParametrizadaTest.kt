package ec.com.paul.testunitarios.calculadora

import junit.framework.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.Before
import org.junit.Test


/**
 * Created by Paul Yaguachi on 24/8/2019.
 * Paul Local
 */

@RunWith(value = Parameterized::class)
class CalculadoraParametrizadaTest constructor(a: Int, b: Int, c: Int) {
    private var numero1: Int = 0
    private var numero2: Int = 0
    private var resultadoExperado: Int = 0
    private var calculadora: Calculadora? = null

    /*
    Ejemplo en nuestra división queremos hacer 5 pruebas
    Positivo / Positivo = Positivo
    Positivo / Negativo = Negativo
    Negativo / Positivo = Negativo
    Negativo / Negativo = Positivo
    Positivo / 0 = Excepción
    Método a Probar                 |      Entrada      |       Salida Experarada
    dividir(int a, int b)           |a = 6, b=2         |3
    dividir(int a, int b)           |a = 6, b=-2        |-3
    dividir(int a, int b)           |a = -6, b=2        |-3
    dividir(int a, int b)           |a = -6, b=-2       |3
    dividir(int a, int b)           |a = -6, b=0        |Excepción
     */

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun obtenerDatosPruebaTest(): ArrayList<Any> {
            val list = ArrayList<Any>()
            list.add(arrayOf<Any>(6, 2, 3))
            list.add(arrayOf<Any>(6, -2, -3))
            list.add(arrayOf<Any>(-6, 2, -3))
            list.add(arrayOf<Any>(-6, -2, 3))
            list.add(arrayOf<Any>(6, 0, 3))
            return list
        }
    }


    init {
        numero1 = a
        numero2 = b
        resultadoExperado = c
    }

    @Before
    fun setUp() {
        calculadora = Calculadora()
    }

    @Test
    fun dividirTest() {
        assertEquals(resultadoExperado, calculadora!!.dividir(numero1, numero2))
    }

}