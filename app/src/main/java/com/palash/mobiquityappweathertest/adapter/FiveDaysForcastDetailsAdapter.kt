package com.palash.mobiquityappweathertest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.palash.mobiquityappweathertest.adapter.view_holder.BookMarkedViewHolder
import com.palash.mobiquityappweathertest.adapter.view_holder.FiveDaysViewHolder
import com.palash.mobiquityappweathertest.interface_listner.RecylerViewClickListner
import com.palash.mobiquityappweathertest.model.ListData
import java.util.ArrayList

class FiveDaysForcastDetailsAdapter (val arrayName:List<ListData>) : RecyclerView.Adapter<FiveDaysViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDaysViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FiveDaysViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FiveDaysViewHolder, position: Int) {
/*        val movie: Movie = list[position]
        holder.bind(movie)*/

        holder.bind(pos = position,listData = arrayName.get( position))



    }

    override fun getItemCount(): Int = arrayName.size

}

