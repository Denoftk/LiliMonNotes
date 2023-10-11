package com.HunterSoul.notas.model
import kotlinx.serialization.Serializable
@Serializable
data class Nota(
    val id: ULong,
    val titulo: String,
    val contenido: String?,
    val status: Int?,
    val tipo: Int,
    val fecha: String,
    val fechaModi: String,
    val fechaCum: String?

)
