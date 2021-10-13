package com.coding.mymvvmkoin.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coding.mymvvmkoin.databinding.GithubUserItemBinding
import com.coding.mymvvmkoin.models.GitHubUserModel
import com.squareup.picasso.Picasso


class GitHubUserRecyclerAdapter() :
    ListAdapter<GitHubUserModel, GitHubUserRecyclerAdapter.GiHubUserViewHolder>(
        GitHubUserDiffCallback
    ) {

    private lateinit var binding: GithubUserItemBinding


    class GiHubUserViewHolder(binding: GithubUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var githubUserName: TextView = binding.githubUserName
        var githubUserImage: ImageView = binding.githubUserImage


        fun bind(gitHubUserModel: GitHubUserModel) {
            var currentGitHubUser = gitHubUserModel
            githubUserName.text = currentGitHubUser.username
            Picasso.get().load(currentGitHubUser.avatarURL).into(githubUserImage);
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiHubUserViewHolder {
        binding = GithubUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiHubUserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GiHubUserViewHolder, position: Int) {

        val currentUser = getItem(position)
        holder.bind(currentUser)

    }
}

object GitHubUserDiffCallback : DiffUtil.ItemCallback<GitHubUserModel>() {
    override fun areItemsTheSame(oldItem: GitHubUserModel, newItem: GitHubUserModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GitHubUserModel, newItem: GitHubUserModel): Boolean {
        return oldItem.username == newItem.username
    }
}