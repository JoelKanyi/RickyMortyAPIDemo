package com.kanyideveloper.rickymortyapi.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.kanyideveloper.rickymortyapi.adapter.RecyclerAdapter
import com.kanyideveloper.rickymortyapi.databinding.ActivityMainBinding
import com.kanyideveloper.rickymortyapi.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter
    private val mainViewModel: MainActivityViewModel by viewModels()

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerAdapter = RecyclerAdapter()

        setUpAdapter()
        startSearchJob()
    }


    private fun startSearchJob() {

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            mainViewModel.getListData()
                .collectLatest {
                    recyclerAdapter.submitData(it)
                }
        }
    }


    private fun setUpAdapter() {

        binding.recyclerview.apply {
            adapter = recyclerAdapter
        }

        recyclerAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {

                if (recyclerAdapter.snapshot().isEmpty()) {
                    binding.progressIndicator.isVisible = true
                }
                binding.errorTextView.isVisible = false

            } else {
                binding.progressIndicator.isVisible = false

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (recyclerAdapter.snapshot().isEmpty()) {
                        binding.errorTextView.isVisible = true
                        binding.errorTextView.text = it.error.localizedMessage
                    }
                }

            }
        }
    }
}



















