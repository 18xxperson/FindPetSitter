package com.revature.findpetsitter.ui

import android.annotation.SuppressLint
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.revature.findpetsitter.BottNavBar
import com.revature.findpetsitter.viewmodel.AppointmentsViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.LiveData
import com.revature.findpetsitter.data.Appointment
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppointmentScreen(navController: NavHostController, appointmentViewModel: AppointmentsViewModel) {
    var list = appointmentViewModel.fetchAllAppointments().observeAsState(listOf())

    Scaffold(topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colors.primary,
            title = {Text("Lawyer Search")})
    },
        bottomBar = {
            BottNavBar(navController)
        }) {
        Column(Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.95f)
            ) {
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp)
                            .background(Color.LightGray)
                    ) {
                        Text(modifier = Modifier
                            .padding(4.dp),
                            style = MaterialTheme.typography.body1,
                            text = "Scheduled Services")
                    }
                }

                items(list.value) { appt ->
                    Card(
                    ) {
                        Column() {
                            Text(text = appt.id.toString())
                            Text(text = appt.user_id.toString())
                            Text(text = appt.sitter_id.toString())
                            appt.start_date?.let { it1 -> Text(text = it1) }
                            appt.end_date?.let { it1 -> Text(text = it1) }
                            appt.service_type?.let { it1 -> Text(text = it1) }
                            Text(text = "$"+appt.total_price.toString())
                        }

                    //                    ApptCard(
//                        fname = appt.,
//                        lname = appt.lname,
//                        startDate = appt.startDate,
//                        endDate = appt.endDate,
//                        price = appt.price,
//                        type = appt.type
//                    )
                    }
                }
                stickyHeader {
                    ListDivier(text = "Past Services")
                }
                    items(pastappts) { appt ->
                        PastApptCard(
                            fname = appt.fname,
                            lname = appt.lname,
                            startDate = appt.startDate,
                            endDate = appt.endDate,
                            price = appt.price,
                            type = appt.type
                        )
                    }
                }
            }
        }
    }
//}

@Composable
fun ApptCard(fname:String,lname:String,startDate:String,endDate:String,price:Float,type:String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Card(Modifier.fillMaxWidth(),
            elevation = 6.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.padding(12.dp)) {
                    Row() {
                        Text(
                            text = fname + " " + lname,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    Row() {
                        Text(
                            text = startDate + "-" + endDate,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                    }
                    Row() {
                        Text(
                            text = "$" + price.toString() + " (" + type + ")",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
                Column(Modifier.fillMaxSize(),horizontalAlignment = Alignment.End) {
                    Row(horizontalArrangement = Arrangement.End) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "image",
                            tint = Color.Red, modifier = Modifier
                                .size(50.dp)
                                .padding(horizontal = 4.dp)
                                .clickable(onClick = {
                                    //edit appt
                                })
                        )
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "image",
                            tint = Color.Red, modifier = Modifier
                                .size(50.dp)
                                .padding(horizontal = 4.dp)
                                .clickable(onClick = {
                                    //delete appt
                                })
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PastApptCard(fname:String,lname:String,startDate:String,endDate:String,price:Float,type:String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Card(
            Modifier.fillMaxWidth(),
            elevation = 6.dp
        ) {
            Row() {
                Column(Modifier.padding(12.dp)) {
                    Row() {
                        Text(
                            text = fname + " " + lname,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    Row() {
                        Text(
                            text = startDate + "-" + endDate,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                    }
                    Row() {
                        Text(
                            text = "$" + price.toString() + " (" + type + ")",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListDivier(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .background(Color.LightGray)
    ) {
        Text(modifier = Modifier
            .padding(4.dp),
            style = MaterialTheme.typography.body1,
            text = "Past Services")
    }
}



val currentappts = listOf(
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service"),
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service")
)

val pastappts = listOf(
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service"),
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service"),
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service"),
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service"),
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service"),
    TestAppointment("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service")
)

@Preview
@Composable
fun previewAppointments() {
//    AppointmentScreen()
//    ApptCard("John","Smith","4/20/2022","4/25/2022",145.99f,"At-Home Service")
}