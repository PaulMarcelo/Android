package ec.com.paul.viewmodeludemy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ec.com.paul.viewmodeludemy.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        btViewModel.setOnClickListener {
            startActivity(Intent(applicationContext, ViewModelActivity::class.java))
        }
        btUserViewModel.setOnClickListener {
            startActivity(Intent(applicationContext, UserViewModelActivity::class.java))
        }
        btLiveData.setOnClickListener {
            startActivity(Intent(applicationContext, LiveDataActivity::class.java))
        }
        btDataBinding.setOnClickListener {
            startActivity(Intent(applicationContext, DataBindingActivity::class.java))
        }
        btDBLABinding.setOnClickListener {
            startActivity(Intent(applicationContext, DBLDActivity::class.java))
        }
    }


}
