package com.revature.findpetsitter.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserId(private val context: Context) {

    companion object {

        private val Context.myDataStoreObject: DataStore<Preferences> by preferencesDataStore("UserDataFile") //datastore file name
        val USER_ID_KEY= stringPreferencesKey("user_id") //key name to retrieve the data

    }

    val getId: Flow<String?> = context.myDataStoreObject.data
        .map {preferences ->

            preferences[USER_ID_KEY]?:"0"
        }

    suspend fun saveId(id:Int)
    {
        context.myDataStoreObject.edit { preferences ->

            preferences[USER_ID_KEY]=id.toString()
        }
    }

}