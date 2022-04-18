package com.revature.findpetsitter.dao

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.revature.findpetsitter.data.Sitters

@Dao
interface sitterDaO {
    @Query("SELECT * FROM Sitters")
    fun getSitters():List<Sitters>

    @Query("SELECT * FROM Sitters WHERE type=:types")
    fun getspecificSitters(types:String):LiveData<List<Sitters>>

    @Insert
    suspend fun insertSitter(sitters: Sitters)

    @Delete
    fun deleteSitter(sitters: Sitters)
}