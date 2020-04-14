package ec.com.paul.roomudemy.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import ec.com.paul.roomudemy.constants.Constants
import ec.com.paul.roomudemy.db.dao.CurseDao
import ec.com.paul.roomudemy.db.dao.ProfessorDao
import ec.com.paul.roomudemy.db.entity.Curse
import ec.com.paul.roomudemy.db.entity.Professor
import android.arch.persistence.db.SupportSQLiteDatabase
import ec.com.paul.roomudemy.constants.SqlScript
import ec.com.paul.roomudemy.db.dao.LanguageDao
import ec.com.paul.roomudemy.db.dao.ProfessorLanguageDao
import ec.com.paul.roomudemy.db.entity.Language
import ec.com.paul.roomudemy.db.entity.ProfessorLanguage


/**
 * Created by Paul Yaguachi on 31/3/2019.
 * Paul Local
 */

@Database(entities = [Professor::class, Curse::class, Language::class, ProfessorLanguage::class], version = 4)
abstract class AppDataBase : RoomDatabase() {

    abstract fun professorDao(): ProfessorDao
    abstract fun curseDao(): CurseDao
    abstract fun languageDao(): LanguageDao
    abstract fun professorLenguageDao(): ProfessorLanguageDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getAppDb(context: Context): AppDataBase {
            if (INSTANCE == null) {
                this.INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    Constants.NAME_DATABASE
                ).allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .build()

            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            this.INSTANCE = null
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(SqlScript.SQL_TABLE_CURSE)
            }
        }
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(SqlScript.SQL_TABLE_LANGUAGE)
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(SqlScript.SQL_TABLE_PROFESSOR_LANGUAGE)
            }
        }
    }

}