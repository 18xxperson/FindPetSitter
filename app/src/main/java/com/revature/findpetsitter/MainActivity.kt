package com.revature.findpetsitter

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.findpetsitter.ui.theme.FindPetSitterTheme
import kotlinx.coroutines.delay
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.room.Room
import com.revature.findpetsitter.ui.Addpet
import com.revature.findpetsitter.ui.Screen_ProfileDetails
import com.revature.findpetsitter.ui.chooseService
import com.revature.findpetsitter.ui.displayList
import com.revature.findpetsitter.ui.theme.FindPetSitterTheme
import com.revature.findpetsitter.viewmodel.SitterViewModel
import com.revature.findpetsitter.Routes
import com.revature.findpetsitter.ui.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sitterViewModel=ViewModelProvider(this).get(SitterViewModel::class.java)
        setContent {
            FindPetSitterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(sitterViewModel)

                }
            }
        }
    }
}


@Composable
fun Navigation(sitterViewModel: SitterViewModel) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route,
    ) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Routes.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Routes.SignIn.route) {

            SignIn(navController = navController)
        }
        composable(Routes.CreateAccount.route) {
            CreateAccount(navController = navController)
        }
        composable(Routes.AddPet.route){
            Addpet(navController)
        }
        composable(Routes.ChooseService.route){
            chooseService(navHostController = navController)
        }
        composable(Routes.AppointmentScreen.route) {
            AppointmentScreen(navController = navController)
        }
        composable(Routes.ProfileDetails.route+"/{firstname}/{lastname}/{type}/{rating}/{price}",
        arguments = listOf(
            navArgument("firstname")
            {
                type= NavType.StringType
            },
            navArgument("lastname")
            {
                type= NavType.StringType
            },
            navArgument("type")
            {
                type= NavType.StringType
            },
            navArgument("rating")
            {
                type= NavType.FloatType
            },
            navArgument("price")
            {
                type= NavType.FloatType
            }
        )) {
            val firstname=it.arguments?.getString("firstname")
            val lastname=it.arguments?.getString("lastname")
            val type=it.arguments?.getString("type")
            val rating=it.arguments?.getFloat("rating")
            val price=it.arguments?.getFloat("price")
            if (firstname != null) {
                if (lastname != null) {
                    if (type != null) {
                        if (rating != null) {
                            if (price != null) {
                                Screen_ProfileDetails(navHostController = navController,firstname,lastname,type,rating,price)
                            }
                        }
                    }
                }
            }
        }
        composable(Routes.Schedule.route) {
            ScheduleService(navController = navController)
        }
        composable(Routes.ListView.route+"/{type}",
            arguments = listOf(
                navArgument("type")
                {
                    type= NavType.StringType
                }
            ))
        {
            val type=it.arguments?.getString("type")
            if (type != null) {
                displayList(type = type, navController = navController,sitterViewModel = sitterViewModel)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    // Animation Effect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                } )
        )
        delay(5000L)
        navController.navigate(Routes.MainScreen.route)
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.project3logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .size(250.dp))
    }
}

@Composable
fun MainScreen(navController: NavController) {

    val context = LocalContext.current

    Column {

        Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.project3mainscreen),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxHeight()
                )

                Button(onClick = {navController.navigate(Routes.CreateAccount.route) }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(20.dp),
                shape = RoundedCornerShape(50.dp))
            {
                Text("Create Account", fontWeight = FontWeight.ExtraBold)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically)
            {
                Image(painter = painterResource(id = R.drawable.project3logo),
                    contentDescription = "Logo",
                    modifier = Modifier.padding(10.dp))

                ClickableText(text = AnnotatedString(text = "Sign In"),
                    onClick = { navController.navigate(Routes.ChooseService.route)
                    })
            } //end row

        } //end box
    } //end main column
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FindPetSitterTheme {
        MainScreen()
    }
}
*/


