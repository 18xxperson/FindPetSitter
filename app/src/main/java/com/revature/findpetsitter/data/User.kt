package com.revature.findpetsitter.data

import androidx.annotation.NonNull
import androidx.compose.ui.text.input.TextFieldValue
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class User(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,

    @ColumnInfo(name = "email")
    var email: TextFieldValue,

    @ColumnInfo(name = "name")
    var name: TextFieldValue,

    @ColumnInfo(name = "password")
    var password: TextFieldValue,

    @ColumnInfo(name = "pets")
    var pets: Int = 0
)
/*    constructor() {}

    constructor(id: Int, email: String, name: String, password: String, pets: Int) {
        this.email = email
        this.name = name
        this.password = password
        this.pets = pets
    }
    constructor(email: String, name: String, password: String, pets: Int) {
        this.email = email
        this.name = name
        this.password = password
        this.pets = pets
    }
}

 */