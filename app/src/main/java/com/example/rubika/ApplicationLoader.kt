package com.example.rubika

import android.app.Application
import android.content.Context
import android.os.Handler
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.*

class ApplicationLoader : Application() {

    companion object{
        var context :Context ?= null
        lateinit var applicationHandler : Handler
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        applicationHandler = Handler(this.mainLooper)

        /** 1- Koin -> modules.. */
        val myModules = module {

        }


        /** 2- Start Coin By Modules... */
        startKoin {
            androidContext(this@ApplicationLoader)
            modules(myModules)
        }


        /** initialize fresco */
//        RoomDB.getDataBase(this)



    }

}


// Singleton Data Store
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")