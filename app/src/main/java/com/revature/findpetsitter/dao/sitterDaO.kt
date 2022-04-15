package com.revature.findpetsitter.dao

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

    @Query("SELECT * FROM Sitters WHERE type = :type")
    fun getspecificSitters(type:String):LiveData<List<Sitters>>

    @Insert
    fun insertSitter(sitters: Sitters)

    @Delete
    fun deleteSitter(sitters: Sitters)
}