package com.delta.mvvm1.rest;


import com.delta.mvvm1.model.AnalyticsCounterResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SBAppInterface {

    @GET("count-parameters")
    Call<AnalyticsCounterResponseModel> getAllAnalyticsCounterData(@Query("id") String id,
                                                                   @Query("start_date") String start_date,
                                                                   @Query("end_date") String end_date);
}