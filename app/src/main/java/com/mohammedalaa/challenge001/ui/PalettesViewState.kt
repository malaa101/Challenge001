package com.mohammedalaa.challenge001.ui

import com.mohammedalaa.challenge001.model.PaletteResult
import com.mohammedalaa.challenge001.ui.redux.State


data class PalettesViewState(
    val showLoading: Boolean = true,
    val pallets: List<PaletteResult.Content>? = null,
    val errorMessage: String? = null,
    val itemContent: PaletteResult.Content.ItemContent? = null
) : State
