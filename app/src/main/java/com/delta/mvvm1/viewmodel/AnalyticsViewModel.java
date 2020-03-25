package com.delta.mvvm1.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.delta.mvvm1.model.AnalyticsCounterResponseModel;
import com.delta.mvvm1.repo.AnalyticsRepo;
import com.delta.mvvm1.repo.RepoCallBacks;
import com.delta.mvvm1.rest.SBAppInterface;


public class AnalyticsViewModel extends ViewModel implements RepoCallBacks {

    private static final String TAG = "AnalyticsViewModel";
    private MutableLiveData<AnalyticsCounterResponseModel.Data> dataList = new MutableLiveData<>();
    private MutableLiveData<Integer> status = new MutableLiveData<>();//0 for progress //1 for success //-1 for error
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();//this is for showing error messages
    private AnalyticsRepo analyticsRepo;

    public void init(SBAppInterface sbAppInterface) {
        if (analyticsRepo != null)
            return;//this check is done to ensure that viewmodel is not reinitialized on rotation
        analyticsRepo = new AnalyticsRepo();
        analyticsRepo.getData(sbAppInterface, this);
    }

    public void reloadData(SBAppInterface sbAppInterface) {
        if (analyticsRepo == null) throw new NullPointerException("Please call init() first");
        analyticsRepo.getData(sbAppInterface, this);
    }

    //returning LiveData instead of MutableLiveData cause we dont want this data to be changed from activity
    public LiveData<AnalyticsCounterResponseModel.Data> getDataList() {
        return dataList;
    }

    public LiveData<Integer> getStatus() {
        return status;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void onProgress() {
        //we set status value to 0 which indicates in progress which is then used by activity to show progress
        //we handle on progress state here such as showing progress bar
        status.postValue(0);
    }

    @Override
    public <T> void onSuccess(boolean remote, @Nullable T localData, Class<T> type) {
        //we handle on success state here such as showing data
        AnalyticsCounterResponseModel.Data analyticsData = (AnalyticsCounterResponseModel.Data) localData;
        dataList.postValue(analyticsData);
        status.postValue(1);
    }

    @Override
    public void onFailure(boolean remote, @Nullable String error) {
        //we set status value to -1 here
        //we handle on failure state here such as showing error message
        status.postValue(-1);
        errorMessage.postValue(error);
    }
}
