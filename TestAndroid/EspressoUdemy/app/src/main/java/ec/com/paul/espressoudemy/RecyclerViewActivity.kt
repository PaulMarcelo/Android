package ec.com.paul.espressoudemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import ec.com.paul.espressoudemy.utils.Persona
import ec.com.paul.espressoudemy.utils.PersonaAdapter
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.ArrayList

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var personas: MutableList<Persona>
    lateinit var personaAdapter: PersonaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setUp()
        setUpView()
    }

    private fun setUpView() {
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerActivityRv.layoutManager = layoutManager
        personaAdapter = PersonaAdapter(personas)
        recyclerActivityRv.adapter = personaAdapter
        recyclerActivityBtWebView.setOnClickListener {
            Log.i("LOG", "CLiCK")
        }
    }

    private fun setUp() {
        personas = ArrayList()
        personas.add(Persona("Alberto1", "1"))
        personas.add(Persona("Alberto2", "2"))
        personas.add(Persona("Alberto3", "3"))
        personas.add(Persona("Alberto4", "4"))
        personas.add(Persona("Alberto5", "5"))
        personas.add(Persona("Alberto6", "6"))
        personas.add(Persona("Alberto7", "7"))
        personas.add(Persona("Alberto8", "8"))
        personas.add(Persona("Alberto9", "9"))
        personas.add(Persona("Alberto10", "10"))
        personas.add(Persona("Alberto11", "11"))
        personas.add(Persona("Alberto12", "12"))
        personas.add(Persona("Alberto13", "13"))
        personas.add(Persona("Alberto14", "14"))
        personas.add(Persona("Alberto15", "15"))
        personas.add(Persona("Alberto16", "16"))
        personas.add(Persona("Alberto17", "17"))
        personas.add(Persona("Alberto18", "18"))
        personas.add(Persona("Alberto19", "19"))
        personas.add(Persona("Alberto20", "20"))
        personas.add(Persona("Paul", "30"))
    }
}
