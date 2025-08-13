package com.fermer.todox.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fermer.task.presentation.TaskScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Dest.Splash.route) {

        composable(Dest.Splash.route) {

            val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
            LaunchedEffect(isLoggedIn) {
                navController.navigate(
                    if (isLoggedIn)
                        Dest.Tasks.route
                    else
                        Dest.SignIn.route)
                {
                    popUpTo(Dest.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
            }

            BoxedLoading()
        }

        composable(Dest.SignIn.route) {

            SignInRoute(
                onNavigateToSignUp = { navController.navigate(Dest.SignUp.route) },
                onAuthSuccess = {
                    navController.navigate(Dest.Tasks.route) {
                        popUpTo(Dest.SignIn.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Dest.SignUp.route) {
            SignUpRoute(
                onNavigateToSignIn = { navController.popBackStack() },
                onAuthSuccess = {
                    navController.navigate(Dest.Tasks.route) {
                        popUpTo(Dest.SignUp.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }


        composable(Dest.Tasks.route) {
            TaskScreen()
        }
    }
}

@Composable
private fun BoxedLoading() {
    Scaffold { padding ->
        CircularProgressIndicator(modifier = androidx.compose.ui.Modifier.padding(padding)) }
}