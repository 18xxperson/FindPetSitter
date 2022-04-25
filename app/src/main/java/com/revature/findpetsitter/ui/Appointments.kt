package com.revature.findpetsitter.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppointmentScreen(navController: NavHostController, appointmentViewModel: AppointmentsViewModel) {
    var list = appointmentViewModel.fetchAllAppointments().observeAsState(listOf())
    val formatter = DateTimeFormatter.ofPattern("M/dd/yyyy")

//    var currentAppts = getCurrentAppts(list)
//    var pastAppts = getPastAppts(list)


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
                            .padding(bottom = 4.dp)
                            .background(Color.LightGray)
                    ) {
                        Text(modifier = Modifier
                            .padding(4.dp),
                            style = MaterialTheme.typography.body1,
                            text = "Scheduled Services")
                    }
                }

                    //current appts
                items(list.value.filter { LocalDate.parse(it.end_date,formatter) > LocalDate.now() }) { appt ->
                    ApptCard(
                        id = appt.id,
                        appointmentViewModel = appointmentViewModel,
                        fname = "Julie",
                        lname = "Doddson",
                        startDate = appt.start_date!!,
                        endDate = appt.end_date!!,
                        price = appt.total_price,
                        type = appt.service_type!!
                    )
                }
                stickyHeader {
                    ListDivier(text = "Past Services")
                }
                    items(list.value.filter { LocalDate.parse(it.end_date,formatter) < LocalDate.now() }) { appt ->
                        PastApptCard(
                            fname = "Julie",
                            lname = "Doddson",
                            startDate = appt.start_date!!,
                            endDate = appt.end_date!!,
                            price = appt.total_price,
                            type = appt.service_type!!
                        )
                    }
                }
            }
        }
    }

//@RequiresApi(Build.VERSION_CODES.O)
//fun getPastAppts(list: State<List<Appointment>>): State<List<Appointment>> {
//    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
//
//    return list.value.filter {
//        //filter all appointments when end_date in the past
//        LocalDate.parse(it.end_date,formatter) < LocalDate.now()
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//fun getCurrentAppts(list: State<List<Appointment>>): State<List<Appointment>> {
//    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
//
//    list.value.filter {
//        //filter all appointments when end_date in the future
//       LocalDate.parse(it.end_date,formatter) > LocalDate.now()
//    }
//    return list.
//}
////}

@Composable
fun ApptCard(appointmentViewModel: AppointmentsViewModel,id:Int,fname:String,lname:String,startDate:String,endDate:String,price:Float,type:String) {
    val scope= rememberCoroutineScope()

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
                                    scope.launch {
                                        appointmentViewModel.deleteAppointmentById(id)
                                    }
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