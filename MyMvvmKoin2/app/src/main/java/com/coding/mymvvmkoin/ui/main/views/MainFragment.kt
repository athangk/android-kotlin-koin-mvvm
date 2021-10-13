package com.coding.mymvvmkoin.ui.main.views

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.coding.mymvvmkoin.adapters.GitHubUserRecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.coding.mymvvmkoin.databinding.MainFragmentBinding
import com.coding.mymvvmkoin.models.GitHubUserModel

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
            adapter.submitList(arrayListOf())
            mainFragmentBinding.noResults = false
            mainFragmentBinding.isLoading = true
            Handler().postDelayed({
                mainViewModel.getGitHubUsers()
            }, 1000)

        }

        mainViewModel.userList.observe(viewLifecycleOwner, { it ->
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            } else {
                mainFragmentBinding.noResults = true
            }
            mainFragmentBinding.isLoading = false

        })


    }

    private fun initRecyclerList(userRecyclerList: ArrayList<GitHubUserModel>) {

        mainFragmentBinding.userRecyclerList.visibility = View.VISIBLE
        adapter = GitHubUserRecyclerAdapter()
        mainFragmentBinding.userRecyclerList.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainFragmentBinding.userRecyclerList.layoutManager = mLayoutManager
        mainFragmentBinding.userRecyclerList.adapter = adapter

        mainFragmentBinding.userRecyclerList.onFlingListener = null
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mainFragmentBinding.userRecyclerList)

    }


}


