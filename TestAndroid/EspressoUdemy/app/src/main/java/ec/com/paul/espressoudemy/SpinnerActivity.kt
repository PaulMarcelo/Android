package ec.com.paul.espressoudemy

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_spinner.*


class SpinnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        setUp()
        setUpDataSpinner()
    }

    private fun setUpDataSpinner() {
        val nombres: MutableList<String> = ArrayList()
        nombres.add("Alberto")
        nombres.add("Manuel")
        nombres.add("Laura")
        nombres.add("Monica")
        nombres.add("Pablo")

        val dataAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, nombres
        )

        spinnerActivitySp.adapter = dataAdapter
    }

    private fun setUp() {
        spinnerActivitySp.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position > 0) {
                    spinnerActivityTv.text = parent.selectedItem as CharSequence
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


}
