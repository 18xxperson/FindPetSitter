package com.revature.findpetsitter.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.revature.findpetsitter.dao.sitterDaO
import com.revature.findpetsitter.data.PetDatabase
import com.revature.findpetsitter.data.Sitters

class SitterRepository(application: Application) {
    private var sitterDaO: sitterDaO
    init {

        var database=PetDatabase.getDatabase(application)
        sitterDaO=database.sitterdao()

    }
    fun fetchsittertype(type:String):LiveData<List<Sitters>>
    {
        return sitterDaO.getspecificSitters(type = type)
    }

    suspend fun insertsitter(sitters: Sitters)
    {
        sitterDaO.insertSitter(sitters)
    }
}