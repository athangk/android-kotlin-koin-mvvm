package com.coding.mymvvmkoin

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.coding.mymvvmkoin.di.myMvvmKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            androidLogger()
            androidContext(this@App)
            modules(myMvvmKoinModules)
        }


    }

}