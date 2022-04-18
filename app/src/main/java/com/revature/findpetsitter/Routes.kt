package com.revature.findpetsitter

sealed class Routes(val route: String) {
    object SplashScreen : Routes ("splashscreen")
    object MainScreen : Routes ("mainscreen")
    object SignIn : Routes("signin")
    object CreateAccount : Routes("createaccount")
}