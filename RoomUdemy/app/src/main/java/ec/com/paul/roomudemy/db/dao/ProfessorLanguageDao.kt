package ec.com.paul.roomudemy.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import ec.com.paul.roomudemy.db.entity.Language
import ec.com.paul.roomudemy.db.entity.Professor
import ec.com.paul.roomudemy.db.entity.ProfessorLanguage

/**
 * Created by Paul Yaguachi on 7/4/2019.
 * Paul Local
 */
@Dao
interface ProfessorLanguageDao {
    @Insert
    fun insert(proffesorLanguage: ProfessorLanguage)

    @Query("SELECT * FROM professor INNER JOIN professorlanguage ON professor.id = professorlanguage.profesorId WHERE professorlanguage.languageId= :lenguajeId ")
    fun getProfessorForRepository(lenguajeId: Int): List<Professor>

    @Query("SELECT * FROM Language INNER JOIN professorlanguage ON language.id = professorlanguage.languageId WHERE professorlanguage.profesorId= :profesorId ")
    fun getLanguageForRepository(profesorId: Int): List<Language>
}