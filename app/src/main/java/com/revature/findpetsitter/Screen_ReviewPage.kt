package com.revature.findpetsitter

import androidx.compose.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun Screen_ReviewPage() {
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp),
            //.fillMaxWidth()
    ) {
        Surface (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text("Leave a Review", fontSize = 30.sp)
        }
        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 3.dp,
            startIndent = 10.dp
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val textState = remember{ mutableStateOf(TextFieldValue())}
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = {Text("Headline")},
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val textState = remember{ mutableStateOf(TextFieldValue())}
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = {Text("Brief Comments")}
            )
        }
        Surface (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
                Text(text = "Submit Review")
            }
        }



    }
}




@Preview
@Composable
fun testReviewPage() {
    Screen_ReviewPage()
}