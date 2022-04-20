package com.revature.findpetsitter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Sitters")
data class Sitters(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true) val id:Int,
    @SerializedName("firstname")
    @ColumnInfo(name = "firstname")val firstname:String,
    @SerializedName("lastname")
    @ColumnInfo(name = "lastname") val lastname:String,
    @SerializedName("type")
    @ColumnInfo(name = "type")val type:String,
    @SerializedName("rating")
    @ColumnInfo(name="rating")val rating:Double

    )
