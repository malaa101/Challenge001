package com.mohammedalaa.challenge001.data.util

import android.content.Context
import android.util.Log
import com.mohammedalaa.challenge001.model.PaletteResult
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class JsonUtil @Inject constructor (@ActivityContext private val context: Context) {

    fun getPalettesDataFromJson(): PaletteResult? {
        val file = "data.json"
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val classType = Types.newParameterizedType(PaletteResult::class.java,PaletteResult::class.java)
        val adapter: JsonAdapter<PaletteResult> = moshi.adapter(classType)

        val json = context.assets.open(file).bufferedReader().use { it.readText() }

        return adapter.fromJson(json)
    }
}