package ec.com.paul.arquitecturamvvm.db

import androidx.room.Dao
import ec.com.paul.arquitecturamvvm.modelo.RepoSearchResult
import androidx.room.OnConflictStrategy
import ec.com.paul.arquitecturamvvm.modelo.Repo
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import ec.com.paul.arquitecturamvvm.modelo.Contributor
import androidx.lifecycle.Transformations
import android.util.SparseIntArray
import androidx.arch.core.util.Function
import java.util.*
import kotlin.Comparator

/**
 * Created by Paul Yaguachi on 25/9/2019.
 * Paul Local
 */
@Dao
abstract class RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg repos: Repo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertContributors(contributors: List<Contributor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repositories: List<Repo>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun creatreRepoIfNotExists(repo: Repo): Long

    @Query("SELECT * FROM repo WHERE owner_login = :login AND name = :name")
    abstract fun load(login: String, name: String): LiveData<Repo>

    @Query("SELECT login, avatarUrl, repoName, repoOwner, contributions FROM contributor WHERE repoName = :name AND repoOwner = :owner ORDER BY contributions DESC")
    abstract fun loadContributors(owner: String, name: String): LiveData<List<Contributor>>

    @Query("SELECT * FROM Repo WHERE owner_login = :owner ORDER BY stars DESC")
    abstract fun loadRepositories(owner: String): LiveData<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(result: RepoSearchResult)

    @Query("SELECT * FROM RepoSearchResult WHERE query = :query")
    abstract fun search(query: String): LiveData<RepoSearchResult>

    @Query("SELECT * FROM Repo WHERE id in(:repoIds)")
    protected abstract fun loadById(repoIds: List<Int>): LiveData<List<Repo>>

    @Query("SELECT * FROM RepoSearchResult WHERE query = :query")
    abstract fun findSearchResult(query: String): RepoSearchResult

    fun loadOrdered(repoIds: List<Int>): LiveData<List<Repo>> {
        val order = SparseIntArray()
        for ((index, repoId) in repoIds.withIndex()) {
            order.put(repoId, index)
        }
        return Transformations.map(loadById(repoIds)) { repositories ->
            Collections.sort(repositories) { o1, o2 ->
                val pos1 = order.get(o1.id)
                val pos2 = order.get(o2.id)
                pos1 - pos2
            }
            repositories
        }
    }

}