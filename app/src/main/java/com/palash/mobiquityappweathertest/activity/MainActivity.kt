package com.palash.mobiquityappweathertest.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.palash.mobiquityappweathertest.R
import com.palash.mobiquityappweathertest.databinding.ActivityMainBinding
import com.palash.mobiquityappweathertest.fragment.*
import com.palash.mobiquityappweathertest.interface_listner.ChangeScreenListner
import com.palash.mobiquityappweathertest.util.FragmentNameEnum
import com.palash.mobiquityappweathertest.util.Logs
import com.palash.mobiquityappweathertest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ChangeScreenListner {

    private lateinit var binding: ActivityMainBinding

    val mainViewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        binding.fab.setOnClickListener { view ->
            binding.fab.hide()
            changeFragment(SelectCityMapsFragment.newInstance(),"")
        }

        initObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                fab.visibility = View.INVISIBLE
                changeFragment(SettingFragment.newInstance(), "")
                true
            }
            R.id.action_information -> {
                fab.visibility = View.INVISIBLE
                changeFragment(InformationFragment.newInstance(), "")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    fun initObserver()
    {
        mainViewModel.isDialogShow.observe(this, Observer {
            Logs.e("post",""+it)
            if(it)
            {
                progressBar.visibility = View.VISIBLE
            }
            else{

                progressBar.visibility = View.GONE
            }
        })

        mainViewModel.cityListMutableLiveData.observe(this,  {

            if (it != null && !it.isEmpty()) {
                changeScreenFun(FragmentNameEnum.CITY_LIST_FRAGMENT, 1)
            }
            else{
                changeScreenFun(FragmentNameEnum.MAP_FRAGMENT, 1)
            }
        })
    }

    override fun <V> changeScreenFun(screenName: FragmentNameEnum, v: V) {
     //   TODO("Not yet implemented")
        binding.fab.hide()

        when (screenName) {

            FragmentNameEnum.MAP_FRAGMENT->{
                val fragmentManager = supportFragmentManager

                    while (fragmentManager.backStackEntryCount>2)
                    {
                        fragmentManager.popBackStack()
                    }

                changeFragment(SelectCityMapsFragment.newInstance(),null)
            }
            else -> {
                if(v is Int)
                {
                    // 1 and 2 for BookMarkedCityFragment 1= add and 2 == replace fragment
                    if(v==1)
                    {
                        val fragmentManager = supportFragmentManager

                        while (fragmentManager.backStackEntryCount>1)
                        {
                            fragmentManager.popBackStack()
                        }

                        changeFragment(BookMarkedCityFragment.newInstance(true),null)
                    }
                   else  if(v==2)
                    {
                        changeFragment(BookMarkedCityFragment.newInstance(false),"")
                    }

                    else{

                    }
                }
                else if(v is HashMap<*, *>)
                {
                  var cityName = v.get("city") as String
                    var pos = v.get("pos") as Int
                    if(pos==3)
                    {
                        changeFragment(CityTempratureDetailsFragment.newInstance(cityName),"")
                    }
                }
            }

        }

    }

    override fun showNaveButton(isShow: Boolean) {
        if(isShow)
        {
            binding.fab.show()
        }
    }


    fun changeFragment(frament: Fragment, screenName: String?) {


        val fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        if (screenName == null) {
            ft.add(R.id.main_container, frament)
        } else {
            ft.replace(R.id.main_container, frament)
            ft.addToBackStack(null)
        }
        ft.commitAllowingStateLoss()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    }
}


//https://api.openweathermap.org/data/2.5/weather?q=Bhopal&appid=fae7190d7e6433ec3a45285ffcf55c86 one day

// https://api.openweathermap.org/data/2.5/forecast?q=Bhopal&appid=fae7190d7e6433ec3a45285ffcf55c86&units=metric