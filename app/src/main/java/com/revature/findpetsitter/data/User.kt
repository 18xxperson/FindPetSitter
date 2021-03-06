package com.revature.findpetsitter.data

import androidx.annotation.NonNull
import androidx.compose.ui.text.input.TextFieldValue
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "User_list")
data class  User(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "pets")
    var pets: Int = 0
)



