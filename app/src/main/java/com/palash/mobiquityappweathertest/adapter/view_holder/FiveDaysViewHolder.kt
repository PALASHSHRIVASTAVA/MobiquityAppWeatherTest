package com.palash.mobiquityappweathertest.adapter.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.palash.mobiquityappweathertest.R
import com.palash.mobiquityappweathertest.model.ListData
import com.palash.mobiquityappweathertest.model.Main
import com.palash.mobiquityappweathertest.model.Weather
import kotlinx.android.synthetic.main.forecast_info_layout.view.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.place_layout.*
import java.time.LocalDate

class FiveDaysViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fiveday_layout, parent, false)) {
    private var textViewDate: TextView? = null
    private var textViewTemprature: TextView? = null
    private var textViewHumidity: TextView? = null
    private var textViewRainChances: TextView? = null
    private var textViewWindInfo: TextView? = null


    init {
        textViewDate = itemView.findViewById(R.id.textViewDate)
        textViewTemprature = itemView.findViewById(R.id.textViewTemprature)
        textViewHumidity = itemView.findViewById(R.id.textViewHumidity)
        textViewRainChances = itemView.findViewById(R.id.textViewRainChances)
        textViewWindInfo = itemView.findViewById(R.id.textViewWindInfo)



    }



    fun bind(pos:Int,listData:  ListData) {
        val degreesymbol = '\u00B0'

        var  main: Main = listData.main!!

        textViewTemprature?.text = main?.temp.toString() + " " + degreesymbol

        //textViewDate?.text = listData.dt_txt?.split(" ")?.get(0)
        textViewDate?.text = listData.dt_txt!!

        if(!listData?.weather.isNullOrEmpty()) {
            var weather: Weather =listData.weather?.get(0)!!
/*            forcast.textView.text = "Weather"
            forcast.textView2.text = weather.main*/

            textViewRainChances?.text = "Rain : "+weather.description

        }
        textViewHumidity?.text  ="Humidity : "
        textViewHumidity?.append(main?.humidity.toString())
        listData.wind?.let  {
            textViewWindInfo?.text = "Wind Speed : "
            textViewWindInfo?.append(it.speed.toString())
        }




    }

}