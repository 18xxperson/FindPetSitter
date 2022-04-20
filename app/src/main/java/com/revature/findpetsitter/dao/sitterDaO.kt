package com.revature.findpetsitter.dao

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.revature.findpetsitter.data.Sitters

@Dao
interface sitterDaO {
    @Query("SELECT * FROM Sitters")
    fun getSitters():LiveData<List<Sitters>>

    @Query("SELECT * FROM Sitters WHERE type = :types")
    fun getspecificSitters(types:String): LiveData<List<Sitters>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSitter(sitters: Sitters)

    @Delete
    fun deleteSitter(sitters: Sitters)
}