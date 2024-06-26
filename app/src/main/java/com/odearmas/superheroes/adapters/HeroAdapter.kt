package com.odearmas.superheroes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odearmas.superheroes.data.HeroResponse
import com.odearmas.superheroes.databinding.ItemHeroBinding

class HeroAdapter(
    private var dataSet: List<HeroResponse> = emptyList(),
    private val onItemClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<HeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context))
        return HeroViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.render(dataSet[position])
        holder.itemView.setOnClickListener { onItemClickListener(holder.adapterPosition) }
    }

    fun updateData(dataSet: List<HeroResponse>?) {
        if (dataSet != null) {
            this.dataSet = dataSet
            notifyDataSetChanged()
        }
    }
}
