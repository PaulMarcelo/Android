package ec.com.paul.testunitarios.mockitomvp

/**
 * Created by Paul Yaguachi on 26/8/2019.
 * Paul Local
 */
class LoginModel(val presenter: Login.Presenter) : Login.Model {


    override fun validadUser(user: String, password: String) {
        if (user == "paul" && password == "1234")
            presenter.usuarioValido()
        else
            presenter.error()
    }


}