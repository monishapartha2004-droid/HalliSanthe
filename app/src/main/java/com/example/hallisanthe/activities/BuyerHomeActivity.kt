package com.example.hallisanthe.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hallisanthe.R
import com.example.hallisanthe.adapter.ProductAdapter
import com.example.hallisanthe.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BuyerHomeActivity : AppCompatActivity() {

    private lateinit var recyclerProducts: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var tvEmpty: TextView
    private lateinit var btnLogout: Button

    private lateinit var productList: ArrayList<Product>
    private lateinit var filteredList: ArrayList<Product>

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_buyer_home)

        recyclerProducts = findViewById(R.id.recyclerProducts)
        etSearch = findViewById(R.id.etSearch)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        tvEmpty = findViewById(R.id.tvEmpty)
        btnLogout = findViewById(R.id.btnLogout)

        productList = ArrayList()
        filteredList = ArrayList()

        adapter = ProductAdapter(filteredList)

        recyclerProducts.layoutManager =
            GridLayoutManager(this, 2)

        recyclerProducts.adapter = adapter

        val categories = arrayOf(
            "All",
            "Toys",
            "Pottery",
            "Textiles",
            "Food",
            "Others"
        )

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )

        spinnerCategory.adapter = spinnerAdapter

        btnLogout.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            startActivity(
                Intent(
                    this,
                    RoleSelectionActivity::class.java
                )
            )

            finish()
        }

        loadProducts()

        etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                filterProducts()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        spinnerCategory.onItemSelectedListener =
            object : android.widget.AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: android.widget.AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    filterProducts()
                }

                override fun onNothingSelected(
                    parent: android.widget.AdapterView<*>?
                ) {
                }
            }
    }

    override fun onResume() {
        super.onResume()

        loadProducts()
    }

    private fun loadProducts() {

        FirebaseFirestore.getInstance()
            .collection("products")
            .get()
            .addOnSuccessListener {

                productList.clear()

                for (document in it.documents) {

                    val product =
                        document.toObject(Product::class.java)

                    if (product != null) {

                        productList.add(product)
                    }
                }

                filterProducts()
            }
    }

    private fun filterProducts() {

        val searchText =
            etSearch.text.toString().lowercase()

        val selectedCategory =
            spinnerCategory.selectedItem.toString()

        filteredList.clear()

        for (product in productList) {

            val matchesSearch =
                product.name.lowercase()
                    .contains(searchText)

            val matchesCategory =
                selectedCategory == "All" ||
                        product.category.equals(
                            selectedCategory,
                            true
                        )

            if (matchesSearch && matchesCategory) {

                filteredList.add(product)
            }
        }

        adapter.notifyDataSetChanged()

        if (filteredList.isEmpty()) {

            tvEmpty.visibility = View.VISIBLE

        } else {

            tvEmpty.visibility = View.GONE
        }
    }
}