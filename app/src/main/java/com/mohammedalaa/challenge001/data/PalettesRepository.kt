package com.mohammedalaa.challenge001.data

import com.mohammedalaa.challenge001.model.PaletteResult

interface PalettesRepository {
    suspend fun loadDataFromJson(): List<PaletteResult.Content>?
}
