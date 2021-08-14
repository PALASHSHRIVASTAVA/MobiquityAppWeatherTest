package com.palash.mobiquityappweathertest.model

data class FiveDaysWatherResponse(
    val city: City? = City(),
    val cnt: Int? = 0,
    val cod: String? = "",
    val list: List<ListData>? = listOf(),
    val message: Int? = 0
)