package com.revature.findpetsitter

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.findpetsitter.ui.theme.FindPetSitterTheme
import kotlinx.coroutines.delay
import com.revature.findpetsitter.Routes


@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun SignIn(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {

    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val email = remember { mutableStateOf(TextFieldValue()) }
        val emailErrorState = remember { mutableStateOf(false) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val passwordErrorState = remember { mutableStateOf(false) }

        Text(text = "Sign In", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email Address") },
            value = email.value,
            onValueChange = {
                if (emailErrorState.value) {
                    emailErrorState.value = false
                }
                email.value = it
           },
            isError = emailErrorState.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()})
        )
        if (emailErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        val passwordVisibility = remember { mutableStateOf(true) }

        TextField(
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            isError = passwordErrorState.value,
            label = {
                Text(text = "Password")
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if (passwordVisibility.value) Icons.Filled.Add
                        else
                            Icons.Filled.AddCircle,
                        contentDescription = "Password Visibility"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}),
            singleLine = true,
            visualTransformation = if (passwordVisibility.value)
                PasswordVisualTransformation()
            else
                VisualTransformation.None
        )
        if (passwordErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {

                          when {
                              email.value.text.isEmpty() -> {
                                  emailErrorState.value = true
                              }
                              password.value.text.isEmpty() -> {
                                  passwordErrorState.value = true
                              }
                              else -> {
                                  passwordErrorState.value = false
                                  emailErrorState.value = false
                                  Toast.makeText(context,
                                  "Signed in successfully",
                                  Toast.LENGTH_SHORT).show()
                                  navController.navigate(Routes.ChooseService.route)
                              }
                          }

//                          navController.navigate(Routes.AddPet.route)

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Sign in")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }

}



