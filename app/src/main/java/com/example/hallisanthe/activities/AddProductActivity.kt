package com.example.hallisanthe.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hallisanthe.R
import com.example.hallisanthe.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class AddProductActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPrice: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnAdd: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_product)

        etName = findViewById(R.id.etName)
        etPrice = findViewById(R.id.etPrice)
        etCategory = findViewById(R.id.etCategory)
        etDescription = findViewById(R.id.etDescription)
        btnAdd = findViewById(R.id.btnAddProduct)

        btnAdd.setOnClickListener {

            val name = etName.text.toString().trim()
            val price = etPrice.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (name.isEmpty() ||
                price.isEmpty() ||
                category.isEmpty() ||
                description.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val docRef =
                    db.collection("products").document()

                val product = Product(
                    docRef.id,
                    name,
                    price,
                    category,
                    description,
                    "",
                    ""
                )

                docRef.set(product)

                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Product Added Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        etName.text.clear()
                        etPrice.text.clear()
                        etCategory.text.clear()
                        etDescription.text.clear()
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
    }
}