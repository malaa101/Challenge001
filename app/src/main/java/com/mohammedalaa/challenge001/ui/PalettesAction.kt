package com.mohammedalaa.challenge001.ui

import com.mohammedalaa.challenge001.model.PaletteResult
import com.mohammedalaa.challenge001.ui.redux.Action

/**
 * These are all of the possible actions that can be triggered from the cities screen.
 */
sealed class PalettesAction : Action {
    data class  JsonDataLoaded(val palettes: List<PaletteResult.Content>) : PalettesAction()
    data object LoadDataFromJson : PalettesAction()
    data object NoResultFound : PalettesAction()

}
