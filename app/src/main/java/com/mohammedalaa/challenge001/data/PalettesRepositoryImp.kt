package com.mohammedalaa.challenge001.data

import com.mohammedalaa.challenge001.model.PaletteResult
import javax.inject.Inject


class PalettesRepositoryImp @Inject constructor( private val jsonDataStore: JsonDataStore) : PalettesRepository {

    override suspend fun loadDataFromJson(): List<PaletteResult.Content>? {
        return jsonDataStore.loadDataFromJson()
    }
}
