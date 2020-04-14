package ec.com.paul.testunitarios.mockitomvp

/**
 * Created by Paul Yaguachi on 26/8/2019.
 * Paul Local
 */
class LoginPresenter(val view: Login.View?) : Login.Presenter {


    var model: Login.Model = LoginModel(this)

    override fun validadUser(user: String, password: String) {
        if (view != null) {
            model.validadUser(user, password)
        }
    }

    override fun usuarioValido() {
        if (view != null) {
            view.usuarioValido()
        }
    }

    override fun error() {
        if (view != null) {
            view.errror()
        }
    }
}