package com.revature.findpetsitter.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "password")
    var password: String = ""

    @ColumnInfo(name = "pets")
    var pets: Int = 0

    constructor() {}

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