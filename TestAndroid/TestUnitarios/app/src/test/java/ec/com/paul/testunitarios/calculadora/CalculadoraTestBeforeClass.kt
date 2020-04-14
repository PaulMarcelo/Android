package ec.com.paul.testunitarios.calculadora

import junit.framework.Assert.assertEquals
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Before
import org.junit.Test
import org.junit.After

/**
 * Created by Paul Yaguachi on 24/8/2019.
 * Paul Local
 */
class CalculadoraTestBeforeClass {
    private var calculadoraBefore: Calculadora? = null
    private var numeroBefore: Int = 0

    companion object {
        var caculadoraBeforeClass: Calculadora? = null
        var numeroBeforeClass: Int = 0

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            caculadoraBeforeClass = Calculadora()
            numeroBeforeClass = 3
            println("Se ha realiazo el beforeClass()")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            caculadoraBeforeClass = null
            numeroBeforeClass = 0
            println("Se ha realiado afterClass()")
        }
    }

    @Before
    fun before() {
        calculadoraBefore = Calculadora()
        numeroBefore = 3
        println("Se ha realiazo el before()")
    }

    @Test
    fun pruebaBeforeVsBeforeClass() {
        numeroBeforeClass += 3
        numeroBefore += 3
        assertEquals("numeroBeforeClass: ", 6, numeroBeforeClass)
        assertEquals("numeroBefore: ", 6, numeroBefore)
        println("Se ha realiazo el pruebaBeforeVsBeforeClass()")
    }

    @Test
    fun pruebaBeforeVsBeforeClass2() {
        numeroBeforeClass += 3
        numeroBefore += 3
        assertEquals("numeroBeforeClass: ", 9, numeroBeforeClass)
        assertEquals("numeroBefore: ", 6, numeroBefore)
        println("Se ha realiazo el pruebaBeforeVsBeforeClass2()")
    }


    @After
    fun after() {
        calculadoraBefore = null
        numeroBefore = 0
        println("Se ha realiado after()")
    }
}