package com.palash.mobiquityappweathertest.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.palash.mobiquityappweathertest.R
import com.palash.mobiquityappweathertest.interface_listner.ChangeScreenListner
import com.palash.mobiquityappweathertest.network.APP_ID
import com.palash.mobiquityappweathertest.util.FragmentNameEnum
import com.palash.mobiquityappweathertest.util.Logs
import com.palash.mobiquityappweathertest.viewmodel.MapViewViewModel
import kotlinx.android.synthetic.main.fragment_select_city_maps.*
import kotlinx.android.synthetic.main.place_layout.*
import org.koin.android.viewmodel.ext.android.viewModel


class SelectCityMapsFragment : BaseFragment(){

    private val viewModel: MapViewViewModel by viewModel()
    var isMapMoved = false;
    private var changeScreenListner: ChangeScreenListner? =null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
      //  googleMap.isMyLocationEnabled = false

        googleMap.uiSettings.isZoomControlsEnabled = false
     //   googleMap.mapType = MAP_TYPE_NORMAL
        googleMap.setMinZoomPreference(2f)
        googleMap.setMaxZoomPreference(8f)

        val heydrbadLtlong = LatLng(17.485503736765857, 78.49876452237368)
     /*   val marker =
            MarkerOptions()
                .position(heydrbadLtlong)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        googleMap.addMarker(
            marker
                //.title("My Location")
        )*/

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(heydrbadLtlong))
        // Move the camera instantly to Sydney with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(heydrbadLtlong));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(heydrbadLtlong, 18f), 5000, null);

        googleMap.setOnCameraMoveListener {
            isMapMoved = true
            imageViewBookMarked.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            textViewCity.text = "Place..."
            textViewTemp.text = "Temperature..."
/*            val midLatLng: LatLng = googleMap.getCameraPosition().target
            if (marker != null) marker.position(midLatLng) else Log.d("TAG", "Marker is null")*/
        }

        googleMap.setOnCameraIdleListener {
            Logs.e("IDEl", googleMap.getCameraPosition().target.toString());
            isMapMoved = false

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                if(!isMapMoved)
                {
                    val latLng = googleMap.getCameraPosition().target;
                    if(latLng!=null)
                    {
                        val map = HashMap<String,Any>()
                       // map.put("units","metric")
                        if(viewModel.getUnitValue()!=null)
                        {
                            map.put("units", viewModel.getUnitValue()!!)
                        }
                        else{
                            map.put("units","metric")
                        }
                        map.put("lat",latLng.latitude)
                        map.put("lon",latLng.longitude)
                        map.put("appid", APP_ID)

                        viewModel.getCityTemprature(map,this)
                    }
                }
            }, 500)



        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_city_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }

    override fun initValue(view: View) {
        Logs.e("init",""+1)


        imageViewBookMarked.setOnClickListener {
            if(!textViewCity.text.toString().equals("Place...",true))
            {
                if(!viewModel.cityList.contains(textViewCity.text.toString()))
                    {
                        viewModel.cityList.add(textViewCity.text.toString())
                        imageViewBookMarked.setImageResource(R.drawable.ic_baseline_bookmark_24)
                        val gsonVal = Gson().toJson(viewModel.cityList)
                        viewModel.saveCity(gsonVal)
                        changeScreenListner?.changeScreenFun(FragmentNameEnum.CITY_LIST_FRAGMENT, 2)

                    }
                else{
                    viewModel.cityList.remove(textViewCity.text.toString())
                    imageViewBookMarked.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                    val gsonVal = Gson().toJson(viewModel.cityList)
                    viewModel.saveCity(gsonVal)
                }


            }
        }

        viewModel.cityListMutableLiveData.observe(this,  {

            if (it != null && !it.isEmpty()) {
                viewModel.cityList.addAll(it)
            }
        })

        viewModel.currentDayMutableLiveData.observe(this,  {


            if(it?.name!=null)
            {
                textViewCity.text = it?.name

                var isSame = viewModel.cityList.contains(it.name)
            if(isSame)
            {
                imageViewBookMarked.setImageResource(R.drawable.ic_baseline_bookmark_24)
            }
            else{
                imageViewBookMarked.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            }

            }

            val degreesymbol = '\u00B0'
                textViewTemp.text = it?.main?.temp.toString()+" "+degreesymbol
            if(viewModel.getUnitValue()!=null && viewModel.getUnitValue().contentEquals("imperial",true) )
            {
                textViewTemp.append("F")
            }
            else{
                textViewTemp.append("C")
            }
            if(it?.weather?.isNotEmpty() == true)
            {
                textViewTemp.append(" "+it?.weather?.get(0)?.main)
            }

        })

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ChangeScreenListner)
        {
            changeScreenListner = context
        }
    }

    override fun onDestroy() {
        Log.e("Destroy,","Yes")
        viewModel.removeObserver(this)
        super.onDestroy()
    }

    companion object {
        fun newInstance() = SelectCityMapsFragment()
    }
}