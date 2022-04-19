package com.revature.findpetsitter.ui

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppointmentScreen() {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colors.primary,
            title = { Text("Scheduled Services") })
    }) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            stickyHeader {
                Row(modifier = Modifier.fillMaxWidth().background(Color.LightGray)) {
                    Text(text = "Current Scheduled Services")
                }
            }
            items(currentappts) { appt ->
                ApptCard(fname=appt.fname,lname=appt.lname,startDate=appt.startDate,endDate=appt.endDate,price=appt.price,type=appt.type)
            }
        }
    }
}


@Composable
fun ApptCard(fname:String,lname:String,startDate:String,endDate:String,price:Float,type:String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Card() {
            Row() {
                Column() {
                    Row() {
                        Text(text = fname + " " + lname,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp))
                    }
                    Row() {
                        Text(text = startDate + "-" + endDate,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp))

                    }
                    Row() {
                        Text(text = "$" + price.toString()+ " (" + type + ")",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp))
                    }
                }
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "image",
                            tint = Color.Red, modifier = Modifier
                                .size(30.dp)
                                .clickable(onClick = {
                                    //edit appt
                                })
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "image",
                            tint = Color.Red, modifier = Modifier
                                .size(30.dp)
                                .clickable(onClick = {
                                    //delete appt
                                })
                        )

                }
            }


//            Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center) {
//                Row(Modifier.fillMaxWidth()) {

//                }

//            }
        }
    }



}

val currentappts = listOf(
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service")
)

@Preview
@Composable
fun previewAppointments() {
    AppointmentScreen()
//    ApptCard("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service")
}