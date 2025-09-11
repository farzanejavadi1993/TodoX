package com.fermer.common.mvi

interface Reducer<S : UiState, E : UiEvent> {
    fun reduce(currentState: S, event: E): S
}
