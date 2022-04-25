package com.revature.findpetsitter.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.revature.findpetsitter.viewmodel.ProfileDetailsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Screen_ProfileDetails(navController: NavController, viewModel:ProfileDetailsViewModel) {
    viewModel.reviewsList()
//    val firstName = navController.currentBackStackEntry?.arguments?.getString("firstname")
//    val lastName = navController.currentBackStackEntry?.arguments?.getString("lastname")
//    val type = navController.currentBackStackEntry?.arguments?.getString("type")
//    val price = navController.currentBackStackEntry?.arguments?.getDouble("price")?.toFloat()
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            title = { Text("Profile Details") })
    }) {

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        ) {
            stickyHeader {
                Column(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    ProfileDetailsCard(
                        navController,
                        firstName = "John",//firstName!!,
                        lastName = "Smith",///lastName!!,
                        type = "Pet Daycare",//type!!,
                        aboutMe = "This is the profile bio , consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                "ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                        price = 100f,
                        image = "https://static01.nyt.com/images/2019/11/17/books/review/17Salam/Salam1-superJumbo.jpg",
                        rating = 4.5f
                    )
                }
            }
            items(viewModel.reviewsResultList.value) { review ->
                Divider(color = Color.DarkGray)
                ProfileReview(fname = review.firstName, lname = review.lastName, rating = review.rating,
                    headline = review.headline, body = review.body, date = review.date)
            }
        }
    }
}



