package ec.com.paul.espressoudemy

import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Created by Paul Yaguachi on 30/12/2019.
 * Paul Local
 */
class ToastMatcher : TypeSafeMatcher<Root>() {

    companion object {
        private val MAX_FAILURES = 5
        private var failures = 0
    }

    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    override fun matchesSafely(root: Root): Boolean {
        val type = root.windowLayoutParams.get().type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowsToken = root.decorView.windowToken
            val appToken = root.decorView.applicationWindowToken
            if (windowsToken == appToken) {  //verifica si esta pantalla no esta contenida en otra pantalla
                return true
            }
        }

        return ++failures > MAX_FAILURES  // evita quedar en un bucle infinito
    }

}