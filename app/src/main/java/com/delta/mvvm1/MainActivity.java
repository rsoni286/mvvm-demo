package com.delta.mvvm1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.delta.mvvm1.model.AnalyticsCounterResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private Dialog dialog;
    LinearLayout llLayout;

    TextView tvTotalOrders, tvTotalZeroOrders, tvTotalOrderValue, tvAverageOrderValue, tvCollectionAmountPending,
            tvCollectionAmountCleared, tvExpenseReported, tvPresentDays, tvLeaves, tvScheduledEffectiveCalls,
            tvUnscheduledEffectiveCalls, tvTotalEffectiveCalls, tvTargetVisits, tvAverageDailyWorkingHour;

    AnalyticsCounterResponseModel.Data dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        dialog = commonUtils.createCustomLoader(this, false);

        llLayout = findViewById(R.id.llLayout);

        tvTotalOrders = findViewById(R.id.tvTotalOrders);
        tvTotalZeroOrders = findViewById(R.id.tvTotalZeroOrders);
        tvTotalOrderValue = findViewById(R.id.tvTotalOrderValue);
        tvAverageOrderValue = findViewById(R.id.tvAverageOrderValue);
        tvCollectionAmountPending = findViewById(R.id.tvCollectionAmountPending);
        tvCollectionAmountCleared = findViewById(R.id.tvCollectionAmountCleared);
        tvExpenseReported = findViewById(R.id.tvExpenseReported);
        tvPresentDays = findViewById(R.id.tvPresentDays);
        tvLeaves = findViewById(R.id.tvLeaves);
        tvScheduledEffectiveCalls = findViewById(R.id.tvScheduledEffectiveCalls);
        tvUnscheduledEffectiveCalls = findViewById(R.id.tvUnscheduledEffectiveCalls);
        tvTotalEffectiveCalls = findViewById(R.id.tvTotalEffectiveCalls);
        tvTargetVisits = findViewById(R.id.tvTargetVisits);
        tvAverageDailyWorkingHour = findViewById(R.id.tvAverageDailyWorkingHour);

        getDataFromServer();

    }

    private void getDataFromServer() {
        if (commonUtils.isNetworkAvailable()) {
            commonUtils.showCustomDialog(dialog, this);

            Call<AnalyticsCounterResponseModel> call = sbAppInterface.getAllAnalyticsCounterData("237", "2020-02-22", "2020-03-02");
            call.enqueue(new Callback<AnalyticsCounterResponseModel>() {
                @Override
                public void onResponse(Call<AnalyticsCounterResponseModel> call, Response<AnalyticsCounterResponseModel> response) {
                    if (response.isSuccessful()) {
                        commonUtils.dismissCustomDialog(dialog);

                        AnalyticsCounterResponseModel model = response.body();
                        if (model != null) {
                            if (model.getSuccess()) {
                                dataList = model.getData();
                                tvTotalOrders.setText(dataList.getTotalOrders()+"");
                                tvTotalZeroOrders.setText(dataList.getTotalZeroOrders()+"");
                                tvTotalOrderValue.setText(dataList.getTotalOrderValue()+"");
                                tvCollectionAmountPending.setText(dataList.getTotalPendingCollectionAmount()+"");
                                tvCollectionAmountCleared.setText(dataList.getTotalClearedCollectionAmount()+"");
                                tvExpenseReported.setText(dataList.getTotalExpense()+"");
                                tvPresentDays.setText(dataList.getPresentDays()+"");
                                tvLeaves.setText(dataList.getLeaves()+"");
                                tvScheduledEffectiveCalls.setText(dataList.getScheduledEffectiveCalls()+"");
                                tvUnscheduledEffectiveCalls.setText(dataList.getUnscheduledEffectiveCalls()+"");


                            } else {
                                commonUtils.dismissCustomDialog(dialog);
                                commonUtils.snackbar(llLayout, model.getMessage());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<AnalyticsCounterResponseModel> call, Throwable t) {
                    commonUtils.dismissCustomDialog(dialog);
                }
            });
        } else {
            commonUtils.internetConnectionSnackbar(llLayout);
        }
    }
}
