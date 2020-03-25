package com.delta.mvvm1.view.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.delta.mvvm1.R;
import com.delta.mvvm1.model.AnalyticsCounterResponseModel;
import com.delta.mvvm1.viewmodel.AnalyticsViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private Dialog dialog;
    LinearLayout llLayout;

    TextView tvTotalOrders, tvTotalZeroOrders, tvTotalOrderValue, tvAverageOrderValue, tvCollectionAmountPending,
            tvCollectionAmountCleared, tvExpenseReported, tvPresentDays, tvLeaves, tvScheduledEffectiveCalls,
            tvUnscheduledEffectiveCalls, tvTotalEffectiveCalls, tvTargetVisits, tvAverageDailyWorkingHour;


    AnalyticsViewModel analyticsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        analyticsViewModel = new ViewModelProvider(this).get(AnalyticsViewModel.class);
        analyticsViewModel.init(sbAppInterface);

        analyticsViewModel.getStatus().observe(this, status -> {
            if (status != null)
                switch (status) {
                    case 0:
                        showInProgress();
                        break;
                    case 1:
                    case -1:
                        hideInProgress();
                        break;
                }
        });


        analyticsViewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                commonUtils.snackbar(llLayout, error);
            }

        });

        analyticsViewModel.getDataList().observe(this, dataList -> {
            if (dataList != null) {
                setData(dataList);
            }
        });
    }

    private void hideInProgress() {
        commonUtils.dismissCustomDialog(dialog);
    }

    private void showInProgress() {
        commonUtils.showCustomDialog(dialog, this);
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


    }

    private void setData(AnalyticsCounterResponseModel.Data dataList) {
        tvTotalOrders.setText(dataList.getTotalOrders() + "");
        tvTotalZeroOrders.setText(dataList.getTotalZeroOrders() + "");
        tvTotalOrderValue.setText(dataList.getTotalOrderValue() + "");
        tvCollectionAmountPending.setText(dataList.getTotalPendingCollectionAmount() + "");
        tvCollectionAmountCleared.setText(dataList.getTotalClearedCollectionAmount() + "");
        tvExpenseReported.setText(dataList.getTotalExpense() + "");
        tvPresentDays.setText(dataList.getPresentDays() + "");
        tvLeaves.setText(dataList.getLeaves() + "");
        tvScheduledEffectiveCalls.setText(dataList.getScheduledEffectiveCalls() + "");
        tvUnscheduledEffectiveCalls.setText(dataList.getUnscheduledEffectiveCalls() + "");
    }

    // we are calling this inside analticsRepository
   /* private void getDataFromServer() {
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
                                tvTotalOrders.setText(dataList.getTotalOrders() + "");
                                tvTotalZeroOrders.setText(dataList.getTotalZeroOrders() + "");
                                tvTotalOrderValue.setText(dataList.getTotalOrderValue() + "");
                                tvCollectionAmountPending.setText(dataList.getTotalPendingCollectionAmount() + "");
                                tvCollectionAmountCleared.setText(dataList.getTotalClearedCollectionAmount() + "");
                                tvExpenseReported.setText(dataList.getTotalExpense() + "");
                                tvPresentDays.setText(dataList.getPresentDays() + "");
                                tvLeaves.setText(dataList.getLeaves() + "");
                                tvScheduledEffectiveCalls.setText(dataList.getScheduledEffectiveCalls() + "");
                                tvUnscheduledEffectiveCalls.setText(dataList.getUnscheduledEffectiveCalls() + "");


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
    }*/
}
