package ec.com.paul.arquitecturamvvm.modelo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName



/**
 * Created by Paul Yaguachi on 24/9/2019.
 * Paul Local
 */
@Entity(primaryKeys = ["repoName", "repoOwner", "login"],
    foreignKeys = [ForeignKey(
        entity = Repo::class,
        parentColumns =["name", "owner_login"],
        childColumns = ["repoName", "repoOwner"],
        onUpdate = ForeignKey.CASCADE
    )]
)
class Contributor {
    @SerializedName("login")
    val login: String?=null

    @SerializedName("contributions")
    val contributions: Int = 0

    @SerializedName("avatar_url")
    val avatarUrl: String? = null

    var repoName: String?=null
    var repoOwner: String?=null
}