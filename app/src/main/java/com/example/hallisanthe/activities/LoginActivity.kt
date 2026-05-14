package com.example.hallisanthe.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hallisanthe.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val etEmail =
            findViewById<EditText>(R.id.etEmail)

        val etPassword =
            findViewById<EditText>(R.id.etPassword)

        val btnLogin =
            findViewById<Button>(R.id.btnLogin)

        val tvRegister =
            findViewById<TextView>(R.id.tvRegister)

        val role = intent.getStringExtra("ROLE")

        btnLogin.setOnClickListener {

            val email =
                etEmail.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Enter all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                auth.signInWithEmailAndPassword(
                    email,
                    password
                )

                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (role == "ARTISAN") {

                            startActivity(
                                Intent(
                                    this,
                                    ArtisanDashboardActivity::class.java
                                )
                            )

                        } else {

                            startActivity(
                                Intent(
                                    this,
                                    BuyerHomeActivity::class.java
                                )
                            )
                        }

                        finish()
                    }

                    .addOnFailureListener {

                        Toast.makeText(
                            this,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }

        tvRegister.setOnClickListener {

            val intent =
                Intent(this, RegisterActivity::class.java)

            intent.putExtra("ROLE", role)

            startActivity(intent)
        }
    }
}