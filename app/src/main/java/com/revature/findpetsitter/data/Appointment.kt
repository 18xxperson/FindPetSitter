package com.revature.findpetsitter.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment")
data class Appointment(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int=0,        //auto-generated id

    @ColumnInfo(name="user_id")
    val user_id:Int,
    @ColumnInfo(name="sitter_id")
    val sitter_id:Int,
    @ColumnInfo(name="sitter_name")
    val sitter_name:String?=null,
    @ColumnInfo(name="start_date")
    val start_date:String?=null,
    @ColumnInfo(name="end_date")
    val end_date:String?=null,
    @ColumnInfo(name="service_type")
    val service_type:String?=null,
    @ColumnInfo(name="total_price")
    val total_price:Float

)