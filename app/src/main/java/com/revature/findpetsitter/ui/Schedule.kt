package com.revature.findpetsitter.ui

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.revature.findpetsitter.R
import com.revature.findpetsitter.Routes
import com.revature.findpetsitter.data.Appointment
import com.revature.findpetsitter.datastore.StoreUserId
import com.revature.findpetsitter.viewmodel.AppointmentsViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleService(navController: NavHostController, appointmentViewModel: AppointmentsViewModel) {
    val context = LocalContext.current
    val id = StoreUserId(context).getId.collectAsState(initial="0").value?.let{
        it.toInt()
    }
    val formatter = DateTimeFormatter.ofPattern("M/d/yyyy")

    var startDate by remember {mutableStateOf("4/26/2022")}
    var endDate by remember {mutableStateOf("4/26/2022")}


    Scaffold(topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colors.primary,
            title = { Text("Schedule Service") })
    }) {
        Column(Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {


            Card(
                Modifier.padding(10.dp),
                elevation = 25.dp
            ) {
                Column(Modifier.padding(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {
                        OutlinedTextField(
                            value = startDate,
                            onValueChange = { startDate = it },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(175.dp),
                            label = { Text("Start Date") },
                            readOnly = true,
                            singleLine = true
                        )
                        Button(
                            onClick = {
                                val calendar = Calendar.getInstance()
                                val year = calendar.get(Calendar.YEAR)
                                val month = calendar.get(Calendar.MONTH)
                                val day = calendar.get(Calendar.DAY_OF_MONTH)
                                calendar.time = Date()
                                val datePickerDialog = DatePickerDialog(
                                    context,
                                    { DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                                        startDate = "${month + 1}/$dayOfMonth/$year"
                                    }, year, month, day
                                )
                                datePickerDialog.show()
                            },
                            modifier = Modifier.padding(horizontal = 4.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff02a60f))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_today_black_24dp),
                                contentDescription = "Schedule",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = endDate,
                            onValueChange = { endDate = it },
                            modifier = Modifier
                                .padding(8.dp)
                                .width(175.dp),
                            label = { Text("End Date") },
                            readOnly = true,
                            singleLine = true
                        )
                        Button(
                            onClick = {
                                val calendar = Calendar.getInstance()
                                val year = calendar.get(Calendar.YEAR)
                                val month = calendar.get(Calendar.MONTH)
                                val day = calendar.get(Calendar.DAY_OF_MONTH)
                                calendar.time = Date()
                                val datePickerDialog = DatePickerDialog(
                                    context,
                                    { DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                                        endDate = "${month + 1}/$dayOfMonth/$year"
                                    }, year, month, day
                                )
                                datePickerDialog.show()
                            },
                            modifier = Modifier.padding(horizontal = 4.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff02a60f))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_today_black_24dp),
                                contentDescription = "Schedule",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    if (id != null) {
                        OrderDetails(navController,appointmentViewModel,id,appointmentViewModel.clickedSitter!!.value.firstname,appointmentViewModel.clickedSitter!!.value.lastname, appointmentViewModel.clickedSitter!!.value.type, 45f, startDate,endDate)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderDetails(navController:NavHostController,appointmentViewModel:AppointmentsViewModel,id:Int,firstName:String,lastName:String,type:String,price:Float, start_date:String,end_date:String) {
    val scope= rememberCoroutineScope()
    val days = dayDifference(start_date,end_date)

    //fake appointment to insert to db for testing
    var appointment = Appointment(
        user_id = id,  //load data store w userID
        sitter_id = 863,
        sitter_name = firstName + " " + lastName,
        start_date = start_date,
        end_date = end_date,
        service_type = type,
        total_price = days*price
    )

    Column(Modifier.padding(horizontal = 55.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            text = firstName + " " + lastName,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = type,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)) {
            Text(
                text = "$" + price.toString() + "/day",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Left
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "x " + days.toString() + " days",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End
            )
        }

        Divider(color = Color.LightGray)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            text = "Total: $" + (100*appointment.total_price).roundToInt()/100,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Column(Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            //insert fake appt into db
            scope.launch {
                appointmentViewModel.insertAppointment(appointment)
            }
            navController.navigate(Routes.AppointmentScreen.route)
        },
            modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)) {
            Text(text = "Schedule")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun dayDifference(start:String, end:String):Int {
    val formatter = DateTimeFormatter.ofPattern("M/d/yyyy")

    var startDate = LocalDate.parse(start,formatter)
    var endDate = LocalDate.parse(end,formatter)
    return ChronoUnit.DAYS.between(startDate,endDate).toInt()
}

@Preview
@Composable
fun previewSchedule() {
//    ScheduleService()
}