package ec.com.paul.viewmodeludemy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ec.com.paul.viewmodeludemy.R
import ec.com.paul.viewmodeludemy.util.User
import ec.com.paul.viewmodeludemy.viewmodel.LiveDataViewModel
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {
    private var numero: Int = 0
    private lateinit var liveDataViewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        setUpView()
    }

    private fun setUpView() {
        liveDataViewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
        btSave.setOnClickListener {
            if (numero == 0) {
                val user = User()
                user.nombre = "Alberto"
                user.edad = "30"
                Log.d("TAG1", "numero0")
                liveDataViewModel.addUser(user)
            }
            if (numero == 1) {
                val user = User()
                user.nombre = "Maria"
                user.edad = "23"
                liveDataViewModel.addUser(user)
            }
            if (numero == 2) {
                val user = User()
                user.nombre = "Manuel"
                user.edad = "40"
                liveDataViewModel.addUser(user)
            }
            numero++
        }


        val listObserver = Observer<List<User>> {
            var texto = ""
            it.forEach { item ->
                texto += item.nombre + " " + item.edad + "\n"
            }
            tvLiveData.text = texto
        }

        liveDataViewModel.getUserList().observe(this, listObserver)
    }
}
