package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.adapters.WeatherModel
import com.example.weatherapp.databinding.FragmentDaysBinding


class Days : Fragment(),WeatherAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding
    private lateinit var daysAdapter: WeatherAdapter
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaysBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        mainViewModel.liveDataList.observe(viewLifecycleOwner){
            daysAdapter.submitList(it.subList(1,it.size))
        }
    }
    private fun initRecyclerView()= with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        daysAdapter= WeatherAdapter(this@Days)
        rcView.adapter=daysAdapter

    }

    companion object {

        @JvmStatic
        fun newInstance() = Days()
    }

    override fun onClick(item: WeatherModel) {
        mainViewModel.liveDataCurrent.value=item
    }
}