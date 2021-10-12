package com.coding.mymvvmkoin.adapters


import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.coding.mymvvmkoin.databinding.GithubUserItemBinding
import com.coding.mymvvmkoin.model.GitHubUserModel
import com.squareup.picasso.Picasso
import androidx.lifecycle.ViewModel

class GitHubUserRecyclerAdapter(private var listData: ArrayList<GitHubUserModel>, private val mContext:Context) : RecyclerView.Adapter<GitHubUserRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: GithubUserItemBinding
    lateinit var viewContext: Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewContext = parent.context

        binding = GithubUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.githubUserName.text = listData[position].username
        Picasso.with(mContext).load(listData[position].avatarURL).into(holder.githubUserImage);

    }


    override fun getItemCount(): Int {
        return listData.size
    }


    fun clearData() {
        listData.clear()
        notifyDataSetChanged()
    }

    fun updateData(updatedUserData: ArrayList<GitHubUserModel>) {
        listData.clear()
        listData = updatedUserData
        notifyDataSetChanged()
    }

    class ViewHolder(binding: GithubUserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var githubUserName: TextView = binding.githubUserName
        var githubUserImage: ImageView = binding.githubUserImage


    }

}