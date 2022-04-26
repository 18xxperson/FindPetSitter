package com.revature.findpetsitter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "Sitters")
data class Sitters(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true) var id:Int,
    @SerializedName("firstname")
    @ColumnInfo(name = "firstname") var firstname:String,
    @SerializedName("lastname")
    @ColumnInfo(name = "lastname") var lastname:String,
    @SerializedName("type")
    @ColumnInfo(name = "type") var type:String,
    @SerializedName("rating")
    @ColumnInfo(name="rating") var rating:Double,
    @SerializedName("price") var price:Double

    )
