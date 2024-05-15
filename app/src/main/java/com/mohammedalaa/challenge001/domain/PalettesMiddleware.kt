package com.mohammedalaa.challenge001.domain

import com.mohammedalaa.challenge001.data.PalettesRepository
import com.mohammedalaa.challenge001.ui.PalettesAction
import com.mohammedalaa.challenge001.ui.PalettesViewState
import com.mohammedalaa.challenge001.ui.redux.Middleware
import com.mohammedalaa.challenge001.ui.redux.Store
import javax.inject.Inject


class PalettesMiddleware @Inject constructor(
    private val palettesRepository: PalettesRepository,
) : Middleware<PalettesViewState, PalettesAction> {

    override suspend fun process(
        action: PalettesAction,
        currentState: PalettesViewState,
        store: Store<PalettesViewState, PalettesAction>,
    ) {
        when (action) {
            is PalettesAction.LoadDataFromJson -> {
                loadDataFromJson(store)
            }
            else -> {
            }
        }
    }

    private suspend fun loadDataFromJson(
        store: Store<PalettesViewState, PalettesAction>,
    ) {
        val palettesResult = palettesRepository.loadDataFromJson()
        store.dispatch(PalettesAction.JsonDataLoaded(palettesResult ?: emptyList()))
    }

}