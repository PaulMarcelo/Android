package ec.com.paul.roomudemy.ui

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ec.com.paul.roomudemy.R
import ec.com.paul.roomudemy.db.database.AppDataBase
import ec.com.paul.roomudemy.db.entity.Professor
import kotlinx.android.synthetic.main.activity_professor.*


class ProfessorActivity : AppCompatActivity() {

    lateinit var professor: Professor
    lateinit var listProfessor: List<Professor>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professor)
        configView()
    }

    private fun configView() {
        this.professor = Professor()
        this.listProfessor = ArrayList()
        this.btn_save.setOnClickListener {
            this.professor.name = et_name.text.toString()
            this.professor.email = et_email.text.toString()
            WriteDataBaseTask().execute(professor)
        }

        this.bt_read.setOnClickListener {
            ReadDataBaseTask().execute()
        }

        this.bt_find_by_name.setOnClickListener {
            this.professor =
                AppDataBase.getAppDb(applicationContext).professorDao().findProfessorByName(et_name.text.toString())
            showProffesor(arrayListOf(this.professor))
        }

        this.bt_find_by_id.setOnClickListener {
            this.professor =
                AppDataBase.getAppDb(applicationContext).professorDao().findProfessorById(1)
            showProffesor(arrayListOf(this.professor))
        }

        this.bt_update_by_id.setOnClickListener {
            val profe = Professor()
            profe.id = 1
            profe.name = "paul"
            profe.email = "paul@gmail.com"
            AppDataBase.getAppDb(applicationContext).professorDao().updateProfessorById(profe)
        }

        this.bt_delete_by_id.setOnClickListener {
            val profe = Professor()
            profe.id = 3
            AppDataBase.getAppDb(applicationContext).professorDao().deleteProfessorById(profe)
        }

        this.bt_delete.setOnClickListener {
            AppDataBase.getAppDb(applicationContext).professorDao().deleteAllProfessor()
        }
    }

    private inner class WriteDataBaseTask : AsyncTask<Professor, Void, Void>() {
        override fun doInBackground(vararg params: Professor): Void? {
            AppDataBase.getAppDb(applicationContext).professorDao().insertProffesor(params[0])
            return null
        }
    }

    private inner class ReadDataBaseTask : AsyncTask<Void, Void, List<Professor>>() {
        override fun doInBackground(vararg params: Void?): List<Professor> {
            return AppDataBase.getAppDb(applicationContext).professorDao().findAllProfessor()
        }

        override fun onPostExecute(result: List<Professor>) {
            super.onPostExecute(result)
            showProffesor(result)
        }
    }


    private fun showProffesor(result: List<Professor>) {
        for (item in result) {
            Log.i("TAG", "id: (${item.id}) nombre: (${item.name}) Email: (${item.email})")
        }
    }

}
