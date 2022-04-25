package com.revature.findpetsitter.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.revature.findpetsitter.BottNavBar
import com.revature.findpetsitter.Routes
import com.revature.findpetsitter.datastore.StoreUserId
import com.revature.findpetsitter.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.lang.Exception


@Composable
fun Addpet(navController: NavHostController,userViewModel: UserViewModel)
{
    val context= LocalContext.current
    val scope= rememberCoroutineScope()
    val id=StoreUserId(context).getId.collectAsState(initial = "0").value?.let{
        it.toInt()
    }
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
        Button(onClick = {
            if(description!=""&&type!=""&&name!="") {
                Toast.makeText(context, "Adding Pet Successful", Toast.LENGTH_LONG).show()

                    val allusers=userViewModel.readAllData().value.orEmpty()
                 //   val user=allusers[0]
                //    val users=userViewModel.readspecificuser(1).value.orEmpty()
                    val filuser = id?.let { userViewModel.readspecificuser(it) }?.value.orEmpty()
                    val user= filuser[0]
                    user.pets++
                    scope.launch {
                        userViewModel.insertUser(user)
                    }

            }
            else{
                Toast.makeText(context, "Please add to every field you have left empty", Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "Add pet")
        }
    }
}
