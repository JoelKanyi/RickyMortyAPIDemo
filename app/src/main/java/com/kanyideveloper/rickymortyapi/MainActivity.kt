package com.kanyideveloper.rickymortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import com.kanyideveloper.rickymortyapi.adapter.RecyclerAdapter
import com.kanyideveloper.rickymortyapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initRecyclerView(){
        binding.recyclerview.apply {
            adapter = adapter
        }
    }
}