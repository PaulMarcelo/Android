package ec.com.paul.arquitecturamvvm.modelo

import com.google.gson.annotations.SerializedName
import androidx.annotation.NonNull
import androidx.room.Entity


/**
 * Created by Paul Yaguachi on 24/9/2019.
 * Paul Local
 */

@Entity(primaryKeys = ["login"])
class User {
    @SerializedName("login")
    val login: String = ""
    @SerializedName("avagtar_url")
    val avatarUrl: String? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("company")
    val company: String? = null
    @SerializedName("repos_url")
    val reposUrl: String? = null
    @SerializedName("blog")
    val blog: String? = null
}
