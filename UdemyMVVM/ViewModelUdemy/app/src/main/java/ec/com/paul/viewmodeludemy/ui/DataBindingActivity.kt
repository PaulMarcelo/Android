package ec.com.paul.viewmodeludemy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ec.com.paul.viewmodeludemy.R
import ec.com.paul.viewmodeludemy.databinding.ActivityDataBindingBinding
import ec.com.paul.viewmodeludemy.util.User

class DataBindingActivity : AppCompatActivity() {

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this, R.layout.activity_data_binding)
        user = User()
        user.nombre = "Marcelo"
        user.edad = "31"
        binding.user = user
    }
}
