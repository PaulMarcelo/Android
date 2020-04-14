package ec.com.paul.roomudemy.db.dao

import android.arch.persistence.room.*
import ec.com.paul.roomudemy.db.entity.Curse

/**
 * Created by Paul Yaguachi on 6/4/2019.
 * Paul Local
 */
@Dao
interface CurseDao {

    @Insert
    fun insertCurse(curse: Curse)

    @Query("SELECT * FROM curse WHERE profesorId= :profesoId")
    fun findCurseByProfessor(profesoId: Int): List<Curse>

    @Update
    fun updateCurseById(curse: Curse)

    @Delete
    fun deleteCurseById(curse: Curse)

}