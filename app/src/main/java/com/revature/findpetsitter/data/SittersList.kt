package com.revature.findpetsitter.data

import com.google.gson.annotations.SerializedName

data class SittersList (
    @SerializedName("sitterlist")
    val sitters:List<Sitters>
)