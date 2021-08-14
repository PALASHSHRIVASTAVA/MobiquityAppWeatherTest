package com.palash.mobiquityappweathertest


import com.palash.mobiquityappweathertest.fragment.SettingViewModel
import com.palash.mobiquityappweathertest.network.RxSingleSchedulers
import com.palash.mobiquityappweathertest.network.createBasicAuthService
import com.palash.mobiquityappweathertest.repository.NetworkRepository
import com.palash.mobiquityappweathertest.utils.SharedPrefrenceDB
import com.palash.mobiquityappweathertest.utils.getDialogShowMutableLiveData
import com.palash.mobiquityappweathertest.viewmodel.BookMarkedCityViewModel
import com.palash.mobiquityappweathertest.viewmodel.CityTempratureDetailsViewModel
import com.palash.mobiquityappweathertest.viewmodel.MainViewModel
import com.palash.mobiquityappweathertest.viewmodel.MapViewViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val networkModule = module {
    single { createBasicAuthService(BuildConfig.SERVER_URL) }
    single { RxSingleSchedulers.DEFAULT }

}
val dialogShow = module {

    single {
        getDialogShowMutableLiveData()
    }
}

val networkRepository = module {
    single { NetworkRepository(get(),get(),get(),get()) }
}

val sharedPrefrenceDB = module {
    single { SharedPrefrenceDB(get()) }
}


val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }

    viewModel {
        MapViewViewModel(get(),get())
    }

    viewModel {
        BookMarkedCityViewModel(get(),get())
    }
    viewModel {
        CityTempratureDetailsViewModel(get(),get())
    }

    viewModel {
        SettingViewModel(get(),get())
    }
/*
    viewModel {
        SplashAndLoginViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        CancelledViewModel(get())
    }

    viewModel {
        RestaurentDetailsViewModel(get())
    }
    viewModel {
        UserMainViewModel(get())
    }
    viewModel {
        BankDetailsViewModel(get())
    }*/
}

