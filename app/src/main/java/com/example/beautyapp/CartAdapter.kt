package com.example.beautyapp

import CartItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.product_image)
        val productName: TextView = view.findViewById(R.id.product_name)
        val productPrice: TextView = view.findViewById(R.id.product_price)
        val quantityText: TextView = view.findViewById(R.id.quantity_text)
        val increaseButton: Button = view.findViewById(R.id.increase_quantity_button)
        val decreaseButton: Button = view.findViewById(R.id.decrease_quantity_button)
        val removeButton: Button = view.findViewById(R.id.remove_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]

        // Set the product image
        holder.productImage.setImageResource(cartItem.product.imageResId)


        //string resource to format the product name and quantity
        holder.productName.text = holder.itemView.context.getString(
            R.string.product_name_with_quantity, cartItem.product.name, cartItem.quantity
        )


        val priceDouble = cartItem.product.price

        // Format the product price using Locale.getDefault()
        holder.productPrice.text = holder.itemView.context.getString(
            R.string.product_price, String.format(Locale.getDefault(), "%.2f", priceDouble)
        )

        // Display the quantity
        holder.quantityText.text = cartItem.quantity.toString()

        // Handle quantity increase
        holder.increaseButton.setOnClickListener {
            cartItem.quantity++
            holder.quantityText.text = cartItem.quantity.toString()

            // Update product price based on quantity using Locale.getDefault()
            holder.productPrice.text = holder.itemView.context.getString(
                R.string.product_price, String.format(Locale.getDefault(), "%.2f", priceDouble * cartItem.quantity)
            )

            onQuantityChanged()  // Notify quantity change
        }

        // Handle quantity decrease
        holder.decreaseButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                holder.quantityText.text = cartItem.quantity.toString()

                // Update product price based on quantity using Locale.getDefault()
                holder.productPrice.text = holder.itemView.context.getString(
                    R.string.product_price, String.format(Locale.getDefault(), "%.2f", priceDouble * cartItem.quantity)
                )

                onQuantityChanged()  // Notify quantity change
            }
        }

        // Handle item removal
        holder.removeButton.setOnClickListener {
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            onQuantityChanged()  // Notify quantity change
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}