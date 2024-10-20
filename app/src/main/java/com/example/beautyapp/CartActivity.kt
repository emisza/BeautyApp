package com.example.beautyapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartRecyclerView: RecyclerView = findViewById(R.id.cart_recycler_view)
        val emptyCartMessage: TextView = findViewById(R.id.empty_cart_message)

        // Check if the cart is empty
        if (Cart.items.isEmpty()) {
            // Show the empty cart message and hide the RecyclerView
            emptyCartMessage.visibility = View.VISIBLE
            cartRecyclerView.visibility = View.GONE
        } else {
            // Hide the empty cart message and show the RecyclerView
            emptyCartMessage.visibility = View.GONE
            cartRecyclerView.visibility = View.VISIBLE

            // Initialize RecyclerView if there are items in the cart
            cartRecyclerView.layoutManager = LinearLayoutManager(this)
            cartAdapter = CartAdapter(Cart.items)
            cartRecyclerView.adapter = cartAdapter
        }
    }
}