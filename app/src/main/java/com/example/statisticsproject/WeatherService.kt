package com.example.statisticsproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherService {
    @GET("avc_dictionary/jsonResponce/saveData.php?")
     fun saveStatistics(
        @Query(
            value =
            "ajax", encoded = true
        ) ajax: Int,
        @Query(
            value =
            "unit_info[unit_name]", encoded = true
        ) unitInfo: String,
        @Query(
            value =
            "unit_info[item_name]", encoded = true
        ) itemName: Int,
        @Query(
            value =
            "unit_info[type]", encoded = true
        ) type: String,
        @Query(
            value =
            "is_first", encoded = true
        ) isFirst: Boolean,
        @Query(
            value =
            "page_number", encoded = true
        ) pageNumber: Int,
        @Query(
            value =
            "statusData[correct]", encoded = true
        ) statusDataCorrect: Int,
        @Query(
            value =
            "statusData[incorrect]", encoded = true
        ) statusDataInCorrect: Int,
        @Query(
            value =
            "statusData[total]", encoded = true
        ) total: Int,
        @Query(
            value =
            "token", encoded = true
        ) token: String,
        @Query(value = "user_id", encoded = true) user_id: String
    ): Call<WeatherResponse>

}