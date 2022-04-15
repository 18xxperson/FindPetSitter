package com.revature.findpetsitter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.findpetsitter.viewmodel.SitterViewModel

@Composable
fun displayList(type:String,sitterViewModel: SitterViewModel) {
    val list = sitterViewModel.fetchtypeofsitter(type).value
    LazyColumn()
    {

        if (list != null) {
            items(items = list.subList(0,list.size)) { sitter ->
                SitterCard(
                    firstname = sitter.firstname,
                    lastname = sitter.lastname,
                    rating = sitter.rating
                )

            }
        }
    }
}


@Composable
fun SitterCard(firstname:String,lastname:String,rating:Double)
{
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ){
       Column() {
           Text(text = "$firstname $lastname")
           Text("rating: $rating")
       }
    }
}

@Preview
@Composable
fun PreviewCard()
{
    SitterCard(firstname = "Jon", lastname = "Work", rating = 3.5)
}