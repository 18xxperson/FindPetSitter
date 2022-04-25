package com.revature.findpetsitter

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.findpetsitter.data.User
import com.revature.findpetsitter.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateAccount(navController: NavController, userViewModel: UserViewModel) {

    val context = LocalContext.current
    val name = remember{mutableStateOf("")}
    val email = remember{mutableStateOf("")}
    val password = remember{mutableStateOf("")}
    val confirmPassword = remember{mutableStateOf(TextFieldValue())}

    val nameErrorState = remember{mutableStateOf(false)}
    val emailErrorState = remember{mutableStateOf(false)}
    val passwordErrorState = remember{mutableStateOf(false)}
    val confirmPasswordErrorState = remember{mutableStateOf(false)}
    val scrollState = rememberScrollState()
    val scope= rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create Account", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(Modifier.size(16.dp))
        TextField(
            value = name.value,
            onValueChange = {
                if(nameErrorState.value) {
                    nameErrorState.value = false
                }
                name.value = it
            },

            isError = nameErrorState.value,
            label = {
                Text(text = "Name")
            },
        )
        if(nameErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))

        TextField(
            value = email.value,
            onValueChange = {
                if(emailErrorState.value) {
                    emailErrorState.value = false
                }
                email.value = it
            },

            isError = emailErrorState.value,
            label = {
                Text(text = "Email")
            },
        )
        if(emailErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }

        Spacer(modifier = Modifier.size(16.dp))

        val passwordVisibility = remember{mutableStateOf(true)}

        TextField(
            value = password.value,
            onValueChange = {
                if(passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            label = {Text(text = "Password")},
            isError = passwordErrorState.value,
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if(passwordVisibility.value) Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff,
                        contentDescription = "visibility"
                        )
                }
            },
            visualTransformation = if(passwordVisibility.value) PasswordVisualTransformation()
            else
                VisualTransformation.None

        )
        if(passwordErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }

        Spacer(Modifier.size(16.dp))
        val cPasswordVisibility = remember{mutableStateOf(true)}
        TextField(
            value = confirmPassword.value,
            onValueChange = {
                if(confirmPasswordErrorState.value) {
                    confirmPasswordErrorState.value = false
                }
                confirmPassword.value = it
            },
            isError = confirmPasswordErrorState.value,
            label = {
                Text(text = "Confirm Password")
            },
            trailingIcon = {
                IconButton(onClick = {
                    cPasswordVisibility.value = !cPasswordVisibility.value
                }) {
                    Icon(
                        imageVector = if(cPasswordVisibility.value) Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        contentDescription = "visibility"
                        )
                }
            },
            visualTransformation = if(cPasswordVisibility.value) PasswordVisualTransformation()
            else
                VisualTransformation.None
        )
        if(confirmPasswordErrorState.value) {
            val msg = if(confirmPassword.value.text.isEmpty()) {
                "Required"
            }
            else if (confirmPassword.value.text != password.value) {
                "Passwords don't match"
            }
            else {
                ""
            }
            Text(text = msg, color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))

        Button(
            onClick = {
                when {
                    name.value.isEmpty() -> {
                        nameErrorState.value = true
                    }
                    email.value.isEmpty() -> {
                        emailErrorState.value = true
                    }
                    password.value.isEmpty() -> {
                        passwordErrorState.value = true
                    }
                    confirmPassword.value.text.isEmpty() -> {
                        confirmPasswordErrorState.value = true
                    }
                    confirmPassword.value.text != password.value -> {
                        confirmPasswordErrorState.value = true
                    }
                    else -> {
                        Toast.makeText(
                            context, "Account Created",
                            Toast.LENGTH_SHORT
                        ).show()
                        scope.launch {
                            userViewModel.insertUser(
                                User(
                                    email = email.value,
                                    name = name.value,
                                    password = password.value
                                )
                            )
                        }
                      navController.navigate(Routes.SignIn.route)
                    }
                }
            },
            content = {
                Text(text = "Sign Up", color = Color.White)
            },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
        )
    }

}