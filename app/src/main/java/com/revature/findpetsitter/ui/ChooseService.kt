package com.revature.findpetsitter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.revature.findpetsitter.BottNavBar
import com.revature.findpetsitter.Routes

@Composable
fun chooseService(navHostController: NavHostController)
{
    Scaffold(bottomBar = {
        BottNavBar(navController = navHostController)
    }) {
        Column() {
            Text(text = "What kind of service would you like?",fontSize = 25.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { navHostController.navigate(Routes.ListView.route+"/HouseSitter") }) {
                Text(text = "HouseSitting")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { navHostController.navigate(Routes.ListView.route+"/Kettle Service") }) {
                Text(text = "Kettle Service")
            }
        }
    }
}