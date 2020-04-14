package ec.com.paul.crashlytics

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.crashlytics.android.Crashlytics
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseError
import io.fabric.sdk.android.services.common.FirebaseInfo

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val a: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        button.setOnClickListener {

//            tvText.text = a!!.toInt().toString()


//            Crashlytics.setString("KEY1", "This es a new message")
//            Crashlytics.log("This is a  new log")
            Crashlytics.log(Log.ERROR, "YourTAG", "YourMessage");
            Crashlytics.logException(Throwable("YourERROR"))
            Crashlytics.logException(Throwable("YourERROR"))

        }
        button2.setOnClickListener {
            throw ArrayIndexOutOfBoundsException()
        }
        button3.setOnClickListener {
            a ?: run {
                Crashlytics.log(Log.ERROR, "YourTAG", "YourMessage");
                Crashlytics.logException(Throwable("YourERROR"))
                Crashlytics.getInstance().crash()
            }
            tvText.text = button3.text

        }


        fab.setOnClickListener { view ->
            Crashlytics.log(Log.INFO, MainActivity::javaClass.name, "dasani")
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
