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


class GitHubUserRecyclerAdapter(private val listData: ArrayList<GitHubUserModel>, private val mContext:Context) : RecyclerView.Adapter<GitHubUserRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: GithubUserItemBinding
    private val finalSize = listData.size - 1
    lateinit var viewContext: Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewContext = parent.context

        binding = GithubUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var posReverse = (finalSize+1) - (position + 1);
        holder.githubUserName.text = listData[posReverse].username

        Picasso.with(mContext).load(listData[posReverse].avatarURL).into(holder.githubUserImage);

    }


    override fun getItemCount(): Int {
        return listData.size
    }


    class ViewHolder(binding: GithubUserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var githubUserName: TextView = binding.githubUserName
        var githubUserImage: ImageView = binding.githubUserImage


    }

}