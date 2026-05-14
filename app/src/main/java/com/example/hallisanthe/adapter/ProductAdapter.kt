package com.example.hallisanthe.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hallisanthe.R
import com.example.hallisanthe.activities.ProductDetailActivity
import com.example.hallisanthe.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class ProductAdapter(
    private val productList: ArrayList<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvName: TextView =
            itemView.findViewById(R.id.tvName)

        val tvPrice: TextView =
            itemView.findViewById(R.id.tvPrice)

        val tvCategory: TextView =
            itemView.findViewById(R.id.tvCategory)

        val btnWishlist: Button =
            itemView.findViewById(R.id.btnWishlist)

        val btnDelete: Button =
            itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        val product = productList[position]

        holder.tvName.text = product.name
        holder.tvPrice.text = "₹${product.price}"
        holder.tvCategory.text = product.category

        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                ProductDetailActivity::class.java
            )

            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price)
            intent.putExtra("category", product.category)
            intent.putExtra("description", product.description)

            holder.itemView.context.startActivity(intent)
        }

        holder.btnWishlist.setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "${product.name} added to Wishlist",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.btnDelete.setOnClickListener {

            FirebaseFirestore.getInstance()
                .collection("products")
                .document(product.id)
                .delete()

            Toast.makeText(
                holder.itemView.context,
                "Product Deleted",
                Toast.LENGTH_SHORT
            ).show()

            productList.removeAt(position)

            notifyItemRemoved(position)
        }
    }
}