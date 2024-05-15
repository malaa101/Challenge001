package com.mohammedalaa.challenge001.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammedalaa.challenge001.domain.PalettesMiddleware
import com.mohammedalaa.challenge001.model.PaletteResult
import com.mohammedalaa.challenge001.ui.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PalettesViewModel @Inject constructor (
    palettesMiddleware: PalettesMiddleware,
) : ViewModel() {

    private val store = Store(
        initialState = PalettesViewState(
            pallets = emptyList(),
            showLoading = true,
            errorMessage = null
        ),
        reducer = SearchCitiesReducer(),
        middlewares = listOf(
            palettesMiddleware
        )
    )

    val viewState: MutableStateFlow<PalettesViewState> = store.state


    init {
        loadDataFromJson()
    }

    private fun loadDataFromJson() {
        val action = PalettesAction.LoadDataFromJson
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun selectedColor(itemContent: PaletteResult.Content.ItemContent) {
        viewState.value = viewState.value.copy(
            itemContent = itemContent
        )
    }

}