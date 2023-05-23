package es.uca.pnet2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReservasViewModel : ViewModel() {
    private val reservasLiveData = MutableLiveData<List<Reservas>>()

    init {
        // Lista de mascotas
        val reservas = listOf(
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
            Reservas("Futbol", "Bahia sur", "23:00"),
        )
        reservasLiveData.value = reservas
    }

    fun getReservas(): LiveData<List<Reservas>> {
        return reservasLiveData
    }
}