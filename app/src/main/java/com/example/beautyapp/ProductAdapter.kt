package com.example.beautyapp

import CartItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import android.widget.Filterable
import java.util.Locale

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), Filterable {

    private var filteredList: List<Product> = productList
    // Inner class to hold references to each view in the product item layout
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.product_image)
        val productName: TextView = view.findViewById(R.id.product_name)
        val productPrice: TextView = view.findViewById(R.id.product_price)
        val productDescription: TextView = view.findViewById(R.id.product_description)
        val addToCartButton: Button = view.findViewById(R.id.add_to_cart_button)
    }

    // Inflate the product_item layout for each product in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    // Bind each product data to the views
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredList[position]
        holder.productImage.setImageResource(product.imageResId)
        holder.productName.text = product.name
        holder.productPrice.text = String.format(Locale.getDefault(), "$%.2f", product.price)

        holder.productDescription.text = product.description

        // Handle Add to Cart button click
        holder.addToCartButton.setOnClickListener {
            Cart.items.add(CartItem(product, 1))  // Add product to Cart as a CartItem with quantity 1
            Toast.makeText(holder.itemView.context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
        }
    }


    // Return the total number of items in the data set
    override fun getItemCount(): Int {
        return filteredList.size
    }
    // Implementation of the Filterable interface to enable search functionality
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase()

                filteredList = if (query.isEmpty()) {
                    productList
                } else {
                    productList.filter {
                        it.name.lowercase().contains(query) || it.description.lowercase().contains(query)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = if (results?.values is List<*>) {
                    (results.values as List<*>).filterIsInstance<Product>()
                } else {
                    emptyList()
                }
                notifyDataSetChanged()
            }

        }
    }
}