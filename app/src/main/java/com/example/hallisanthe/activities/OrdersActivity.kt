package com.example.hallisanthe.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hallisanthe.R
import com.google.firebase.firestore.FirebaseFirestore

class OrdersActivity : AppCompatActivity() {

    private lateinit var tvOrders: TextView
    private lateinit var btnTrackOrder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_orders)

        tvOrders = findViewById(R.id.tvOrders)

        btnTrackOrder =
            findViewById(R.id.btnTrackOrder)

        loadOrders()

        btnTrackOrder.setOnClickListener {

            val intent =
                Intent(this, TrackOrderActivity::class.java)

            startActivity(intent)
        }
    }

    private fun loadOrders() {

        FirebaseFirestore.getInstance()
            .collection("orders")
            .get()
            .addOnSuccessListener {

                val builder = StringBuilder()

                for (document in it.documents) {

                    val name =
                        document.getString("productName")

                    val price =
                        document.getString("productPrice")

                    val status =
                        document.getString("status")

                    builder.append(
                        "Product: $name\n" +
                                "Price: ₹$price\n" +
                                "Status: $status\n\n"
                    )
                }

                tvOrders.text = builder.toString()
            }
    }
}