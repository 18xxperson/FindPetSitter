package com.revature.findpetsitter.ui

import android.app.DatePickerDialog
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
import java.util.*

@Composable
fun ScheduleService(navController: NavHostController) {
    var startDate by remember {mutableStateOf("")}
    var endDate by remember {mutableStateOf("")}
    val context = LocalContext.current

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
                    OrderDetails(navController,"Shirley", "Williams", "At-Home Service", 45f, 5)
                }
            }
        }
    }
}

@Composable
fun OrderDetails(navController:NavHostController,firstName:String,lastName:String,type:String,price:Float, numDays:Int) {
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
                text = "x " + numDays.toString() + " days",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End
            )
        }

        Divider(color = Color.LightGray)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            text = "Subtotal: $${numDays * price}",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Column(Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            navController.navigate(Routes.AppointmentScreen.route)
        },
            modifier = Modifier.padding(top = 20.dp, bottom = 40.dp)) {
            Text(text = "Schedule")
        }
    }


}

@Composable
fun AlertDiaglog(/*pass in values to post*/) {


}

@Preview
@Composable
fun previewSchedule() {
//    ScheduleService()
}