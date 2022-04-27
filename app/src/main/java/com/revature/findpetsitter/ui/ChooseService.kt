package com.revature.findpetsitter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.revature.findpetsitter.BottNavBar
import com.revature.findpetsitter.Routes

@Composable
fun chooseService(navHostController: NavHostController)
{
    Scaffold(bottomBar = {
        BottNavBar(navController = navHostController)
    }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "What kind of service would you like?", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { navHostController.navigate(Routes.ListView.route + "/HouseSitter") },modifier = Modifier.fillMaxWidth()) {
                        Text(text = "HouseSitting")
                    }
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                        .data("https://www.theconstantrambler.com/wp-content/uploads/2015/04/housesitting-1280x851.jpg")
                        .crossfade(true)
                        .build(),
                        contentDescription = "",modifier = Modifier.size(150.dp))

                    Button(onClick = { navHostController.navigate(Routes.ListView.route + "/Drop Off") },modifier=Modifier.fillMaxWidth()) {
                        Text(text = "Drop Off")
                    }
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                        .data("https://www.samfordpetresort.com.au/wp-content/uploads/2017/01/petpickupservice-feat.jpg")
                        .crossfade(true)
                        .build(),
                        contentDescription = "",modifier=Modifier.size(150.dp))

                    Button(onClick = { navHostController.navigate(Routes.ListView.route + "/Kennel Service") },modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Kennel Service")
                    }
                        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                            .data("https://i.ebayimg.com/images/i/291982831046-0-1/s-l1000.jpg")
                            .crossfade(true)
                            .build(),
                            contentDescription = "",modifier = Modifier.size(150.dp))

                    Button(onClick = {
                        navHostController.navigate(Routes.ListView.route + "/Pet Daycare")
                    },modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Pet Daycare")
                    }
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                        .data("https://s3-us-west-2.amazonaws.com/dogtopia-com/wp-content/uploads/2018/08/DogtopiaScottsdale_0442.jpg")
                        .crossfade(true)
                        .build(),
                        contentDescription = "",modifier = Modifier
                            .size(140.dp)
                            .padding(horizontal = 5.dp))


            }

    }
}