package ec.com.paul.roomudemy.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ec.com.paul.roomudemy.R
import ec.com.paul.roomudemy.db.database.AppDataBase
import ec.com.paul.roomudemy.db.entity.Curse
import kotlinx.android.synthetic.main.activity_curse.*
import android.util.Log


class CurseActivity : AppCompatActivity() {

    private lateinit var curse: Curse
    private lateinit var curses: List<Curse>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curse)
        configView()
        bt_save.setOnClickListener {
            this.curse = Curse()
            this.curse.name = et_name_curse.text.toString()
            this.curse.duration = et_duration_curse.text.toString()
            this.curse.profesoId = et_id_professor.text.toString().toInt()
            AppDataBase.getAppDb(applicationContext).curseDao().insertCurse(this.curse)
        }

        bt_read_curse_by_proffesor.setOnClickListener {
            this.curses = AppDataBase.getAppDb(applicationContext).curseDao()
                .findCurseByProfessor(et_id_professor.text.toString().toInt())
            for (course in curses) {
                Log.d(
                    "TAG",
                    "id: " + course.id + " Nombre: " + course.name + " Duration:" + course.duration + " idProfesor: " + course.profesoId
                )
            }
        }

        bt_update_curse.setOnClickListener {
            this.curse = Curse()
            this.curse.id = 1
            this.curse.name = "Android"
            this.curse.duration = "5"
            this.curse.profesoId = 2

            AppDataBase.getAppDb(applicationContext).curseDao()
                .updateCurseById(this.curse)
        }
        bt_delete_curse.setOnClickListener {
            this.curse = Curse()
            this.curse.id = 1

            AppDataBase.getAppDb(applicationContext).curseDao()
                .deleteCurseById(this.curse)
        }

    }

    private fun configView() {
        this.curse = Curse()
        this.curses = ArrayList()
    }
}
