package com.yan.campusbbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;

import com.yan.campusbbs.base.BaseActivity;
import com.yan.campusbbs.module.MainActivity;
import com.yan.campusbbs.rxbusaction.ActionChangeSkin;
import com.yan.campusbbs.rxbusaction.ActionMainActivityShowComplete;
import com.yan.campusbbs.util.setting.SettingHelper;
import com.yan.campusbbs.util.setting.SettingModule;
import com.yan.campusbbs.util.RxBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class FlashActivity extends BaseActivity {

    @Inject
    RxBus rxBus;

    @Inject
    SettingHelper settingHelper;
    @BindView(R.id.activity_flash)
    CoordinatorLayout activityFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);
        daggerInject();
        initRxBusDisposable();
        settingInit();
    }

    public void daggerInject() {
        DaggerFlashComponent.builder()
                .applicationComponent(((ApplicationCampusBBS) getApplication())
                        .getApplicationComponent())
                .settingModule(new SettingModule(this, compositeDisposable))
                .build().inject(this);
    }

    private void initRxBusDisposable() {
        addDisposable(Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> {
                    startActivity(new Intent(FlashActivity.this, MainActivity.class));
                }));

        addDisposable(rxBus.getEvent(ActionMainActivityShowComplete.class)
                .subscribe(actionMainActivityShowComplete -> {
                    finish();
                }));
    }

    @Override
    public void changeSkin(ActionChangeSkin actionChangeSkin) {
        super.changeSkin(actionChangeSkin);
        activityFlash.setBackgroundResource(actionChangeSkin.getColorPrimaryId());
    }
}
