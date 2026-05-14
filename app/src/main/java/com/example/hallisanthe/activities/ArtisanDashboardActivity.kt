package com.example.hallisanthe.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hallisanthe.R

class ArtisanDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_artisan_dashboard)

        val btnAddProduct = findViewById<Button>(R.id.btnAddProduct)

        btnAddProduct.setOnClickListener {

            startActivity(
                Intent(this, AddProductActivity::class.java)
            )
        }
    }
}