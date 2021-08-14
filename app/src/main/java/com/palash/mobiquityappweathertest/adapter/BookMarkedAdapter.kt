package com.palash.mobiquityappweathertest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.palash.mobiquityappweathertest.adapter.view_holder.BookMarkedViewHolder
import com.palash.mobiquityappweathertest.interface_listner.RecylerViewClickListner
import java.util.*
import kotlin.collections.ArrayList

class BookMarkedAdapter(
    var arrayName: ArrayList<String>,
    val isCityAvailabe: MutableLiveData<Boolean>,
    val recylerViewClickListner: RecylerViewClickListner
) : RecyclerView.Adapter<BookMarkedViewHolder>() {


    var isFilterEnable : Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookMarkedViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BookMarkedViewHolder, position: Int) {
/*        val movie: Movie = list[position]
        holder.bind(movie)*/

        holder.bind(pos = position, name = arrayName.get(position))
        if (!isFilterEnable) {
            holder.imageViewBookMarked?.visibility = View.VISIBLE
        } else {
            holder.imageViewBookMarked?.visibility = View.INVISIBLE
        }

        holder.imageViewBookMarked?.setOnClickListener {

            // notifyItemRemoved(position)
            val temp= arrayName.get(position);
/*            arrayName.remove(temp)
            if (!arrayName.isEmpty()) {
                notifyItemRemoved(position)
            } else {
                notifyDataSetChanged()
            }
            isCityAvailabe.postValue(true)*/
            recylerViewClickListner.removeItem(temp)

        }

        holder.placeContainer?.setOnClickListener {
            recylerViewClickListner.onItemClick(pos = position)
        }


    }

    fun isFilterEnableDisable(isFilter:Boolean)
    {
        isFilterEnable = isFilter
    }
    fun filterList(filterdNames: ArrayList<String>) {
        this.arrayName = filterdNames
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = arrayName.size

}

