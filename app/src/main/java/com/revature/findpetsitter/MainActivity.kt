package com.revature.findpetsitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.revature.findpetsitter.data.PetDatabase
import com.revature.findpetsitter.ui.Addpet
import com.revature.findpetsitter.ui.Screen_ProfileDetails
import com.revature.findpetsitter.ui.displayList
import com.revature.findpetsitter.ui.theme.FindPetSitterTheme
import com.revature.findpetsitter.viewmodel.SitterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val sitterViewModel=ViewModelProvider(this).get(SitterViewModel::class.java)
            FindPetSitterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    displayList(type = "HouseSitter",sitterViewModel)
                }
            }
        }
    }
}


