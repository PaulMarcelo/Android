package ec.com.paul.roomudemy.db.dao

import android.arch.persistence.room.*
import ec.com.paul.roomudemy.db.entity.Professor

/**
 * Created by Paul Yaguachi on 31/3/2019.
 * Paul Local
 */
@Dao
interface ProfessorDao {
    @Insert
    fun insertProffesor(proffesor: Professor)

    @Query("SELECT * FROM professor")
    fun findAllProfessor(): List<Professor>

    @Query("SELECT * FROM professor WHERE name LIKE :name")
    fun findProfessorByName(name: String): Professor

    @Query("SELECT * FROM professor WHERE id LIKE :id")
    fun findProfessorById(id: Int): Professor

    @Update
    fun updateProfessorById(professor: Professor)

    @Query("DELETE FROM professor")
    fun deleteAllProfessor()

    @Delete
    fun deleteProfessorById(professor: Professor)

}