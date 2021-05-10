package com.kanyideveloper.rickymortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kanyideveloper.rickymortyapi.adapter.RecyclerAdapter
import com.kanyideveloper.rickymortyapi.databinding.ActivityMainBinding
import com.kanyideveloper.rickymortyapi.network.ResultData
import com.kanyideveloper.rickymortyapi.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter
    private val mainViewModel : MainActivityViewModel by viewModels()
    private val TAG = "MainActivity"



    private val repositoryObserver = Observer<ResultData<Any>> { resultData ->
        when (resultData) {
            is ResultData.Loading -> {
                //binding.progressIndicator.show()

                Log.d(TAG, "Loading ")
            }
            is ResultData.Success -> {
                //binding.progressIndicator.hide()
                resultData.data
                //adapter.submitList(resultData.data)

                Log.d(TAG, "Success: ${resultData.data?.toString()} ")
            }
            is ResultData.Failed -> {
                //binding.progressIndicator.hide()

                Log.d(TAG, "Failed: ")
            }
            is ResultData.Exception -> {
                //binding.progressIndicator.hide()

                Log.d(TAG, "Exception Caught: ${resultData.exception}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerAdapter = RecyclerAdapter()

        getDataAndSubscribeEvents()
    }

    private fun initRecyclerView(){
        binding.recyclerview.apply {
            adapter = recyclerAdapter
        }
    }

    /*private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recyclerAdapter.submitData(it)
            }
        }
    }*/

    private fun getDataAndSubscribeEvents() {
        val repositoriesListLiveData = mainViewModel.getListData()
        repositoriesListLiveData.observe(this, repositoryObserver)
    }
}



















