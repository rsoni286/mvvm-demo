package com.delta.mvvm1.dagger.module;


import com.delta.mvvm1.rest.SBAppInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module(includes = {NetModule.class})
public class APIModule {

    @Provides
    @Singleton
    public SBAppInterface provideSbAppInterface(Retrofit retrofit) {
        return retrofit.create(SBAppInterface.class);
    }
}
