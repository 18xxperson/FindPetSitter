package com.revature.findpetsitter.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.revature.findpetsitter.R
import com.revature.findpetsitter.Routes
import java.util.*


@Composable
fun ProfileDetailsCard(navHostController: NavHostController, firstName:String, lastName:String, type:String, aboutMe:String, price:Float, image:String, rating:Float, navController: NavController) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 20.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column() {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(140.dp)
                            .padding(8.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape),
                        contentScale = ContentScale.Fit
                    )
                    Row() {
                        Text(
                            text = rating.toString(),
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.star3_ccexpress),
                            contentDescription = "Profile Picture",
                            Modifier
                                .size(26.dp)
                                .padding(2.dp)
                        )
                    }
                }


                Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                    Text(
                        text = firstName + " " + lastName,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = type,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "$"+price.toInt().toString()+"/day",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row() {
                        Button(onClick = {
                            val calendar = Calendar.getInstance()
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH)
                            val day = calendar.get(Calendar.DAY_OF_MONTH)
                            calendar.time = Date()
                            val datePickerDialog = DatePickerDialog(
                                context,
                                { DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                                }, year, month, day
                            )
                            datePickerDialog.show()
                        }) {
                            Text(text = "See Availability")
                        }
                        Button(
                            onClick = { navController.navigate(Routes.Schedule.route) },
                            modifier = Modifier.padding(horizontal = 4.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff02a60f))) {
                            Icon(
                                painter = painterResource(R.drawable.outline_today_black_24dp),
                                contentDescription = "Schedule",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }


                }

            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                Text(
                    text = aboutMe,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp,top = 4.dp)
                )
            }
        }
    }
}
