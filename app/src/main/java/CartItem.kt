import com.example.beautyapp.Product

data class CartItem(
    val product: Product,
    var quantity: Int = 1 // Default quantity is 1
)
