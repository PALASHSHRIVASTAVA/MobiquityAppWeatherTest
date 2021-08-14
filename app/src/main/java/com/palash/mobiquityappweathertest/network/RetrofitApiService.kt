package com.palash.mobiquityappweathertest.network

import com.palash.mobiquityappweathertest.model.CurrentDayResponse
import com.palash.mobiquityappweathertest.model.FiveDaysWatherResponse
import io.reactivex.Single
import retrofit2.http.*


interface RetrofitApiService {


    @GET(ONE_DAY_WATHER)
    @JvmSuppressWildcards
    fun getCurrentDayWather(
        @QueryMap values:Map<String,Any>
    ): Single<CurrentDayResponse>



    @GET(FIVE_DAYS_WEATHER)
    @JvmSuppressWildcards
    fun getFiveDayReport(
        @QueryMap values:Map<String,Any>
    ):Single<FiveDaysWatherResponse>

/*    @POST(STORE_ACTION)
    @FormUrlEncoded
    fun updateOrderStatus(
        @Query(value = "key") key: String,
        @Field(value = "token") token: String,
        @Field(value = "order_id") orderId: String,
        @Field(value = "action") action: String
    ):Single<GenricResponse<Any>>*/

}