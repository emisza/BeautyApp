package com.example.beautyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: MutableList<Product>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.product_name)
        val productPrice: TextView = view.findViewById(R.id.product_price)
        val removeButton: Button = view.findViewById(R.id.remove_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.productName.text = product.name
        holder.productPrice.text = product.price

        holder.removeButton.setOnClickListener {
            cartItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}