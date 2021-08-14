package com.palash.mobiquityappweathertest.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.palash.mobiquityappweathertest.adapter.BookMarkedAdapter
import com.palash.mobiquityappweathertest.databinding.FragmentFirstBinding
import com.palash.mobiquityappweathertest.interface_listner.ChangeScreenListner
import com.palash.mobiquityappweathertest.interface_listner.RecylerViewClickListner
import com.palash.mobiquityappweathertest.util.FragmentNameEnum
import com.palash.mobiquityappweathertest.viewmodel.BookMarkedCityViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.android.viewmodel.ext.android.viewModel


class BookMarkedCityFragment : BaseFragment(), RecylerViewClickListner {

    private val viewModel: BookMarkedCityViewModel by viewModel()
    private var _binding: FragmentFirstBinding? = null
    private var changeScreenListner: ChangeScreenListner ? =null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var bookMarkedAdapter:BookMarkedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



    }

    override fun initValue(view: View) {
   //     TODO("Not yet implemented")

        bookMarkedAdapter =  BookMarkedAdapter(
            viewModel.cityList,
            viewModel.isCityAvailabe,
            this
        )
        cityList.adapter =bookMarkedAdapter



        viewModel.cityListMutableLiveData.observe(this,{
        if(it!=null) {
    viewModel.cityList.clear()
    viewModel.cityList.addAll(it!!)
    (cityList.adapter as RecyclerView.Adapter).notifyDataSetChanged()
    }
        })

        viewModel.isCityAvailabe.observe(this,{
            if(it!=null && it)
            {
                val gsonVal = Gson().toJson(viewModel.cityList)
                viewModel.saveCity(gsonVal)

                if(viewModel.cityList.isEmpty())
                {
                    changeScreenListner?.changeScreenFun(FragmentNameEnum.MAP_FRAGMENT, 1)
                }
            }
        })


        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                if(editable.toString().length>0)
                {
                    filter(editable.toString())
                }
                else{
                    bookMarkedAdapter.isFilterEnableDisable(false)
                    bookMarkedAdapter.filterList(viewModel.cityList)
                    bookMarkedAdapter.notifyDataSetChanged()
                }

            }
        })
    }

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filterdNames: ArrayList<String> = ArrayList()

        //looping through existing elements
        for (s in viewModel.cityList) {
            //if the existing elements contains the search input
            if (s.lowercase().contains(text.lowercase())) {
                //adding the element to filtered list
                filterdNames.add(s)
            }
        }

        //calling a method of the adapter class and passing the filtered list
        bookMarkedAdapter.filterList(filterdNames)
        bookMarkedAdapter.isFilterEnableDisable(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ChangeScreenListner)
        {
            changeScreenListner = context
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getCityValue()
        changeScreenListner?.showNaveButton(arguments?.getBoolean("isShow")!!)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(isShow:Boolean) = BookMarkedCityFragment().apply {
            arguments = Bundle().apply {
                putBoolean("isShow",isShow)
            }
        }
    }

    override fun onDestroy() {
        Log.e("Destroy,","Yes")
        viewModel.removeObserver(this)
        super.onDestroy()
    }

    override fun onItemClick(pos: Int) {
        var map = HashMap<String,Any>()
        map.put("city",viewModel.cityList.get(pos))
        map.put("pos",3)
        changeScreenListner?.changeScreenFun(FragmentNameEnum.CITY_TEMRATURE_DETAILS_FRAGMENT, map)
    }

    override fun removeItem(cityName: String) {

       viewModel.cityList.remove(cityName)
        bookMarkedAdapter.notifyDataSetChanged()
        val gsonVal = Gson().toJson(viewModel.cityList)
        viewModel.saveCity(gsonVal)

        if(viewModel.cityList.isEmpty())
        {
            changeScreenListner?.changeScreenFun(FragmentNameEnum.MAP_FRAGMENT, 1)
        }
    }
}