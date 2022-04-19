package com.revature.findpetsitter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.findpetsitter.R

@Composable
fun ProfileReview(fname:String, lname:String,rating:Float,headline:String,body:String,date:String) {
    Box(modifier = Modifier.fillMaxWidth(1f)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(left = 8.dp, right = 8.dp, top = 16.dp, bottom = 16.dp)
            ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(rating.toInt()) {
                    Image(
                        painter = painterResource(R.drawable.star3_ccexpress),
                        contentDescription = "Profile Picture",
                        Modifier
                            .size(27.dp)
                            .padding(0.dp)
                    )
                }
                repeat(5-rating.toInt()) {
                    Image(
                        painter = painterResource(R.drawable.emptystar_ccexpress),
                        contentDescription = "Profile Picture",
                        Modifier
                            .size(25.dp)
                            .padding(2.dp)
                    )
                }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = date,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.End
                    )

            }
            Row(Modifier.padding(vertical = 8.dp)) {
                Text(text = headline,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold)
            }
            Row() {
                Text(text = body,
                    style = MaterialTheme.typography.body1)
            }
            Row(Modifier.padding(vertical=8.dp)) {
                Text(text = fname + " " + lname,
                    color = Color.LightGray)
            }
        }
    }
}

@Preview
@Composable
fun previewReview() {
    ProfileReview(fname = "Carol", lname = "Williams", rating = 4.0f, headline = "Overall, pretty good service", body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
        "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ",date = "11/22/2021")
}