package com.coding.mymvvmkoin.data


import android.util.Log
import androidx.lifecycle.LiveData
import com.coding.mymvvmkoin.data.openHab.GitHubDao
import com.coding.mymvvmkoin.data.openHab.GitHubEntity
import com.coding.mymvvmkoin.network.GitHubAPI
import com.coding.mymvvmkoin.model.GitHubUserModel
import com.coding.mymvvmkoin.utilities.ERROR_EXCEPTION_MSG
import com.coding.mymvvmkoin.utilities.MY_MVVM_TAG
import java.util.*

class GitHubRepository(private val gitHubAPI: GitHubAPI, private val gitHubDao: GitHubDao) {


    // DATABASE OPERATIONS NO FUNCTIONALITY JUST IMPLEMENTED AS WITH DATABASE

    // INSERT
    suspend fun insertGiHubEntity(openHabStaticEntity: GitHubEntity) {
        return gitHubDao?.insertGitHubUser(openHabStaticEntity)
    }

    // DELETE
    suspend fun deleteGitHubEntities() {
        return gitHubDao?.deleteGitHubUsers()
    }

    // QUERY USERS
    suspend fun queryGiHubEntities(): LiveData<List<GitHubEntity>> {
        return gitHubDao.queryGitHubUsers()
    }

    // REST GET
    suspend fun getGitHubUsers(queryVal:String): ArrayList<GitHubUserModel>? {
        return try {

            val response = gitHubAPI.getGitHubUsers(queryVal)

            if (!response.isSuccessful) {
                throw Throwable(ERROR_EXCEPTION_MSG)
            }

            return response.body()?.items

        } catch (cause: Throwable) {
            Log.d(MY_MVVM_TAG, "Error :$cause")
            null
        }
    }
}