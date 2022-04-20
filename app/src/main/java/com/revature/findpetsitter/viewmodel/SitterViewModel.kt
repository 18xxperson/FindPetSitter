package com.revature.findpetsitter.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.revature.findpetsitter.data.Sitters
import com.revature.findpetsitter.data.SittersList
import com.revature.findpetsitter.repository.RetroFitHelper
import com.revature.findpetsitter.repository.SitterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SitterViewModel: ViewModel() {
   // private val sitterRepository:SitterRepository=SitterRepository(appObj)
    private val fetchSittersLiveData= MutableLiveData<SittersList>()
    var sitterResultList: MutableState<List<Sitters>> = mutableStateOf(listOf())
    init {
        sitterlist()
    }
  //  fun insertSitter(sitters: Sitters)
  //  {
  //      viewModelScope.launch {
   //         sitterRepository.insertsitter(sitters)
   //     }
  //  }

   // fun fetchtypeofsitter(type:String):LiveData<List<Sitters>>
   // {
    //        return sitterRepository.fetchsittertype(type)
   // }

    fun sitterlist(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val sitterservice=RetroFitHelper.getAuthService()
                val responseService=sitterservice.getSitters()
                if(responseService.isSuccessful)
                {
                    responseService.body()?.let { it ->
                        sitterResultList.value = it.sitters
                        Log.d("Sitter Service",responseService.body().toString())
                    }
                }
                else {

                    responseService.errorBody()?.let { error ->
                        Log.d("Retrieval Error", "Response Token $error")
                        error.close()
                    }
                }
                fetchSittersLiveData.postValue(responseService.body())
            }
            catch(e:Exception) {
                Log.d("SitterSearch", "Exception ${e.message}")
            }
        }
    }
}