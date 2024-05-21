package com.mohammedalaa.challenge001.ui

import com.mohammedalaa.challenge001.ui.redux.Reducer


class PalettesReducer : Reducer<PalettesViewState, PalettesAction> {


    override fun reduce(
        currentState: PalettesViewState,
        action: PalettesAction,
    ): PalettesViewState {
        return when (action) {
            is PalettesAction.JsonDataLoaded -> stateResultLoaded(currentState,action)
            is PalettesAction.NoResultFound -> stateNoResultFound(currentState)
            else -> currentState
        }
    }

    private fun stateNoResultFound(currentState: PalettesViewState) =
        currentState.copy(showLoading = false, pallets = emptyList())

    private fun stateResultLoaded(currentState: PalettesViewState, action: PalettesAction.JsonDataLoaded) =
        currentState.copy(showLoading  = false,pallets = action.palettes)
}