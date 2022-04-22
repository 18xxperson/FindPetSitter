package com.revature.findpetsitter.data.network

import com.revature.findpetsitter.data.network.Review
import com.google.gson.annotations.SerializedName

data class SitterReviews (
    @SerializedName("reviews")
    val reviews:List<Review>
) {

}