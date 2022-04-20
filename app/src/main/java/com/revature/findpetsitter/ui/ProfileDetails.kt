package com.revature.findpetsitter.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Screen_ProfileDetails(navHostController: NavHostController) {

    Scaffold(topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colors.primary,
            title = { Text("Profile Details") })
    }) {

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.95f),
        ) {
            stickyHeader {
                Column(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    ProfileDetailsCard(
                        navHostController,
                        firstName = "James",
                        lastName = "Smith",
                        type = "Kennel Service",
                        aboutMe = "This is the profile bio , consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                "ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                        price = 75f,
                        image = "https://static01.nyt.com/images/2019/11/17/books/review/17Salam/Salam1-superJumbo.jpg",
                        rating = 4.5f,
                        navHostController
                    )
                }
            }
            items(testReviewList) { review ->
                Divider(color = Color.DarkGray)
                ProfileReview(fname = review.firstName, lname = review.lastName, rating = review.rating,
                    headline = review.headline, body = review.body, date = review.date)
            }
        }
    }
}

