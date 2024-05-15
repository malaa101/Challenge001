package com.mohammedalaa.challenge001.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PaletteResult(
    val content: List<Content>,
    val success: Boolean // true
) {
    @JsonClass(generateAdapter = true)
    data class Content(
        @Json(name = "content")
        val itemContent: List<ItemContent>?,
        val icon: String?, // https://and.appchief.dev/storage/templates/January2024/h1a9ocqWuBU8loaMjJG7/cover.png
        val id: String?, // 1
        @Json(name = "is_new")
        val isNew: Boolean?, // true
        val name: String?, // Basic Solid
        val type: String? // solid
    ) {
        @JsonClass(generateAdapter = true)
        data class ItemContent(
            var isSelected: Boolean = false,
            val angle: Double?, // 0.0
            val color: String?, // #EA3333
            val colors: List<String>?,
            val id: String?, // 1
            val image: String?, // https://and.appchief.dev/storage/media/136/c/7OgskdOeBHImXzz1RQx4-2x.png
            @Json(name = "is_new")
            val isNew: Boolean?, // true
            @Json(name = "is_premium")
            val isPremium: Boolean?, // false
            val positions: List<Double>?
        )


    }
}