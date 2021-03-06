package com.yan.campusbbs;

import android.content.Context;

import com.yan.campusbbs.setting.AdapterImageControl;
import com.yan.campusbbs.setting.ImageControl;
import com.yan.campusbbs.repository.AppRetrofit;
import com.yan.campusbbs.util.RxBus;
import com.yan.campusbbs.util.ToastUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getContext();

    RxBus getRxBus();

    ToastUtils getToastUtil();

    AdapterImageControl getAdapterImageControl();

    ImageControl getImageControl();

    AppRetrofit getAppRetrofit();
}
