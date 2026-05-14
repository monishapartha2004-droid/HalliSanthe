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

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val etEmail =
            findViewById<EditText>(R.id.etRegisterEmail)

        val etPassword =
            findViewById<EditText>(R.id.etRegisterPassword)

        val btnRegister =
            findViewById<Button>(R.id.btnRegister)

        val tvLogin =
            findViewById<TextView>(R.id.tvLogin)

        btnRegister.setOnClickListener {

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

                auth.createUserWithEmailAndPassword(
                    email,
                    password
                )

                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Registration Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this,
                                LoginActivity::class.java
                            )
                        )

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

        tvLogin.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }
    }
}