package ec.com.paul.testunitarios.mockitomvp

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import java.lang.RuntimeException
import org.mockito.Mockito.`when`
import org.mockito.Spy
import java.nio.file.Files.size
import java.nio.file.Files.size










/**
 * Created by Paul Yaguachi on 31/8/2019.
 * Paul Local
 */

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    lateinit var view: Login.View

    @Mock
    lateinit var model: Login.Model

    lateinit var presenter: LoginPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = LoginPresenter(view)
    }


    @Test
    @Throws(Exception::class)
    fun shouldShowErrorMessageWhenUserOrPasswordIsWrongTest() {
        `when`(view.getUserName()).thenReturn("maria")
        `when`(view.getPassword()).thenReturn("1234")
        presenter.validadUser(view.getUserName(), view.getPassword())
        verify(view).errror()
    }

    @Test
    @Throws(Exception::class)
    fun shouldCallusuarioValidoWhenUserAndPasswordAreRightTest() {
        `when`(view.getUserName()).thenReturn("paul")
        `when`(view.getPassword()).thenReturn("1234")
        assertEquals("correcto", "paul", view.getUserName())
        presenter.validadUser(view.getUserName(), view.getPassword())
        verify(view).usuarioValido()
    }


    @Test
    @Throws(Exception::class)
    fun shouldThrowingException() {
        `when`(view.getUserName()).thenThrow(RuntimeException::class.java)
        try {
            view.getUserName()
        } catch (e: RuntimeException) {
            //Does thing
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldCallusuarioValidoWhenUserAndPasswordAreRightDoReturnTest() {
        doReturn("alberto").`when`(view).getUserName()
        Assert.assertEquals("alberto", view.getUserName())
    }

    @Test
    @Throws(Exception::class)
    fun shouldCallusuarioValidoWhenUserAndPasswordAreRightTest2() {
        //2.- Nos permite programar un comprortamiento
        `when`(view.getUserName()).thenReturn("paul")
        `when`(view.getPassword()).thenReturn("1234")
        Assert.assertEquals("paulnetfli", view.getUserName())
        //3.- Ejecutamos un metodo
        presenter.validadUser(view.getUserName(), view.getPassword())
        //4.- Verificar que la vista ejecuta el metodo Error
        verify(view).usuarioValido()

        verify(view, times(1)).usuarioValido()
        // verify(view, never()).usuarioValido();
        verify(view, atLeast(1)).usuarioValido()
        verify(view, atMost(7)).usuarioValido()
        //verify(view, only()).usuarioValido();

    }


    @Mock
    var mockList: MutableList<String> = ArrayList()
    @Spy
    var spyList: MutableList<String> = ArrayList()

    @Test
    @Throws(Exception::class)
    fun spyTest() {
        spyList.add("one")
        spyList.add("two")

        verify(spyList).add("one")
        verify(spyList).add("two")

        Assert.assertEquals(2, spyList.size)
    }

    @Test
    @Throws(Exception::class)
    fun mockTest() {
        mockList.add("one")
        mockList.add("two")

        verify(mockList).add("one")
        verify(mockList).add("two")

        Assert.assertEquals(2, spyList.size)
    }
}