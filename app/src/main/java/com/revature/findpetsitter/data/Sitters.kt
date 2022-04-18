package com.revature.findpetsitter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sitters")
data class Sitters(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "firstname") val firstname:String,
    @ColumnInfo(name = "lastname") val lastname:String,
    @ColumnInfo(name = "rating") val rating:Double=0.0,
    @ColumnInfo(name="type") val type:String,

    )
