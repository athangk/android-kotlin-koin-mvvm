package com.coding.mymvvmkoin.ui.main.views

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import com.coding.mymvvmkoin.adapters.GitHubUserRecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.coding.mymvvmkoin.databinding.MainFragmentBinding
import com.coding.mymvvmkoin.model.GitHubUserModel
import com.coding.mymvvmkoin.utilities.MY_MVVM_TAG

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var adapter: GitHubUserRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)

        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerList(arrayListOf())
        mainFragmentBinding.noResults = true

        mainFragmentBinding.btnGetUsers.setOnClickListener {
            resetRecyclerList()
            mainFragmentBinding.noResults = false
            mainFragmentBinding.isLoading = true
            Handler().postDelayed({
                mainViewModel.getGitHubUsers()
            }, 1000)

        }

        mainViewModel.userList.observe(viewLifecycleOwner, { it ->
            if (it.isNotEmpty()) {
                updateRecyclerList(it)
            } else {
                mainFragmentBinding.noResults = true
            }
            mainFragmentBinding.isLoading = false

        })


    }

    private fun initRecyclerList(userRecyclerList: ArrayList<GitHubUserModel>) {

        mainFragmentBinding.userRecyclerList.visibility = View.VISIBLE
        adapter = GitHubUserRecyclerAdapter(userRecyclerList, requireContext())
        mainFragmentBinding.userRecyclerList.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainFragmentBinding.userRecyclerList.layoutManager = mLayoutManager
        mainFragmentBinding.userRecyclerList.adapter = adapter

        mainFragmentBinding.userRecyclerList.onFlingListener = null
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mainFragmentBinding.userRecyclerList)

    }

    private fun resetRecyclerList() {
        adapter.clearData()
    }

    private fun updateRecyclerList(userRecyclerList: ArrayList<GitHubUserModel>) {
        adapter.updateData(userRecyclerList)
    }


}


