package ec.com.paul.roomudemy.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ec.com.paul.roomudemy.R
import ec.com.paul.roomudemy.db.database.AppDataBase
import ec.com.paul.roomudemy.db.entity.Professor
import ec.com.paul.roomudemy.db.entity.ProfessorLanguage
import ec.com.paul.roomudemy.db.entity.Language
import kotlinx.android.synthetic.main.activity_proffesor_lenguaje.*


class ProffesorLenguajeActivity : AppCompatActivity() {

    private lateinit var professorLanguage: ProfessorLanguage
    private lateinit var professors: List<Professor>
    private lateinit var languages: List<Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proffesor_lenguaje)

        btn_salvar.setOnClickListener {
            professorLanguage = ProfessorLanguage()
            professorLanguage.languageId = Integer.parseInt(et_lenguaje_id.text.toString())
            professorLanguage.profesorId = Integer.parseInt(et_professor_id.text.toString())
            AppDataBase.getAppDb(applicationContext).professorLenguageDao().insert(professorLanguage)
        }
        btn_read_professor.setOnClickListener {
            this.professors = AppDataBase.getAppDb(applicationContext).professorLenguageDao().getProfessorForRepository(Integer.parseInt(et_lenguaje_id.text.toString()))
            for (item in professors) {
                Log.i("TAG", "id: (${item.id}) nombre: (${item.name}) Email: (${item.email})")
            }
        }
        btn_read_language.setOnClickListener {
            this.languages = AppDataBase.getAppDb(applicationContext).professorLenguageDao().getLanguageForRepository(Integer.parseInt(et_professor_id.text.toString()))
            for (language in languages) {
                Log.d(
                    "TAG",
                    "id: " + language.id + " Nombre: " + language.name
                )
            }
        }

    }
}
