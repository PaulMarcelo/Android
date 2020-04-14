package ec.com.paul.espressoudemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_toast.*

class ToastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)

        btToast.setOnClickListener {
            Toast.makeText(applicationContext, "Testeando un Toast", Toast.LENGTH_LONG).show()
        }
    }


}
