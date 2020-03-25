package com.delta.mvvm1.repo;

import com.delta.mvvm1.global.CommonUtils;
import com.delta.mvvm1.model.AnalyticsCounterResponseModel;
import com.delta.mvvm1.rest.SBAppInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalyticsRepo {


    public void getData(SBAppInterface sbAppInterface, RepoCallBacks callBacks) {
        callBacks.onProgress();
        getDataFromServer(sbAppInterface, callBacks);
    }

    private void getDataFromServer(SBAppInterface sbAppInterface, RepoCallBacks callBacks) {

        Call<AnalyticsCounterResponseModel> call = sbAppInterface.getAllAnalyticsCounterData("237", "2020-02-22", "2020-03-02");
        call.enqueue(new Callback<AnalyticsCounterResponseModel>() {
            @Override
            public void onResponse(Call<AnalyticsCounterResponseModel> call, Response<AnalyticsCounterResponseModel> response) {
                if (response.isSuccessful()) {

                    AnalyticsCounterResponseModel model = response.body();
                    if (model != null) {
                        if (model.getSuccess()) {
                            callBacks.onSuccess(true, model.getData(), AnalyticsCounterResponseModel.Data.class);
                        } else {
                            callBacks.onFailure(true, model.getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AnalyticsCounterResponseModel> call, Throwable t) {
                callBacks.onFailure(true, t.getLocalizedMessage());
            }
        });

    }

}
