package com.revature.findpetsitter.data.network

import com.google.gson.annotations.SerializedName

data class Review (
    @SerializedName("id")
    val reviewId:Int,
    @SerializedName("first_name")        //will convert to JSON
    val firstName:String,
    @SerializedName("last_name")
    val lastName:String,
    @SerializedName("rating")
    val rating:Float,
    @SerializedName("headline")
    val headline:String,
    @SerializedName("body")
    val body:String,
    @SerializedName("date")
    val date:String
        )