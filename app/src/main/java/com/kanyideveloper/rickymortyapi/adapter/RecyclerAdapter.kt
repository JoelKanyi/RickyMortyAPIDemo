package com.kanyideveloper.rickymortyapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kanyideveloper.rickymortyapi.model.CharacterData
import com.kanyideveloper.rickymortyapi.databinding.RecyclerRowBinding


class RecyclerAdapter : PagingDataAdapter<CharacterData, RecyclerAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class MyViewHolder(private val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterData: CharacterData) {

            binding.characterName.text = characterData.name
            binding.characterSpecies.text = characterData.species

            Glide.with(binding.characterImage)
                .load(characterData.image)
                .circleCrop()
                .into(binding.characterImage)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterData>(){
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }

    }
}