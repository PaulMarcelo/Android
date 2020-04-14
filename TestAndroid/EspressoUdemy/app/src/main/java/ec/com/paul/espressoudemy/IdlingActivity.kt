package ec.com.paul.espressoudemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ec.com.paul.espressoudemy.utils.Callback
import ec.com.paul.espressoudemy.utils.EspressoIdlingResource
import ec.com.paul.espressoudemy.utils.WebServiceMock
import kotlinx.android.synthetic.main.activity_idling.*

class IdlingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idling)
        setUpView()
    }

    private fun setUpView() {
        idlingActivityBt.setOnClickListener {
            cargarDatos()
        }

        idlingActivityBtGoToSpinner.setOnClickListener {
            // startActivity(new Intent(getApplicationContext(), SpinnerActivity.class));
        }
    }

    private fun cargarDatos() {
        EspressoIdlingResource.increment()
        val webServiceMock = WebServiceMock()
        webServiceMock.login("Paul", "1234", object : Callback {
            override fun onSuccess(response: String) {
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
                idlingActivityTv.text = "Paul"
            }

            override fun onFailure(response: String) {

            }

        })
    }
}
