package es.uca.pnet2023

import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReservasAdapter (private var reservas: List<Reservas>) :
    RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder>(){

    inner class ReservasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sportsTextView: TextView = itemView.findViewById(R.id.textViewSportsName)
        val centerTextView: TextView = itemView.findViewById(R.id.textViewCenterName)
        val timeTextView: TextView = itemView.findViewById(R.id.textViewTime)
        val detalleButton: Button = itemView.findViewById(R.id.buttonDetalles)
        val editButton: Button = itemView.findViewById(R.id.editButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        val nameTextView: TextView = itemView.findViewById(R.id.reservationName)
        val mailTextView: TextView = itemView.findViewById(R.id.reservationMail)
        val phoneTextView: TextView = itemView.findViewById(R.id.reservationPhone)
        val peopleTextView: TextView = itemView.findViewById(R.id.reservationPeople)
        val dniTextView: TextView = itemView.findViewById(R.id.reservationDni)
        val messageTextView: TextView = itemView.findViewById(R.id.reservationMessage)
    }
    constructor() : this(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_reservas, parent, false)
        return ReservasViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ReservasAdapter.ReservasViewHolder, position: Int){
        val currentReserva = reservas[position]
        val endHour = currentReserva.duration.toInt() + currentReserva.hour.substringBefore(':').toInt()
        val isExpanded = currentReserva.isExpanded
        val apiService = ApiService()

        if (isExpanded) {
            holder.nameTextView.visibility = View.VISIBLE
            holder.mailTextView.visibility = View.VISIBLE
            holder.phoneTextView.visibility = View.VISIBLE
            holder.peopleTextView.visibility = View.VISIBLE
            holder.dniTextView.visibility = View.VISIBLE
            holder.messageTextView.visibility = View.VISIBLE
            holder.editButton.visibility = View.VISIBLE
            holder.deleteButton.visibility = View.VISIBLE

        } else {
            holder.nameTextView.visibility = View.GONE
            holder.mailTextView.visibility = View.GONE
            holder.phoneTextView.visibility = View.GONE
            holder.peopleTextView.visibility = View.GONE
            holder.dniTextView.visibility = View.GONE
            holder.messageTextView.visibility = View.GONE
            holder.editButton.visibility = View.GONE
            holder.deleteButton.visibility = View.GONE
        }

        holder.sportsTextView.text = currentReserva.pista
        holder.centerTextView.text = currentReserva.centro
        holder.timeTextView.text = currentReserva.hour + " - " + endHour.toString() + ':' + currentReserva.hour.substringAfter(':')
        holder.nameTextView.text = "Name: " + currentReserva.name
        holder.mailTextView.text = "Mail: " + currentReserva.mail
        holder.phoneTextView.text = "Phone: " + currentReserva.phone
        holder.peopleTextView.text = "People: " + currentReserva.personas
        holder.dniTextView.text = "DNI: " + currentReserva.dni
        holder.messageTextView.text = "Message: " + currentReserva.message
        holder.detalleButton.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Detalle Reserva ${currentReserva.pista}", Toast.LENGTH_SHORT).show()
        }
        holder.deleteButton.setOnClickListener() {
            GlobalScope.launch {
                apiService.deleteData(currentReserva._id)

            }
        }

        holder.detalleButton.setOnClickListener {
            currentReserva.isExpanded = !currentReserva.isExpanded
            notifyDataSetChanged()
        }
    }
    override fun getItemCount() = reservas.size

    fun setReservas(reservas: List<Reservas>){
        this.reservas = reservas
    }
}

