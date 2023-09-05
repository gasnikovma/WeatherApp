package com.example.weatherapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.adapters.WeatherModel
import com.example.weatherapp.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject


class Hours : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var weatherAdapter: WeatherAdapter
    private val model:MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHoursBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        model.liveDataCurrent.observe(viewLifecycleOwner){
            weatherAdapter.submitList(getHoursList(it))
        }

    }
    private fun initRecyclerView()= with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        weatherAdapter= WeatherAdapter(null)
        rcView.adapter=weatherAdapter

    }
    private fun getHoursList(Witem:WeatherModel):List<WeatherModel>{
        val hoursArray=JSONArray(Witem.hours)
        val list =ArrayList<WeatherModel>()
        for(i in 0 until hoursArray.length()){
            val item = WeatherModel(
                Witem.city,
                (hoursArray[i] as JSONObject).getString("time"),
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("text"),
                (hoursArray[i] as JSONObject).getString("temp_c"),
                "",
                "",
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("icon"),
                ""

            )
            list.add(item)
        }
        return list

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            Hours()
    }
}