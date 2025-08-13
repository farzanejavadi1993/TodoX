package com.fermer.todox.nav

sealed class Dest(val route: String) {
    object Splash : Dest("splash")
    object SignIn : Dest("auth/signin")
    object SignUp : Dest("auth/signup")
    object Tasks  : Dest("tasks")
}