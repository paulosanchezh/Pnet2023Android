package es.uca.pnet2023

import kotlinx.serialization.Serializable

@Serializable
data class Reservas(val _id: String, val centro: String, val pista: String, val personas: Int, val date: String, val duration: String, val hour: String, val dni: String, val message: String, val name: String, val mail: String, val phone: String) {
    var isExpanded: Boolean = false
}
