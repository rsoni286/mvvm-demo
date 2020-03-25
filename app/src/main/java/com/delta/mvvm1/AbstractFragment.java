package com.delta.mvvm1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import com.delta.mvvm1.global.CommonUtils;
import com.delta.mvvm1.global.SharedPref;
import com.delta.mvvm1.rest.SBAppInterface;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import javax.inject.Inject;

import retrofit2.Retrofit;

public abstract class AbstractFragment extends Fragment {

    @Inject
    protected
    CommonUtils commonUtils;
    @Inject
    protected
    SBAppInterface sbAppInterface;
    @Inject
    public Gson gson;
    @Inject
    public
    SharedPref sharedPref;
    @Inject
    protected
    DecimalFormat decimalFormat;

    @Inject
    Retrofit retrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SBApp) getActivity().getApplication()).getmNetComponent().inject(this);
    }


}
