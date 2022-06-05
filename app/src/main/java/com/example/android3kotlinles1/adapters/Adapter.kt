package com.example.android3kotlinles1.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android3kotlinles1.adapters.clickers.OnItemClick
import com.example.android3kotlinles1.databinding.ItemBinding
import com.example.android3kotlinles1.models.Model

class Adapter : ListAdapter<Model, Adapter.ViewHolder>(DiffUtilCallBack) {
    lateinit var onItemClick: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    fun onItemClickListener(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: Model) {
            binding.tvAny.text = model.anyText
            itemView.setOnClickListener {
                onItemClick.onItemClickListener(model)
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.anyText === newItem.anyText
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem === newItem
        }
    }
}