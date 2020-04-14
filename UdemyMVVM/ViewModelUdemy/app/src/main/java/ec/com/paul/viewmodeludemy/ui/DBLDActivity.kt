package ec.com.paul.viewmodeludemy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ec.com.paul.viewmodeludemy.R
import ec.com.paul.viewmodeludemy.databinding.ActivityDataBindingBinding
import ec.com.paul.viewmodeludemy.databinding.ActivityDbldBinding
import ec.com.paul.viewmodeludemy.util.User
import ec.com.paul.viewmodeludemy.viewmodel.DBLDViewModel

class DBLDActivity : AppCompatActivity() {

    lateinit var viewmodel: DBLDViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDbldBinding>(this, R.layout.activity_dbld)
        binding.lifecycleOwner = this
        viewmodel = ViewModelProviders.of(this).get(DBLDViewModel::class.java)
        binding.viewModel = viewmodel

        val user = User()
        user.nombre = "Alberto"
        user.edad = "30"
        viewmodel.setUser(user)
    }

}
