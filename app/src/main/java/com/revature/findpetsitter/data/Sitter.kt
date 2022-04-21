package com.revature.findpetsitter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sitters")
data class Sitter(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "firstname")val firstname:String,
    @ColumnInfo(name = "lastname") val lastname:String,
    @ColumnInfo(name = "type")val type:String,
    @ColumnInfo(name="rating")val rating:Double,
    @ColumnInfo(name="price")val price:Double
)
