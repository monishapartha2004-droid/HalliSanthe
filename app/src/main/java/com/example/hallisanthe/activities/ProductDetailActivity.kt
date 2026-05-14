package com.example.hallisanthe.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hallisanthe.R
import com.example.hallisanthe.model.Order
import com.google.firebase.firestore.FirebaseFirestore

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product_detail)

        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvPrice = findViewById<TextView>(R.id.tvDetailPrice)
        val tvCategory = findViewById<TextView>(R.id.tvDetailCategory)
        val tvDescription =
            findViewById<TextView>(R.id.tvDetailDescription)

        val btnContactSeller =
            findViewById<Button>(R.id.btnContactSeller)

        val btnPlaceOrder =
            findViewById<Button>(R.id.btnPlaceOrder)

        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val category = intent.getStringExtra("category")
        val description = intent.getStringExtra("description")

        tvName.text = name
        tvPrice.text = "₹$price"
        tvCategory.text = category
        tvDescription.text = description

        btnContactSeller.setOnClickListener {

            Toast.makeText(
                this,
                "Seller Contacted!",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnPlaceOrder.setOnClickListener {

            val order = Order(
                name ?: "",
                price ?: "",
                "Order Placed"
            )

            FirebaseFirestore.getInstance()
                .collection("orders")
                .add(order)

            Toast.makeText(
                this,
                "Order Placed Successfully",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(
                Intent(this, OrdersActivity::class.java)
            )
        }
    }
}