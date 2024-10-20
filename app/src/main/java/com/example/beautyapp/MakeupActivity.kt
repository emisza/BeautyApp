package com.example.beautyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class MakeupActivity : AppCompatActivity() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makeup)


        val productList = listOf(
            Product(R.drawable.product_image1, "MAC Silky Matte Lipstick", "$15.99", "A vibrant reddish burgundy lipstick. Get more longwear with 12 hours of lasting colour, and 8 hours of moisture that leaves lips looking visibly fuller."),
            Product(R.drawable.product_image2, "NARS Light Reflecting Foundation", "$52.00", "Breathable, medium buildable foundation instantly conceals redness and dark spots, and blurs wrinkles, pores, and texture."),
            Product(R.drawable.product_image3, "Mascara Lash Sensational Maybelline Sky High", "$9.98", "Full volume and limitless length mascara is formula infused with Bamboo extract and fibers."),
            Product(R.drawable.product_image7, "Rare Beauty: Mini Soft Pinch Liquid Blush", "$14.00", "Weightless, long-lasting liquid blush with up to 12-hour wear. Blends and builds beautifully for a soft, healthy flush in matte or dewy finishes. Color: Hope - nude mauve."),
            Product(R.drawable.product_image8, "NARS: Radiant Creamy Concealer with Medium Coverage", "$32.00", "A multipurpose concealer that brightens, corrects, and perfects for up to 16 hours with a creamy medium-to-buildable coverage and natural, radiant finish. Color: Madeleine - L2.3 - light with neutral undertones."),
            Product(R.drawable.product_image9, "e.l.f. Cosmetics Power Grip Primer", "$10.00", "e.l.f. Cosmetics Power Grip Primer is a gel-based, ingredient-driven face primer that smoothes skin while gripping your makeup to help it last longer."),
            Product(R.drawable.product_image10, "HUDA BEAUTY: Easy Bake Loose Baking & Setting Powder", "$38.00", "A lightly pigmented, silky setting powder that bakes & sets, blurs the look of fine lines, and locks in makeup for 10 hours with an airbrushed finish. Color: Peach Pie - medium to tan with warm undertones."),
            Product(R.drawable.product_image11, "Urban Decay: All Nighter Waterproof Makeup Setting Spray", "$36.00", "An award-winning lightweight vegan setting and finishing spray that keeps makeup fresh, smudge-proof, and transfer resistant for up to 16 hours. Skin Type: Normal, Dry, Combination, and Oily."),
            Product(R.drawable.product_image12, "Valentino: Colorgraph Waterproof Gel Eyeliner Pencil", "$38.00", "A versatile dual-ended, creamy gel eyeliner that delivers rich color and waterproof wear without fading or creasing for up to 24 hours. Color: Rockstud Noir - intense black."),
            Product(R.drawable.product_image13, "Benefit Cosmetics: Cookie Shimmer Finish Powder Highlighter", "$35.00", "A silky-soft, superfine powder highlighter. Color: Cookie - golden pearl."),
            Product(R.drawable.product_image14, "DIOR: Lip Glow Oil", "$40.00", " A nurturing, glossy lip oil that protects and enhances the lips, bringing out their natural color. Color: Raspberry - raspberry."),
            Product(R.drawable.product_image15, "Anastasia Beverly Hills: Mini Spice Eye Palette", "$29.00", "An essential mini eyeshadow collection featuring nine full-pigment shades—from warm-toned neutrals to brilliant golds—in matte and metallic finishes."),
            Product(R.drawable.product_image16, "Benefit Cosmetics: 24-HR Brow Setter Clear Brow Gel with Lamination Effect", "$26.00", "A clear gel that easily shapes and tames brows, locking in your look for up to 24 hours."),
            Product(R.drawable.product_image17, "Fenty Beauty by Rihanna: Pro Filt’r Soft Matte Longwear Liquid Foundation", "$40.00", "A soft-matte, long-wearing foundation with climate-adaptive technology to fight heat, sweat, and shine—available in a wide range of shades. Color: 450 - for deep skin with neutral undertones."),
            Product(R.drawable.product_image18, "Fenty Beauty by Rihanna: Match Stix Matte Contour Skinstick", "$32.00", "A matte contour stick in a range of shades for all skin tones. The buildable cream-to-powder long-wear formula is lightweight and blendable. Color: Soft Amber - contour, cool neutral undertone for light skin tones."),
            Product(R.drawable.product_image19, "Hourglass: Mini Vanish™ Airbrush Concealer", "$18.00", "A bestselling concealer, in a travel size, in a weightless, waterproof, and full-coverage formula. Color: Beech 6 - Medium, Yellow Undertones."),
            Product(R.drawable.product_image20, "Too Faced: Better Than Sex Volumizing & Lengthening Mascara", "$29.00", "A conditioning mascara with a formula that thickens, lengthens, and locks in curl for dramatic lashes with extreme volume."),
            Product(R.drawable.product_image21, "Urban Decay: 24/7 Inks Easy Ergonomic Liquid Eyeliner Pen", "$28.00", "A vegan liquid eyeliner that has an ergonomic grip, is water- and smudge-resistant, wears all day, and comes in a range of vibrant colors. Color: Zero - matte black, brush tip."),
            Product(R.drawable.product_image22, "Anastasia Beverly Hills: DIPBROW Waterproof, Smudge Proof Brow Pomade", "$23.00", "A smudge-free, waterproof pomade formula that performs as an all-in-one brow product. Color: Dark Brown."),
            Product(R.drawable.product_image23, "Benefit Cosmetics: Precisely, My Brow Detailer Microfine Waterproof Eyebrow Pencil", "$25.00", "A microfine detailing eyebrow pencil with a 0.8-millimeter tip that adds precise detail, dimension, and depth to brows. Color: Shade 03.")

        )

        // Initialize RecyclerView and set its layout manager and adapter
        productRecyclerView = findViewById(R.id.product_recycler_view)
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList)
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

        // Add Open Cart button listener to navigate to CartActivity
        val openCartButton: Button = findViewById(R.id.open_cart_button)
        openCartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}