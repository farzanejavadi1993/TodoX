package com.fermer.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fermer.auth.presentation.model.AuthUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()



    fun signUp(email: String, password: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _uiState.value = if (task.isSuccessful) {
                        AuthUiState(isLoading = false, isSuccess = true)
                    } else {
                        AuthUiState(isLoading = false, errorMessage = task.exception?.message)
                    }
                }
        }
    }

    fun signIn(email: String, password: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _uiState.value = if (task.isSuccessful) {
                    AuthUiState(isLoading = false, isSuccess = true)
                } else {
                    AuthUiState(isLoading = false, errorMessage = task.exception?.message)
                }
            }
    }
}


