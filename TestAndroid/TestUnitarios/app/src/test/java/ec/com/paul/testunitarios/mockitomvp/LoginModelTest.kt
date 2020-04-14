package ec.com.paul.testunitarios.mockitomvp

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Before


/**
 * Created by Paul Yaguachi on 1/9/2019.
 * Paul Local
 */
@RunWith(MockitoJUnitRunner::class)
class LoginModelTest {


    @Mock
    private val presenter: Login.Presenter? = null

    private var model: LoginModel? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        model = LoginModel(presenter!!)
    }

    @Test
    @Throws(Exception::class)
    fun shouldSuccessWithValidUserAndPasswordTest() {
        //3.- Nos permite ejecutar un metodo
        model!!.validadUser("alberto", "1234")
        //4.- Verificamos si han realizado las llamadas correctas (en este caso si se ha llamado a usuarioValido() en el presenter)
        //al ejecutar el metodo validaUser del modelo.
        //Es decir estamos enviado el usuario y password correcto, por lo tanto tiene que llamar a este metodo.
        verify(presenter!!).usuarioValido()
    }


    @Test
    @Throws(Exception::class)
    fun shouldNotSuccessWithValidUserAndPasswordTest() {
        //3.- Nos permite ejecutar un metodo
        model!!.validadUser("maria", "1234")
        //4.- Verificamos si han realizado las llamadas correctas (en este caso si se ha llamado a usuarioValido() en el presenter)
        //al ejecutar el metodo validaUser del modelo.
        //Es decir estamos enviado el usuario y password correcto, por lo tanto tiene que llamar a este metodo.
        verify(presenter!!).error()
    }
}