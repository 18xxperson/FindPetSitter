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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.revature.findpetsitter.data.PetDatabase

@Composable
fun displayList(type:String)
{
    val context= LocalContext.current
    val db = Room.databaseBuilder(
        context,
        PetDatabase::class.java, "Users database"
    ).build()
    val dao=db.sitterdao()
    val sitters=dao.getspecificSitters(type)
    LazyColumn(){
         items(sitters){  sitter->
             SitterCard(firstname = sitter.firstname,
                 lastname =sitter.lastname ,
                 rating = sitter.rating)

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
           Text("$rating")
       }
    }
}