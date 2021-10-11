package com.example.abuseyou.modules.list.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.abuseyou.databinding.ListItemBinding
import com.example.abuseyou.db.model.AbuseModel

class MyListAdapter(
    private val itemClickCallback: (Int) -> Unit
) :
    ListAdapter<AbuseModel, MyListAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private val view: ListItemBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(
            item: AbuseModel,
            clickCallback: (Int) -> Unit
        ) {
            view.textView.text = item.insult
            view.textView2.text = item.created
            view.textView3.setOnClickListener { clickCallback(item.id) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], itemClickCallback)
    }

    object DiffCallback : DiffUtil.ItemCallback<AbuseModel>() {
        override fun areItemsTheSame(oldItem: AbuseModel, newItem: AbuseModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AbuseModel,
            newItem: AbuseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}