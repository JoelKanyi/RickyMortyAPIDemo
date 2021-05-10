package com.kanyideveloper.rickymortyapi.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kanyideveloper.rickymortyapi.adapter.RecyclerAdapter
import com.kanyideveloper.rickymortyapi.databinding.ActivityMainBinding
import com.kanyideveloper.rickymortyapi.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter
    private val mainViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerAdapter = RecyclerAdapter()

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.recyclerview.apply {
            adapter = recyclerAdapter
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            mainViewModel.getListData().collectLatest {
                recyclerAdapter.submitData(it)
            }
        }
    }
}



















