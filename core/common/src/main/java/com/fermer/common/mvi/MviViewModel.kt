package com.fermer.common.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MviViewModel<S : UiState, E : UiEvent, F : UiEffect>(
    initialState: S,
    private val reducer: Reducer<S, E>
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    protected fun updateState(update: (S) -> S) {
        _uiState.value = update(_uiState.value)
    }

    fun sendEvent(event: E) {
        val newState = reducer.reduce(_uiState.value, event)
        _uiState.value = newState
        handleEvent(event)
    }

    protected abstract fun handleEvent(event: E)


}
