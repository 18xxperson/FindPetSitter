package com.revature.findpetsitter.repository

import com.revature.findpetsitter.data.Token
import com.revature.findpetsitter.data.network.SitterReviews
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ReviewsAPIService {
    @GET("reviews")
    suspend fun getReviewList(@Body auth_token: Token): Response<SitterReviews>
}