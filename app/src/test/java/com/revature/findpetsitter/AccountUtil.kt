package com.revature.findpetsitter

object AccountUtil {

    private val existingUsers = listOf("Rahul", "Rohan")



    fun validAccountInput(
        userName: String,
        password: String,
        confirmPassword: String)
    : Boolean {

        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false
        }

        if (password != confirmPassword){
            return false
        }
        return true
    }

}