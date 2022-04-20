package com.revature.findpetsitter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Sitters(
    @SerializedName("id")val id:Int,
    @SerializedName("firstname")val firstname:String,
    @SerializedName("lastname")val lastname:String,
    @SerializedName("type")val type:String,
    @SerializedName("rating")val rating:Double

    )
