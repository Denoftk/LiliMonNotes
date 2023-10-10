package com.HunterSoul.notas.data

import com.HunterSoul.notas.model.Nota
import com.HunterSoul.notas.repository.NotasApiService

interface NotasRepository {
    suspend fun getNotas(): List<Nota>

}

class LilNotasRepository(
    private val notasApiService: NotasApiService
): NotasRepository{
    override suspend fun getNotas(): List<Nota> =
        notasApiService.getAllNotas()
}