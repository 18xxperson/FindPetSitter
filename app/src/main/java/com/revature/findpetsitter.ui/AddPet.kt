package com.revature.findpetsitter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.revature.findpetsitter.BottNavBar
import com.revature.findpetsitter.Screens



@Composable
fun Addpet(navController: NavHostController)
{
    Scaffold(bottomBar = {
        BottNavBar(navController = navController)
    }) {

    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var name by remember{ mutableStateOf("")}
        var type by remember{ mutableStateOf("")}
        var description by remember{ mutableStateOf("")}
        Text(text = "Allowing visibility of your pet",fontSize = 25.sp)
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(value = name, onValueChange = {
            name=it
        },label = {
            Text(text = "name")
        })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = type, onValueChange = {
            type=it
        },label = {
            Text(text = "type")
        })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = description, onValueChange = {
            description=it
        },label = {
            Text(text = "description")
        })
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 56.dp)) {
            Text(text = "Add image")
        }
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add pet")
        }
    }
}
