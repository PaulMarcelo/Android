package ec.com.paul.testunitarios.calculadora

import java.lang.ArithmeticException

/**
 * Created by Paul Yaguachi on 20/8/2019.
 * Paul Local
 */
class Calculadora {
    /*
    //METODO////////////////////////////////////////ESPECIFICACIOM//////////////////////////////////////////////////////////////////////////////
    int sumar(                  |Este método devuelve un int resultado de la suma de numero 1 y numero2
        int numero1,            |
        int numero2)            |
    ------------------------------------------------------------------------------------------------------------------------
    int restar(                 |Este método devuelve un int resultado de la resta de numero 1 y numero2
        int numero1,            |
        int numero2)            |
    ------------------------------------------------------------------------------------------------------------------------
     */

    var resultado = 0

    fun sumar(numero1: Int, numero2: Int): Int {
        resultado = numero1 + numero2
        return resultado
    }

    fun restar(numero1: Int, numero2: Int): Int {
        resultado = numero1 - numero2
        return resultado
    }

    fun dividir(numero1: Int, numero2: Int): Int {
        resultado = numero1 / numero2
        return resultado
    }

    fun dividirPorCero(numero1: Int, numero2: Int): Int {
        if (numero2 == 0) {
            throw  ArithmeticException("No se puede dividir por cero")
        }
        resultado = numero1 / numero2
        return resultado
    }

    fun operacionLargaDuracion() {
        try {
            Thread.sleep(599)
        } catch (e: Exception) {
            //Does nothing
        }
    }

}