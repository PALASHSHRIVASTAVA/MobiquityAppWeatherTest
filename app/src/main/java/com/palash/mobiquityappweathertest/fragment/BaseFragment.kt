package com.palash.mobiquityappweathertest.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.palash.mobiquityappweathertest.network.NetworkErrorListner

abstract class BaseFragment : Fragment(), NetworkErrorListner
{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initValue(view)
    }

    override fun networkErrorOccur(msg: String) {

        Toast.makeText(activity,"network error",Toast.LENGTH_SHORT).show()
    }

    abstract fun initValue(view:View);

}