package com.revature.findpetsitter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.revature.findpetsitter.Routes
import com.revature.findpetsitter.data.Sitters
import com.revature.findpetsitter.viewmodel.AppointmentsViewModel
import com.revature.findpetsitter.viewmodel.SitterViewModel


@Composable
fun displayList(navController: NavController,type: String,sitterViewModel: SitterViewModel, apptViewModel:AppointmentsViewModel) {
      var list=sitterViewModel.sitterResultList.value
      list=list.filter { it.type==type }
      list=list.sortedBy { it.rating }.asReversed()
   //   list.forEach{
    //      sitterViewModel.insertSitter(it)
    //  }
  //  val datalist=sitterViewModel.getsitters().value
    //  val filterdata=sitterViewModel.fetchtypeofsitter(type)
     // val filterlist=filterdata.value
        LazyColumn()
        {
       //     if (filterlist != null) {
         //       items(items = filterlist.subList(0,filterlist.size)) { sitter ->
      //      if (datalist != null) {
            //    items(items = datalist.subList(0,datalist.size)){sitter ->
                   items(items=list){sitter->
                    SitterCard(
                        firstname = sitter.firstname,
                        lastname = sitter.lastname,
                        rating = sitter.rating,
                        type, navController = navController,
                        price = sitter.price,
                        image = personlist[sitter.id% personlist.size],
                        viewModel = apptViewModel
                    )

              //  }
            }

        }

}


@Composable
fun SitterCard(firstname:String,lastname:String,rating:Double,type:String,navController: NavController,price:Double, viewModel:AppointmentsViewModel,image:String)
{
    var sitter: MutableState<Sitters>? = null
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.clickedSitter = mutableStateOf(
                    Sitters(
                        firstname = firstname,
                        lastname = lastname,
                        id=100,
                        rating = rating,
                        type=type,
                        price=price,
                    )
                )
                navController.navigate(
                    Routes.ProfileDetails.route+"/$firstname/$lastname/$type/$rating") }
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ){
        Row() {

            Column() {
                Text(text = "$firstname $lastname")
                Text("rating: $rating")
                Text(text = type)
                Text(text = "price: $$price per day")
            }
            AsyncImage(model =ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(), contentDescription = "")
        }
    }
}

var personlist= listOf("https://cdn.pixabay.com/photo/2013/03/14/06/08/person-93459_640.jpg",
    "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/dnmtn4ksijwyep0xmljk.jpg",
"http://www.themarketingsage.com/wp-content/uploads/2020/10/image.jpg",
    "https://blogs.bmj.com/ebn/files/2015/11/Professor-Brendan-McCormack-low-res-2-683x1024.jpg",
"https://get.pxhere.com/photo/person-people-girl-woman-hair-female-singer-brunette-portrait-model-young-fashion-clothing-lady-lip-hairstyle-smiling-smile-makeup-mouth-long-hair-make-up-dentist-dental-black-hair-face-nose-eyes-head-skin-lips-diversity-beauty-beautiful-teeth-wig-indian-attractive-lipstick-adult-cosmetics-persian-skincare-arabian-ethnicity-arab-photo-shoot-new-jersey-brown-hair-head-shot-hair-coloring-layered-hair-903073.jpg",
    "https://media.glamour.com/photos/56966e6716d0dc3747f0435b/master/pass/beauty-2014-04-lupita-nyongo-people-most-beautiful-woman-main.jpg",
  "https://cdn.acidcow.com/pics/20120426/most_beautiful_woman_01.jpg",
    "https://cdn.acidcow.com/pics/20120426/most_beautiful_woman_04.jpg",
    "https://theartsdesk.com/sites/default/files/mastimages/Roy_Andersson_Being_A_Human_Person.jpg",
    "https://www.csp.org.uk/sites/default/files/2_may_in_person_large.jpg",
"https://www.quizony.com/am-i-a-good-person/imageForSharing.jpg")

