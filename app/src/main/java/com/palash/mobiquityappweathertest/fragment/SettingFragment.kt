package com.palash.mobiquityappweathertest.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import com.palash.mobiquityappweathertest.R
import kotlinx.android.synthetic.main.setting_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class SettingFragment : BaseFragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private val viewModel: SettingViewModel by viewModel()

    override fun initValue(view: View) {


        if (viewModel.getUnitValue() != null && viewModel.getUnitValue()?.equals("imperial")!!) {
            toggleButton.isChecked = true
        } else {
            toggleButton.isChecked = false
        }

        toggleButton.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.setUnitValues("imperial")

            } else {
                viewModel.setUnitValues("metric")
            }
        })


        buttonClear.setOnClickListener {
            val builder1: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            builder1.setMessage("Are you sure, you want to removed all book marked places.")
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Yes",
                { dialog, id ->
                    viewModel.resetAllBookMarked()
                })

            builder1.setNegativeButton(
                "No",
                { dialog, id -> dialog.cancel() })

            val alert11: AlertDialog = builder1.create()
            alert11.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }


}