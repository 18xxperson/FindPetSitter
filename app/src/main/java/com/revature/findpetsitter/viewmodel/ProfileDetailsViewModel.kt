package com.revature.findpetsitter.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.findpetsitter.data.Token
import com.revature.findpetsitter.data.network.Review
import com.revature.findpetsitter.data.network.SitterReviews
import com.revature.findpetsitter.repository.RetroFitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileDetailsViewModel: ViewModel() {
    private val fetchReviewsLiveData= MutableLiveData<SitterReviews>()
    var reviewsResultList: MutableState<List<Review>> = mutableStateOf(listOf())

    init {
        reviewsList()
    }

    fun reviewsList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val reviewService = RetroFitHelper.getReviewService()

                val responseService = reviewService.getReviewList()

                if (responseService.isSuccessful) {
                    //if you receive a response from server
                    responseService.body()?.let { it ->
                        reviewsResultList.value = it.reviews
                        Log.d("REVIEWS SERVICE","WAS SUCCESSFULL,,!!")
                        Log.d("Reviews Service", responseService.body().toString())
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        Log.d("Retrieval Error", "Response Token $error")
                        error.close()
                    }
                }


                fetchReviewsLiveData.postValue(responseService.body())
            } catch (e: Exception) {
                Log.d("LawyerSearch", "Exception")
            }
        }
    }
}