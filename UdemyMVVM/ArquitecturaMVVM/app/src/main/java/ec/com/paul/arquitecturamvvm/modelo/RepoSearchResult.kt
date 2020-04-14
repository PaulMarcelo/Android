package ec.com.paul.arquitecturamvvm.modelo

import androidx.room.Entity
import androidx.room.TypeConverters
import ec.com.paul.arquitecturamvvm.db.GithubTypeConverters


/**
 * Created by Paul Yaguachi on 24/9/2019.
 * Paul Local
 */
@Entity(primaryKeys = ["query"])
@TypeConverters(
    GithubTypeConverters::class)
class RepoSearchResult(
    val query: String?=null,
    val repoIds: List<Int>?=null,
    val totalCount: Int?=null,
    val next: Int?=null
)