package com.palash.mobiquityappweathertest.adapter.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.palash.mobiquityappweathertest.R

class BookMarkedViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.place_layout, parent, false)) {
    private var textViewCity: TextView? = null
    private var textViewTemp: TextView? = null
    var imageViewBookMarked:ImageView? = null
    var placeContainer:ConstraintLayout? = null

    init {
        textViewCity = itemView.findViewById(R.id.textViewCity)
        textViewTemp = itemView.findViewById(R.id.textViewTemp)
        imageViewBookMarked = itemView.findViewById(R.id.imageViewBookMarked)
        placeContainer = itemView.findViewById(R.id.placeContainer)
        imageViewBookMarked?.setImageResource(R.drawable.ic_baseline_bookmark_24)


    }



    fun bind(pos:Int,name: String) {
        textViewCity?.text = name

        textViewTemp?.visibility = View.GONE

    }

}