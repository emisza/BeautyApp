package com.example.beautyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SkincareActivity : AppCompatActivity() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skincare)


        val skincareProductList = listOf(
            Product(R.drawable.product_image4, "TATCHA The Dewy Skin Cream", "$20.99", "The Dewy Skin Cream Plumping & Hydrating Refillable Moisturizer"),
            Product(R.drawable.product_image5, "La Roche-Posay Anthelios Tinted Sunscreen SPF 40+", "$38.99", "SPF 50 sunscreen for sun protection."),
            Product(R.drawable.product_image6, "TATCHA The Rice Washpro", "$40.00", "Soft Cream Facial Cleanser Washes Away Buildup Without Stripping Skin For A Luminous Complexion 4 oz."),
            Product(R.drawable.product_image24, "La Roche-Posay: Toleriane Hydrating Gentle Face Cleanser for Dry Skin", "$19.99", "La Roche-Posay Toleriane Hydrating Gentle Cleanser is a face cleanser for dry skin that removes impurities and makeup while reinforcing the skin barrier."),
            Product(R.drawable.product_image25, "TULA: The Cult Classic Purifying Face Cleanser", "$34.00", "TULA’s The Cult Classic Purifying Face Cleanser is the #1 facial cleanser in the US.Removes dirt, impurities & makeup without stripping or overdrying."),
            Product(R.drawable.product_image26, "La Roche-Posay: Toleriane Double Repair Face Moisturizer with Niacinamide", "$24.99", "La Roche-Posay Toleriane Double Repair Face Moisturizer for sensitive skin with niacinamide provides 48-hour hydration and prebiotic benefits for the skin barrier."),
            Product(R.drawable.product_image27, "First Aid Beauty: Ultra Repair Cream", "$48.00", "First Aid Beauty's Ultra Repair cream instantly relieves dry, distressed skin + eczema, plus strengthens skin barrier in 7 days for calm, comfortable skin."),
            Product(R.drawable.product_image28, "Good Molecules: Discoloration Correcting Serum", "$12.00", "Target hyperpigmentation and uneven skin tone with Discoloration Correcting Serum from Good Molecules. Formulated with an advanced form of tranexamic acid to visibly improve the appearance of dark spots, acne scars, melasma, and sun damage."),
            Product(R.drawable.product_image29, "TULA: Ultra Hydration Triple-Hydra Complex Day & Night Serum", "$48.00", "TULA's innovative, multitasking 24-7 Ultra Hydration Triple-Hydra Complex Day & Night Serum provides intense hydration, making skin appear plumper. Triple-Hydra Complex drenches skin with 3 molecular weights of hyaluronic acid, plus polyglutamic acid to deliver moisture to skin on multiple levels."),
            Product(R.drawable.product_image30, "Oh K!: Bubble Sheet Mask", "$3.00", "Gentle O2 bubbling action helps lift dirt and dead skin cells away to reveal smooth and radiant skin with Oh K's Bubble Sheet Mask."),
            Product(R.drawable.product_image31, "Drunk Elephant: D-Bronzi Anti-Pollution Bronzing Drops with Peptides", "$38.00", "Drunk Elephant's D-Bronzi Anti-Pollution Bronzing Drops with Peptides is an antioxidant-rich, peptide-infused serum that delivers a bronzy wash of color while boosting skin's elasticity and supporting healthy barrier function."),
            Product(R.drawable.product_image32, "e.l.f. Cosmetics: Bronzing Drops", "$12.00", "e.l.f.'s Bronzing Drops are about to glow your mind. This antioxidant-rich tinted serum allows you to customize a sun-kissed glow by mixing it with your favorite moisturizer or oil (the more drops you add, the more you glow!), while moisturizing and nourishing your skin."),
            Product(R.drawable.product_image33, "TONYMOLY: Plump-kin Retinol Firming Hydrogel Eye Patches", "$24.00", "TONYMOLY's Plump-kin Retinol Eye Patches help with fine lines and under-eye darkness."),
            Product(R.drawable.product_image34, "TULA: Glow & Get It Cooling & Brightening Eye Balm", "$38.00", "TULA's game-changing Eye Balm that started it all. Provides a dewy glow while it hydrates & cools."),
            Product(R.drawable.product_image35, "Flawless by Finishing Touch: Flawless Facial Massage Ice Roller", "$9.99", "The Flawless by Finishing Touch Ice Roller is a beauty device that combines two timeless beauty techniques: rolling massage and ice therapy."),
            Product(R.drawable.product_image36, "Winky Lux: Sugared Watermelon Lip Scrub", "$16.00", "Get yourself a slice of the Winky Lux Sugared Watermelon Lip Scrub. The natural sugar scrub sloughs away dead cells and smooths delicate skin, all with a fresh crushed watermelon flavor. Infusions of Jojoba & Grapeseed Oils and Vitamin E will leave your lips smooth, moisturized and ultra juicy."),
            Product(R.drawable.product_image37, "Pixi: Hydrating Milky Mist with Hyaluronic Acid and Black Oat", "$15.00", "PIXI Hydrating Milky Mist with Hyaluronic Acid and Black Oat is a lightweight mist-on moisturizer formulated with Hyaluronic Acid for an instant surge of hydration. Perfect first layer of moisture."),
            Product(R.drawable.product_image38, "First Aid Beauty: Ultra Repair Hydra-Firm Night Cream", "$44.00", "First Aid Beauty's Ultra Repair Hydra-Firm Night Cream intensely hydrates while combating visible signs of aging during skin's nightly repair phase."),
            Product(R.drawable.product_image39, "ELEMIS: Superfood Facial Oil", "$69.00", "ELEMIS Superfood Facial Oil is a nutrient-rich facial oil that feeds your skin with 9 antioxidant-rich Superfoods to reveal a healthy-looking glow."),
            Product(R.drawable.product_image40, "Good Molecules: Niacinamide Brightening Toner", "$14.00", "Promote bright, even skin and less visible pores with Good Molecules Niacinamide Brightening Toner that's gentle enough for daily use.")
        )

        // Initialize RecyclerView and set its layout manager and adapter
        productRecyclerView = findViewById(R.id.product_recycler_view)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(skincareProductList)
        productRecyclerView.adapter = productAdapter

       // Initialize SearchView and set up listener
        val searchView: SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                productAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productAdapter.filter.filter(newText)
                return false
            }
        })
      // Open Cart button listener to navigate to CartActivity
        val openCartButton: Button = findViewById(R.id.open_cart_button)
        openCartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}