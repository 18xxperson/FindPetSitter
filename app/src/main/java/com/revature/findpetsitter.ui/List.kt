package com.revature.findpetsitter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.findpetsitter.Routes
import com.revature.findpetsitter.data.Sitters
import com.revature.findpetsitter.viewmodel.SitterViewModel


@Composable
fun displayList(navController: NavController,type: String,sitterViewModel: SitterViewModel) {
      val list=sitterViewModel.sitterResultList.value
      list.forEach{
          sitterViewModel.insertSitter(it)
      }
    val datalist=sitterViewModel.getsitters().value
    //  val filterdata=sitterViewModel.fetchtypeofsitter(type)
     // val filterlist=filterdata.value
        LazyColumn()
        {
       //     if (filterlist != null) {
         //       items(items = filterlist.subList(0,filterlist.size)) { sitter ->
            if (datalist != null) {
                items(items = datalist.subList(0,datalist.size)){sitter ->

                    SitterCard(
                        firstname = sitter.firstname,
                        lastname = sitter.lastname,
                        rating = sitter.rating,
                        type, navController = navController
                    )

                }
            }

        }

}


@Composable
fun SitterCard(firstname:String,lastname:String,rating:Double,type:String,navController: NavController)
{
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
         //   .clickable { navController.navigate(Routes.ProfileDetails.route) }
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ){
       Column() {
           Text(text = "$firstname $lastname")
           Text("average rating: $rating")
           Text(text = type)
       }
    }
}

