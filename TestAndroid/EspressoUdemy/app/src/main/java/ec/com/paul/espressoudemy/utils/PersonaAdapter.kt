package ec.com.paul.espressoudemy.utils

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.com.paul.espressoudemy.R


/**
 * Created by Paul Yaguachi on 7/12/2019.
 * Paul Local
 */
class PersonaAdapter(private val personas: List<Persona>) : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_persona, parent, false)
        return PersonaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        holder.tvNombre.text = personas[position].nombre
        holder.tvEdad.text = personas[position].edad
    }

    override fun getItemCount(): Int {
        return personas.size
    }

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val tvNombre: TextView
         val tvEdad: TextView

        init {
            tvNombre = itemView.findViewById(R.id.tvNombre)
            tvEdad = itemView.findViewById(R.id.tvEdad)
        }
    }
}