package com.example.beautyapp

import CartItem

object Cart {
    val items = mutableListOf<CartItem>()

    // Function to add a product to the cart, or increase its quantity if it already exists
    fun addProduct(product: Product) {
        val existingItem = items.find { it.product.name == product.name }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            items.add(CartItem(product))
        }
    }

    // Function to remove a product from the cart
    fun removeProduct(product: Product) {
        val existingItem = items.find { it.product.name == product.name }
        if (existingItem != null) {
            items.remove(existingItem)
        }
    }


    // Function to clear the entire cart
    fun clearCart() {
        items.clear()
    }

    // Function to calculate the total price of items in the cart
    fun calculateTotalPrice(): Double {
        return items.sumOf {
            it.product.price
        }
    }

}