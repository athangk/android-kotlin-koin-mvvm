package com.coding.mymvvmkoin

import android.app.Application
import com.coding.mymvvmkoin.di.module.repoViewModule
import com.coding.mymvvmkoin.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf( networkModule, repoViewModule))
        }


    }

}