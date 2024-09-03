package com.meli.feature.productlist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.feature.productlist.domain.model.ProductModel
import com.meli.feature.productlist.presentation.databinding.ProductItemBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    var onClick: (String) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    lateinit var binding: ProductItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding.root)
    }


    override fun getItemCount() = productList.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: ProductModel) {
            binding.name.text = product.title
            binding.value.text = product.price
        }


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position)
                    onClick(productList[position].id)
            }
        }
    }
}