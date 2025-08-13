package com.fermer.todox.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fermer.auth.presentation.AuthViewModel
import com.fermer.auth.presentation.screen.SignInScreen
import com.fermer.auth.presentation.screen.SignUpScreen

@Composable
fun SignInRoute(
    onNavigateToSignUp: () -> Unit,
    onAuthSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onAuthSuccess()
    }

    SignInScreen(
        isLoading = state.isLoading,
        error = state.errorMessage,
        onSignInClick = { email, pass -> viewModel.signIn(email, pass) },
        onNavigateToSignUp = onNavigateToSignUp
    )
}

@Composable
fun SignUpRoute(
    onNavigateToSignIn: () -> Unit,
    onAuthSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onAuthSuccess()
    }

    SignUpScreen(
        isLoading = state.isLoading,
        error = state.errorMessage,
        onSignUpClick = { email, pass -> viewModel.signUp(email, pass) },
        onNavigateToSignIn = onNavigateToSignIn
    )
}

