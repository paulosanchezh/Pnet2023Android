package es.uca.pnet2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReservarAdapter() :
    RecyclerView.Adapter<ReservarAdapter.ReservarViewHolder>() {

    inner class ReservarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val centro: EditText = itemView.findViewById(R.id.editTextCentro)
        val pista: EditText = itemView.findViewById(R.id.editTextPista)
        val date: EditText = itemView.findViewById(R.id.editTextDate)
        val time: EditText = itemView.findViewById(R.id.editTextTime)
        val duration: EditText = itemView.findViewById(R.id.editTextNumberSigned)
        val people: EditText = itemView.findViewById(R.id.editTextPeople)
        val name: EditText = itemView.findViewById(R.id.editTextName)
        val mail: EditText = itemView.findViewById(R.id.editTextEmailAddress)
        val dni: EditText = itemView.findViewById(R.id.editTextDNI)
        val phone: EditText = itemView.findViewById(R.id.editTextPhone)
        val message: EditText = itemView.findViewById(R.id.editTextMessage)
        val submitButton: Button = itemView.findViewById(R.id.submitButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservarViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reservas, parent, false)
        return ReservarViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ReservarViewHolder, position: Int) {
        val apiService = ApiService()

        holder.submitButton.setOnClickListener() {
            println("salut")
            GlobalScope.launch {
                val data = Reservas("", holder.centro.text.toString(), holder.pista.text.toString(), holder.people.text.toString(), holder.date.text.toString(), holder.duration.text.toString(), holder.time.text.toString(), holder.dni.text.toString(), holder.message.text.toString(), holder.name.text.toString(), holder.mail.text.toString(), holder.phone.text.toString())
                apiService.postData(data)
            }
        }


    }

}