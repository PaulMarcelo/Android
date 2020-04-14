package ec.com.paul.arquitecturamvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ec.com.paul.arquitecturamvvm.modelo.Contributor
import ec.com.paul.arquitecturamvvm.modelo.Repo
import ec.com.paul.arquitecturamvvm.modelo.RepoSearchResult
import ec.com.paul.arquitecturamvvm.modelo.User

/**
 * Created by Paul Yaguachi on 28/9/2019.
 * Paul Local
 */

@Database(entities = [User::class, Repo::class, Contributor::class, RepoSearchResult::class], version = 1)
abstract class GithubDb : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun repoDao(): RepoDao
}