package ec.com.paul.testunitarios.calculadora

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by Paul Yaguachi on 24/8/2019.
 * Paul Local
 */

@RunWith(value = Suite::class)
@Suite.SuiteClasses(
    CalculadoraParametrizadaTest::class,
    CalculadoraTest::class
)
class CalculadoraSuiteTest