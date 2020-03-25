package com.delta.mvvm1.view.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.delta.mvvm1.SBApp;
import com.delta.mvvm1.global.CommonUtils;
import com.delta.mvvm1.global.KeyboardWatcher;
import com.delta.mvvm1.global.SharedPref;
import com.delta.mvvm1.rest.SBAppInterface;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class BaseActivity extends AppCompatActivity implements KeyboardWatcher.OnKeyboardToggleListener {

    private KeyboardWatcher keyboardWatcher;

    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    public
    SBAppInterface sbAppInterface;
    @Inject
    public
    Gson gson;
    @Inject
    public CommonUtils commonUtils;
    @Inject
    public
    SharedPref sharedPref;
    @Inject
    DecimalFormat decimalFormat;
    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SBApp) getApplication()).getmNetComponent().inject(this);

    }

    @Override
    public void onKeyboardShown(int keyboardSize) {
    }

    @Override
    public void onKeyboardClosed() {
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }


}
