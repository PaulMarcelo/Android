package ec.com.paul.testunitarios.mockitomvp

/**
 * Created by Paul Yaguachi on 26/8/2019.
 * Paul Local
 */
interface Login {
    interface View{
        fun usuarioValido()
        fun errror()
        fun getUserName(): String
        fun getPassword(): String
    }
    interface Presenter{
        fun validadUser(user:String, password:String)
        fun usuarioValido()
        fun error()
    }
    interface Model{
        fun validadUser(user:String, password:String)
    }
}