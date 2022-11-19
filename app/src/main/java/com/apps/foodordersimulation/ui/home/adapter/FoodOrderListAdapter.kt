package com.apps.foodordersimulation.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.foodordersimulation.data.database.Food
import com.apps.foodordersimulation.databinding.OrderItemRowBinding
import com.apps.foodordersimulation.utils.OrderStatusColorEnum
import com.apps.foodordersimulation.utils.OrderStatusEnum

class FoodOrderListAdapter(private val onItemClicked: (Food) -> Unit, private val onOrderClicked: (Food) -> Unit) : ListAdapter<Food, FoodOrderListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewHolder = ViewHolder(
            OrderItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        viewHolder.statusBtn.setOnClickListener {
            val position = viewHolder.adapterPosition
            if(position > -1) {
                onItemClicked(getItem(position).copy())
            }
        }

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if(position > -1) {
                onOrderClicked(getItem(position).copy())
            }
        }
        return viewHolder
    }


    class ViewHolder(private var binding: OrderItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val statusBtn = binding.status
        val order = binding.orderContainer
        fun bind(item: Food) {
            binding.orderName.text = ("Order Id:" +item.id).toString()
            binding.status.text = OrderStatusEnum.fromValue(item.orderStatus).toString()
            binding.status.setBackgroundColor(Color.parseColor(OrderStatusColorEnum.valueOf(binding.status.text as String).color))
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem.id == newItem.id && oldItem.orderStatus == newItem.orderStatus
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}