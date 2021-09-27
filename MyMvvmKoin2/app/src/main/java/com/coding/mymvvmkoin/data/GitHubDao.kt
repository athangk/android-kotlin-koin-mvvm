package com.coding.mymvvmkoin.data.openHab


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GitHubDao {

    @Query("SELECT * from github_table")
    fun queryGitHubUsers(): LiveData<List<GitHubEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGitHubUser(gitHubEntity: GitHubEntity)

    @Query("DELETE FROM github_table")
    suspend fun deleteGitHubUsers()

}