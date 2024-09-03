package com.meli.feature.search.presentation.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.feature.search.domain.model.CategoriesModel
import com.meli.feature.search.presentation.databinding.CategoriesItemBinding

class CategoriesAdapter(
    private val categoriesList: List<CategoriesModel>,
    var onClick: (String) -> Unit,
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    lateinit var binding: CategoriesItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding = CategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding.root)
    }

    override fun getItemCount() = categoriesList.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categories: CategoriesModel) {
            binding.categories.text = categories.name
        }


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position)
                    onClick(categoriesList[position].id)
            }
        }
    }
}