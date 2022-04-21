package com.revature.findpetsitter.repository

import com.revature.findpetsitter.data.SittersList
import retrofit2.Response
import retrofit2.http.GET

interface ListAPIService {
   @GET("sitterlist")
   suspend fun getSitters(): Response<SittersList>
}
