package es.uca.pnet2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReservasViewModel : ViewModel() {
    private val reservasLiveData = MutableLiveData<List<Reservas>>()

    init {
        val apiService = ApiService()
        viewModelScope.launch {
            try {
                val reservas = apiService.getData()
                reservasLiveData.value = reservas
            } catch (e: Exception) {
                // Gérer les erreurs lors de l'appel API
            }
        }
        // Lista de mascotas
        /*val reservas = listOf(
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
        reservasLiveData.value = reservas*/
    }

    fun getReservas(): LiveData<List<Reservas>> {
        return reservasLiveData
    }
}