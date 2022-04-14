package com.revature.findpetsitter.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sitters(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val firstname:String,
    @ColumnInfo val lastname:String,
    @ColumnInfo val rating:Double,
    @ColumnInfo val type:String,

    )
