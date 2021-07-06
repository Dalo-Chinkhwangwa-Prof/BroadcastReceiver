package com.dynamicdevz.dynamicbroadcast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamicdevz.dynamicbroadcast.databinding.GitItemLayoutBinding
import com.dynamicdevz.dynamicbroadcast.model.GitResponseItem

class GitAdapter: RecyclerView.Adapter<GitAdapter.GitViewHolder>() {
    var list: List<GitResponseItem> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class GitViewHolder(val binding: GitItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val binding = GitItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.binding.textView.text = list[position].name
    }

    override fun getItemCount(): Int = list.size
}