package ec.com.paul.arquitecturamvvm.modelo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName


/**
 * Created by Paul Yaguachi on 24/9/2019.
 * Paul Local
 */

@Entity(
    indices = [Index("id"), Index("owner_login")],
    primaryKeys = ["name", "owner_login"]
)
class Repo(
    val id: Int, @field:SerializedName("name")
    val name: String, @field:SerializedName("full_name")
    val fullName: String, @field:SerializedName("description")
    val description: String, @field:SerializedName("stargazers_count")
    val stars: Int, @field:SerializedName("owner")
    @field:Embedded(prefix = "owner_")
    val owner: Owner
) {

    class Owner(
        @field:SerializedName("login")
        val login: String,
        @field:SerializedName("url")
        val url: String?
    )

    companion object {

        const val UNKNOWN_ID = -1
    }
}
