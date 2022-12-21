package com.apps.foodordersimulation.ui.orderDetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apps.foodordersimulation.R
import com.apps.foodordersimulation.data.database.Food
import com.apps.foodordersimulation.databinding.FragmentFoodOrderDetailsBinding
import com.apps.foodordersimulation.utils.DateUtils
import com.apps.foodordersimulation.utils.OrderStatusColorEnum
import com.apps.foodordersimulation.utils.OrderStatusEnum

private const val ARG_PARAM1 = "param1"

class FoodOrderDetails : Fragment() {

    private lateinit var binding: FragmentFoodOrderDetailsBinding

    private var item: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodOrderDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setUpAnimation()
        return binding.root
    }

    private fun setUpAnimation() {
        item?.let {
            when (it.orderStatus) {
                0 -> {
                    binding.animationView.setAnimation(R.raw.placed)
                }

                1 -> {
                    binding.animationView.setAnimation(R.raw.preparing)
                }

                2 -> {
                    binding.animationView.setAnimation(R.raw.ready)
                }

                3 -> {
                    binding.animationView.setAnimation(R.raw.delivered)
                }
            }


            binding.orderId.text = "Order Id: ${it.id}"
            binding.orderName.text = "Order Name: ${it.itemName}"
            binding.orderStatus.text = "Order Status: ${OrderStatusEnum.fromValue(it.orderStatus)}"
            binding.orderDate.text = "Order Time: ${DateUtils.getDateTime(it.date)}"
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(item: Food) =
            FoodOrderDetails().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, item)
                }
            }
    }
}