package ec.com.paul.roomudemy.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ec.com.paul.roomudemy.R
import ec.com.paul.roomudemy.db.database.AppDataBase
import ec.com.paul.roomudemy.db.entity.Language
import kotlinx.android.synthetic.main.activity_languages.*

class LanguagesActivity : AppCompatActivity() {

    private lateinit var language: Language
    private lateinit var languages: List<Language>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_languages)
        btn_save.setOnClickListener {
            this.language = Language()
            this.language.name = et_name_curse.text.toString()
            AppDataBase.getAppDb(applicationContext).languageDao().insertCLanguage(this.language)
        }
        btn_read_language.setOnClickListener {
            this.languages = AppDataBase.getAppDb(applicationContext).languageDao().findAllLanguages()
            for (language in languages) {
                Log.d(
                    "TAG",
                    "id: " + language.id + " Nombre: " + language.name
                )
            }
        }

        btn_update_language.setOnClickListener {
            this.language = Language()
            this.language.id = 1
            this.language.name = "Android"

            AppDataBase.getAppDb(applicationContext).languageDao()
                .updateLanguageById(this.language)
        }

        btn_delete_language.setOnClickListener {
            this.language = Language()
            this.language.id = 1

            AppDataBase.getAppDb(applicationContext).languageDao()
                .deleteLanguageById(this.language)
        }

    }
}
