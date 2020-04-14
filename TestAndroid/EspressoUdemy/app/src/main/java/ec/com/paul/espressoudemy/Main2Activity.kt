package ec.com.paul.espressoudemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        this.setUpView()
    }

    private fun setUpView() {
        this.activityMain2BtReset.setOnClickListener {
            this.activityMain2TvMiFirstTest.text = "Reset Text"
        }
        this.activityMain2btChange.setOnClickListener {
            this.activityMain2TvMiFirstTest.text = this.activityMain2EtMiFirstEditText.text
        }
        this.activityMain2BtGoToLogin.setOnClickListener {
            //does nothing
        }
    }


}
