package ec.com.paul.viewmodeludemy

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created by Paul Yaguachi on 15/9/2019.
 * Paul Local
 */
class ViewModelBindingAdapter {

    companion object {
        @BindingAdapter("onlyVisible")
        @JvmStatic
        fun setVisible(view: View, visibility: Boolean) {
            view.visibility = if (visibility) {
                View.VISIBLE
            } else
                View.GONE
        }

        @BindingAdapter("visible", "size")
        @JvmStatic
        fun setSize(view: TextView, visibility: Boolean, size: Float) {
            if (visibility) {
                view.visibility = View.VISIBLE
                view.textSize = size
            } else
                view.visibility = View.GONE
        }
    }
}