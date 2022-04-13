package com.revature.findpetsitter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.revature.findpetsitter.dao.sitterDaO

@Database(entities = [Sitters::class],version = 1)
abstract class PetDatabase :RoomDatabase() {
    abstract fun sitterdao():sitterDaO
}