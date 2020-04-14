package ec.com.paul.arquitecturamvvm.modelo

import com.google.gson.annotations.SerializedName



/**
 * Created by Paul Yaguachi on 24/9/2019.
 * Paul Local
 */
class RepoSearchResponse {
    @SerializedName("total_count")
    var total: Int = 0
    @SerializedName("items")
    var items: List<Repo>? = null
    var nextPage: Int? = null

    fun getRepoIds(): List<Int> {
        val repoIds = arrayListOf<Int>()
        for (repo in this.items!!) {
            repoIds.add(repo.id)
        }
        return repoIds
    }
}