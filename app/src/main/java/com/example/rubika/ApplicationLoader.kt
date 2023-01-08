package com.example.rubika

import android.app.Application
import android.content.Context
import android.os.Handler
import com.example.rubika.repository.datasource.db.RoomDB
import com.example.rubika.utility.util.localizedContext
import ir.ha.dummy.utility.util.ThemeUtils
import kotlinx.coroutines.DelicateCoroutinesApi

class ApplicationLoader : Application() {

    companion object{
        var context :Context ?= null
        lateinit var applicationHandler : Handler
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        applicationHandler = Handler(this.mainLooper)

        // singleton db instance
        RoomDB.getDataBase(this.applicationContext)
        localizedContext(this)
        ThemeUtils.changeTheme(false)

    }

}


// Singleton Data Store
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref")