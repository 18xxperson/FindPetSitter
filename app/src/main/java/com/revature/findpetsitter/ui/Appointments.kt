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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.revature.findpetsitter.BottNavBar
import com.revature.findpetsitter.viewmodel.AppointmentsViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.revature.findpetsitter.data.Appointment


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppointmentScreen(navController: NavHostController, appointmentViewModel: AppointmentsViewModel) {
    val id:Int=0
    val appointment = Appointment(
        user_id = 123,
        sitter_id = 555,
        start_date = "5/23/2022",
        end_date = "5/28/2022",
        service_type = "At-Home Service",
        total_price = 145.50f
    )
    appointmentViewModel.insertAppointment(appointment)
    var apptList = appointmentViewModel.fetchAppointmentsById(123).observeAsState(listOf())

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

                items(currentappts) { appt ->
                    ApptCard(
                        fname = appt.fname,
                        lname = appt.lname,
                        startDate = appt.startDate,
                        endDate = appt.endDate,
                        price = appt.price,
                        type = appt.type
                    )
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
            .padding(top = 8.dp,bottom = 8.dp)
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