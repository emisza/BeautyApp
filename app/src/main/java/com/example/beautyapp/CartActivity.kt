package com.example.beautyapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class CartActivity : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartRecyclerView: RecyclerView = findViewById(R.id.cart_recycler_view)
        totalPriceTextView = findViewById(R.id.total_price_text)  // Initialize totalPriceTextView

        // Check if the cart is empty
        if (Cart.items.isEmpty()) {
            findViewById<TextView>(R.id.empty_cart_message).visibility = View.VISIBLE
            cartRecyclerView.visibility = View.GONE
        } else {
            cartRecyclerView.visibility = View.VISIBLE
            cartRecyclerView.layoutManager = LinearLayoutManager(this)

            cartAdapter = CartAdapter(Cart.items) {
                updateTotalPrice()
            }
            cartRecyclerView.adapter = cartAdapter

            // Calculate total price and update
            updateTotalPrice()
        }
    }

    private fun updateTotalPrice() {
        val totalPrice = Cart.items.sumOf { it.product.price * it.quantity }
        totalPriceTextView.text = getString(R.string.total_price, String.format(Locale.getDefault(), "%.2f", totalPrice))
    }
}