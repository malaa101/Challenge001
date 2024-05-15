package com.mohammedalaa.challenge001.data

import com.mohammedalaa.challenge001.data.util.JsonUtil
import com.mohammedalaa.challenge001.model.PaletteResult
import javax.inject.Inject

class JsonDataStore @Inject constructor (private val jsonUtil: JsonUtil) : PalettesRepository {

    private val paletteList = jsonUtil.getPalettesDataFromJson()

    override suspend fun loadDataFromJson(): List<PaletteResult.Content>? {
        return paletteList?.content
    }

}
