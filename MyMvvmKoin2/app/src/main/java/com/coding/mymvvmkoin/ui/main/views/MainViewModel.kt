package com.coding.mymvvmkoin.ui.main.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.mymvvmkoin.data.GitHubRepository
import com.coding.mymvvmkoin.model.GitHubUserModel
import com.coding.mymvvmkoin.utilities.ERROR_EXCEPTION_MSG
import com.coding.mymvvmkoin.utilities.MY_MVVM_TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class MainViewModel(private val repository: GitHubRepository) : ViewModel() {
    companion object {
        private val TAG: String = MainViewModel::class.java.simpleName
    }

    private var _userList: MutableLiveData<ArrayList<GitHubUserModel>> = MutableLiveData()
    var userList: LiveData<ArrayList<GitHubUserModel>>

    init {
        userList = _userList
    }

    fun getGitHubUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {


                var gitHubUserList: java.util.ArrayList<GitHubUserModel>? = repository.getGitHubUsers()
                    ?: throw Exception(ERROR_EXCEPTION_MSG)

                if (gitHubUserList != null) {
                    gitHubUserList = sortUsers(gitHubUserList)
                }

                _userList.postValue(gitHubUserList)

            } catch (error: Exception) {
                val errorMsg = error.toString()
                Log.d(MY_MVVM_TAG, "Error: ${TAG}, $errorMsg")
                _userList.postValue(arrayListOf())
            }
        }
    }

    private fun sortUsers(gitHubUserList:ArrayList<GitHubUserModel>) :ArrayList<GitHubUserModel>{
            gitHubUserList?.sortWith { e1, e2 ->
                e2.username.toLowerCase(Locale.ROOT)
                    .compareTo(e1.username.toLowerCase(Locale.ROOT))
            }
            return gitHubUserList
    }
}