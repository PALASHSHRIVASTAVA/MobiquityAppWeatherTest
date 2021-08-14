package com.palash.mobiquityappweathertest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.palash.mobiquityappweathertest.R
import com.palash.mobiquityappweathertest.adapter.BookMarkedAdapter
import com.palash.mobiquityappweathertest.adapter.FiveDaysForcastDetailsAdapter
import com.palash.mobiquityappweathertest.databinding.FragmentSecondBinding
import com.palash.mobiquityappweathertest.model.Main
import com.palash.mobiquityappweathertest.model.Weather
import com.palash.mobiquityappweathertest.network.APP_ID
import com.palash.mobiquityappweathertest.viewmodel.CityTempratureDetailsViewModel
import com.palash.mobiquityappweathertest.viewmodel.MapViewViewModel
import kotlinx.android.synthetic.main.forecast_info_layout.view.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.cityList
import kotlinx.android.synthetic.main.place_layout.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CityTempratureDetailsFragment : BaseFragment() {
    private val viewModel: CityTempratureDetailsViewModel by viewModel()
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

         arguments?.getString("city")


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsContainer.visibility = View.INVISIBLE
        binding.buttonSecond.setOnClickListener {
           // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun initValue(view: View) {

        imageViewBookMarked.visibility = View.INVISIBLE
        val map = HashMap<String,Any>()
        // map.put("units","metric")
        if(viewModel.getUnitValue()!=null)
        {
            map.put("units", viewModel.getUnitValue()!!)
        }
        else{
            map.put("units","metric")
        }
        map.put("q",arguments?.getString("city")!!)
        map.put("appid", APP_ID)
        textViewCity.text = arguments?.getString("city")!!


        viewModel.getCityTempratureDetails(map,this)



       // viewModel.getCityTemprature(map,this)


        viewModel.fiveDayMutableLiveData.observe(this,  {
        if(it?.cod!=null && it.cod.equals("200",true)) {

            if(it?.list?.isNotEmpty()!!) {

                cityList.adapter =
                    FiveDaysForcastDetailsAdapter(
                        it?.list)
                val degreesymbol = '\u00B0'
                detailsContainer.visibility = View.VISIBLE
              var  main: Main = it?.list?.get(0)?.main!!

                textViewTemp.text = main?.temp.toString() + " " + degreesymbol
                temperature.textView.text = "Temprature"
                temperature.textView2.text = main?.temp.toString() + " " + degreesymbol

                    if (viewModel.getUnitValue() != null && viewModel.getUnitValue()
                        .contentEquals("imperial", true)
                ) {
                    textViewTemp.append("F")
                        temperature.textView2.append("F")

                } else {
                    textViewTemp.append("C")
                        temperature.textView2.append("C")
                }
            if(!it?.list?.get(0)?.weather.isNullOrEmpty()) {
                var weather: Weather = it?.list?.get(0)?.weather?.get(0)!!
                forcast.textView.text = "Weather"
                forcast.textView2.text = weather.main

                rain.textView.text = "Rain"
                rain.textView2.text = weather.description

            }
                humidity.textView.text = "Humidity"
                humidity.textView2.text = main?.humidity.toString()
                it?.list?.get(0)?.wind?.let  {
                    wind.textView.text = "Wind Speed"
                    wind.textView2.text =it.speed.toString()
                }

            }

        }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance( cityName:String) = CityTempratureDetailsFragment().apply {
            arguments = Bundle().apply {

                putString("city", cityName)

            }
        }
    }


    /*
    *   viewModel.currentDayMutableLiveData.observe(this,  {


            if(it?.name!=null)
            {
                val degreesymbol = '\u00B0'
                detailsContainer.visibility = View.VISIBLE
                var  main: Main = it?.main!!

                textViewTemp.text = main?.temp.toString() + " " + degreesymbol
                temperature.textView.text = "Temprature"
                temperature.textView2.text = main?.temp.toString() + " " + degreesymbol

                if (viewModel.getUnitValue() != null && viewModel.getUnitValue()
                        .contentEquals("imperial", true)
                ) {
                    textViewTemp.append("F")
                    temperature.textView2.append("F")

                } else {
                    textViewTemp.append("C")
                    temperature.textView2.append("C")
                }
                if(!it?.weather.isNullOrEmpty()) {
                    var weather: Weather = it?.weather?.get(0)!!
                    forcast.textView.text = "Weather"
                    forcast.textView2.text = weather.main

                    rain.textView.text = "Rain"
                    rain.textView2.text = weather.description

                }
                humidity.textView.text = "Humidity"
                humidity.textView2.text = main?.humidity.toString()
                it?.wind?.let  {
                    wind.textView.text = "Wind Speed"
                    wind.textView2.text =it.speed.toString()
                }

            }
            val map = HashMap<String,Any>()
            // map.put("units","metric")
            if(viewModel.getUnitValue()!=null)
            {
                map.put("units", viewModel.getUnitValue()!!)
            }
            else{
                map.put("units","metric")
            }
            map.put("q",arguments?.getString("city")!!)
            map.put("appid", APP_ID)


            viewModel.getCityTempratureDetails(map,this)



    })
    *
    * */
}