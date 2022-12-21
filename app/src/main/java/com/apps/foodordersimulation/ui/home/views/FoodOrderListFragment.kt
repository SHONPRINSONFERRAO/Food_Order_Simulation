package com.apps.foodordersimulation.ui.home.views

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.coroutineScope
import com.apps.foodordersimulation.databinding.FragmentFoodOrderListBinding
import com.apps.foodordersimulation.ui.home.adapter.FoodOrderListAdapter
import com.apps.foodordersimulation.ui.home.viewmodel.FoodOrdersViewModel
import com.apps.foodordersimulation.ui.orderDetails.FoodOrderDetails
import com.apps.foodordersimulation.utils.OrderStatusEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FoodOrderListFragment : Fragment() {

    private lateinit var binding: FragmentFoodOrderListBinding
    private val viewModel: FoodOrdersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchOrders()
        fetchOrderStatusCount()
    }

    private fun fetchOrderStatusCount() {
        lifecycle.coroutineScope.launch {
            viewModel.fetchNewStatusCount().collect {
                binding.newStatusCount.text = it.toString()
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.fetchReadyStausCount().collect {
                binding.readyStatusCount.text = it.toString()
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.fetchPreparingStatusCount().collect {
                binding.preparingStatusCount.text = it.toString()
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.fetchDeliveredStatusCount().collect {
                binding.deliveredStatusCount.text = it.toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodOrderListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setupUiComponents()
        return binding.root
    }

    private fun setupUiComponents() {

        binding.foodOrdersList.apply {
            adapter = FoodOrderListAdapter({

                if(it.orderStatus == OrderStatusEnum.Delivered.status) {
                    lifecycle.coroutineScope.launch {
                        viewModel.orderDelivered(it)
                    }
                } else {
                    it.orderStatus = OrderStatusEnum.getNextStatus(it.orderStatus)
                    lifecycle.coroutineScope.launch {
                        viewModel.updateOrderStatus(it)
                    }
                }

            }, {
                setFragmentResult("showOrderDetails", bundleOf("order_info" to it))
            })
        }

        binding.placeOrderButton.setOnClickListener {
            lifecycle.coroutineScope.launch {
                viewModel.placeOrder()
            }
        }
    }

    private fun fetchOrders() {
        lifecycle.coroutineScope.launch {
            viewModel.fetchAllOrders().collect {
                (binding.foodOrdersList.adapter as FoodOrderListAdapter).submitList(it.toList())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodOrderListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}