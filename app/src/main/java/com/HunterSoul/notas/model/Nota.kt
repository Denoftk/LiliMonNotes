package com.HunterSoul.notas.model
import kotlinx.serialization.Serializable
@Serializable
data class Nota(
    val id: Long,
    val titulo: String,
    val contenido: String,
    val status: Int,
    val tipo: Int

)
