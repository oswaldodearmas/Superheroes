package com.odearmas.superheroes.adapters

import androidx.recyclerview.widget.RecyclerView
import com.odearmas.superheroes.data.*
import com.odearmas.superheroes.databinding.ItemHeroBinding
import com.squareup.picasso.Picasso

class HeroViewHolder (private val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {
    fun render(hero: HeroResponse) {
        binding.nameTextView.text = hero.name
        Picasso.get().load(hero.image.imageURL).into(binding.avatarImageView)
    }
}