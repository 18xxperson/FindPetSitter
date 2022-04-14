package com.revature.findpetsitter

import androidx.compose.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties

@Composable
fun Screen_ReviewPage() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Leave a Review", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },
        content = {myContent()}
    )

}

@Composable
fun myContent(){

    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp),
        //.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Surface(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
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
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            val textState = remember{ mutableStateOf(TextFieldValue())}
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = {Text("Brief Comments")}
            )
        }
        DropDownMenu()
        Surface (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Button(onClick = {}, modifier = Modifier.padding(20.dp)) {
                Text(text = "Submit Review")
            }
        }
    }
}

@Composable
fun DropDownMenu() {
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mCities = listOf("1","2","3","4","5")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text("Score")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}


@Preview
@Composable
fun testReviewPage() {
    Screen_ReviewPage()
}