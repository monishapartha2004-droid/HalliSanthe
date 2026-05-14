package com.example.hallisanthe.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hallisanthe.R

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_role_selection)

        val btnBuyer = findViewById<Button>(R.id.btnBuyer)
        val btnArtisan = findViewById<Button>(R.id.btnArtisan)

        btnBuyer.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "BUYER")
            startActivity(intent)
        }

        btnArtisan.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "ARTISAN")
            startActivity(intent)
        }
    }
}