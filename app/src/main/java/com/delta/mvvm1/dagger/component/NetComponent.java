package com.delta.mvvm1.dagger.component;


import com.delta.mvvm1.AbstractFragment;
import com.delta.mvvm1.BaseActivity;
import com.delta.mvvm1.dagger.module.APIModule;
import com.delta.mvvm1.dagger.module.AppModule;
import com.delta.mvvm1.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, APIModule.class})
public interface NetComponent {
    void inject(BaseActivity baseActivity);
    void inject(AbstractFragment abstractFragment);
}
