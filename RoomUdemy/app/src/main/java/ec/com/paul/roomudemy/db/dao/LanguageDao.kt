package ec.com.paul.roomudemy.db.dao

import android.arch.persistence.room.*
import ec.com.paul.roomudemy.db.entity.Language

/**
 * Created by Paul Yaguachi on 7/4/2019.
 * Paul Local
 */
@Dao
interface LanguageDao {

    @Insert
    fun insertCLanguage(language: Language)

    @Query("SELECT * FROM language")
    fun findAllLanguages(): List<Language>

    @Update
    fun updateLanguageById(language: Language)

    @Delete
    fun deleteLanguageById(language: Language)
}