package ec.com.paul.roomudemy.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ec.com.paul.roomudemy.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configView()

    }

    private fun configView() {
        btn_professor.setOnClickListener {
            startActivity(Intent(applicationContext, ProfessorActivity::class.java))
        }
        btn_cursos.setOnClickListener {
            startActivity(Intent(applicationContext, CurseActivity::class.java))
        }
        btn_languages.setOnClickListener {
            startActivity(Intent(applicationContext, LanguagesActivity::class.java))
        }
        btn_professor_language.setOnClickListener {
            startActivity(Intent(applicationContext, ProffesorLenguajeActivity::class.java))
        }
    }
}
