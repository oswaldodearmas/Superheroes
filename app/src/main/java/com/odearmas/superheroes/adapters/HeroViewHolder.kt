package com.odearmas.superheroes.adapters

import androidx.recyclerview.widget.RecyclerView
import com.odearmas.superheroes.data.HeroResponse
import com.odearmas.superheroes.databinding.ItemHeroBinding

class HeroViewHolder (private val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {
    fun render(hero: HeroResponse) {
        binding.nameTextView.text = hero.name
    }
}