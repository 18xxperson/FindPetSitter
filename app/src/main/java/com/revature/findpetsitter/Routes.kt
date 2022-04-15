package com.revature.findpetsitter

sealed class Routes(val route: String) {
    object SplashScreen : Routes ("SplashScreen")
    object MainScreen : Routes ("MainScreen")
    object SignIn : Routes("SignIn")
    object CreateAccount : Routes("CreateAccount")
}