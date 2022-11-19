package com.apps.foodordersimulation.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.apps.foodordersimulation.R
import com.apps.foodordersimulation.data.database.Food
import com.apps.foodordersimulation.ui.orderDetails.FoodOrderDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodOrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

        supportFragmentManager
            .setFragmentResultListener("showOrderDetails", this) { requestKey, bundle ->
                bundle.getParcelable<Food>("order_info")?.let {
                    showOrderDetails(it)
                }
            }
    }

    private fun showOrderDetails(food: Food) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = FoodOrderDetails.newInstance(food)
        fragmentTransaction.add(R.id.container, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}