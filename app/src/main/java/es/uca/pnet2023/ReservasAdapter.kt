package es.uca.pnet2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ReservasAdapter (private var reservas: List<Reservas>) :
    RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder>(){
    inner class ReservasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sportsTextView: TextView = itemView.findViewById(R.id.textViewSportsName)
        val centerTextView: TextView = itemView.findViewById(R.id.textViewCenterName)
        val timeTextView: TextView = itemView.findViewById(R.id.textViewTime)
        val detalleButton: Button = itemView.findViewById(R.id.buttonDetalles)
    }
    constructor() : this(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_reservas, parent, false)
        return ReservasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReservasAdapter.ReservasViewHolder, position: Int){
        val currentReserva = reservas[position]
        val endHour = currentReserva.duration.toInt() + currentReserva.hour.substringBefore(':').toInt()
        holder.sportsTextView.text = currentReserva.pista
        holder.centerTextView.text = currentReserva.centro
        holder.timeTextView.text = currentReserva.hour + " - " + endHour.toString() + ':' + currentReserva.hour.substringAfter(':')
        holder.detalleButton.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Detalle Reserva ${currentReserva.pista}", Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount() = reservas.size

    fun setReservas(reservas: List<Reservas>){
        this.reservas = reservas
    }
}

