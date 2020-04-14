package ec.com.paul.arquitecturamvvm.db

import ec.com.paul.arquitecturamvvm.modelo.User
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Created by Paul Yaguachi on 25/9/2019.
 * Paul Local
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE login = :login")
    fun findByLogin(login: String): LiveData<User>
}