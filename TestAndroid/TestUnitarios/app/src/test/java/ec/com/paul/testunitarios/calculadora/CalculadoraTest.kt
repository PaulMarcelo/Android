package ec.com.paul.testunitarios.calculadora

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import java.lang.ArithmeticException

/**
 * Created by Paul Yaguachi on 20/8/2019.
 * Paul Local
 */
class CalculadoraTest {

    var calculadoraNull: Calculadora? = null
    var calculadora: Calculadora? = null

    @Before
    fun setUp() {
        calculadora = Calculadora()
        println("start setUp")
    }

    @Test
    fun calculadoraNotNullTest() {
        assertNotNull("Calculadora debe ser null", calculadora)
        println("start calculadoraNotNullTest")
    }

    @Test
    fun calculadoraNullTest() {
        //assertNotNull("Calculadora debe ser null", calculadora)
        assertNull("Calculadora debe ser null", calculadoraNull)
        println("start calculadoraNullTest")
    }


    @After
    fun tearDown() {
        calculadora = null
        println("start tearDown")
    }


    @Test
    fun sumarTestExplicado() {
        val calculadoraPrueba = Calculadora()
        val resultadoEsperado = 30
        var resultadoActual = 0
        resultadoActual = calculadoraPrueba.sumar(10, 20)
        assertEquals(resultadoEsperado, resultadoActual)
        assertEquals(30, calculadoraPrueba.sumar(10, 20))
        println("start sumarTestExplicado")
    }

    @Test
    fun ejemploAssert() {

    }

    /*
  Método a Probar                 |      Entrada      |       Salida Experarada
  sumar(int a, int b)             |a = 10, b=20       |30
  sumar(int a, int b)             |a = 7, b=4         |11
  restar(int a, int b)            |a = 7, b=4         |3
  restar(int a, int b)            |a = 10, b=20       |-10
   */

    @Test
    @Throws
    fun sumar() {
        val a = 10
        val b = 20
        val resultadoEsperado = 30
        val resultado: Int
        resultado = calculadora!!.sumar(a, b)
        assertEquals(resultadoEsperado, resultado)
        println("start sumar")
    }


    @Test
    fun sumar_ValidInput_ValidExpected_Test() {
        assertEquals(11, calculadora!!.sumar(7, 4))
        println("start sumar_ValidInput_ValidExpected_Test")
    }

    @Test
    fun restar_ValidInput_ValidExpedted_Test() {
        assertEquals(3, calculadora!!.restar(7, 4))
        println("start restar_ValidInput_ValidExpedted_Test")
    }

    @Test
    fun restar_ValidInput_ValidNegativeExpedted_Test() {
        assertEquals(-10, calculadora!!.restar(10, 20))
        println("start restar_ValidInput_ValidNegativeExpedted_Test")
    }


    @Test
    fun dividir_ValidInput_ValidExpedted_Test() {
        assertEquals(2, calculadora!!.dividir(4, 2))
        println("start dividir_ValidInput_ValidExpedted_Test")
    }

    @Test
    fun dividir_NotValidInput_Test() {
        calculadora!!.dividir(4, 0)
        println("start dividir_NotValidInput_Test")
        fail("Fallo detectado Manualmente - No se puede dividir por Cero")
    }

    @Test(expected = ArithmeticException::class)
    fun dividirPorCero_Novalidinput_ExpectedExceptionTest() {
        calculadora!!.dividirPorCero(5, 0)
        println("start dividirPorCero_Novalidinput_ExpectedExceptionTest")
    }

    @Test(timeout = 600)
    fun operacionLargaDuracion_TimeOut_Test() {
        calculadora!!.operacionLargaDuracion()
    }

    @Ignore("Metodo no listo. Ignorar por ahora. Esperando solucionar la división por cero")
    @Test
    fun dividir_NotValidInput_Ignore_Test(){
        assertEquals(2, calculadora!!.dividir(5,0));
        println("Se ha ejecutado el test operacionLargaDuracion_TimeOut_Test()")
    }

}