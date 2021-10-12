package com.coding.mymvvmkoin.di.module

import androidx.room.Room
import com.coding.mymvvmkoin.data.GitHubRepository
import com.coding.mymvvmkoin.data.GitHubRoomDatabase
import com.coding.mymvvmkoin.data.openHab.GitHubDao
import com.coding.mymvvmkoin.ui.main.views.MainViewModel
import com.coding.mymvvmkoin.utilities.GIT_HUB_DATABASE
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repoViewModule = module {

    single {
        Room.databaseBuilder(androidApplication(), GitHubRoomDatabase::class.java, GIT_HUB_DATABASE)
            .build()
    }

    single { provideGitHubDao(get())}
    single { GitHubRepository(get(),get()) }

    viewModel { MainViewModel(get()) }

}

fun provideGitHubDao(database: GitHubRoomDatabase): GitHubDao {
    return  database.gitHubDao()
}