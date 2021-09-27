package com.coding.mymvvmkoin.di


import androidx.room.Room
import com.coding.mymvvmkoin.data.GitHubRepository
import com.coding.mymvvmkoin.data.GitHubRoomDatabase
import com.coding.mymvvmkoin.di.network.GitHubAPI
import com.coding.mymvvmkoin.di.network.NetworkService
import com.coding.mymvvmkoin.ui.main.views.MainViewModel
import com.coding.mymvvmkoin.utilities.GIT_HUB_URL
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val myMvvmKoinModules = module {

    single {
        Room.databaseBuilder(androidApplication(), GitHubRoomDatabase::class.java, "github_database")
                .build()
    }



    factory { NetworkService() }

    factory { GitHubRepository(NetworkService().callAPI(GitHubAPI::class.java, GIT_HUB_URL),get<GitHubRoomDatabase>().gitHubDao()) }

    viewModel { MainViewModel(get()) }

}