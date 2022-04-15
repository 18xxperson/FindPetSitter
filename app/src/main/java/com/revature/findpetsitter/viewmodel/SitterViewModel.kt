package com.revature.findpetsitter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.revature.findpetsitter.data.Sitters
import com.revature.findpetsitter.repository.SitterRepository
import kotlinx.coroutines.launch

class SitterViewModel(appObj: Application): AndroidViewModel(appObj) {
    private val sitterRepository:SitterRepository=SitterRepository(appObj)
    fun insertSitter(sitters: Sitters)
    {
        viewModelScope.launch {
            sitterRepository.insertsitter(sitters)
        }
    }

    fun fetchtypeofsitter(type:String):LiveData<List<Sitters>>
    {
            return sitterRepository.fetchsittertype(type)
    }
}