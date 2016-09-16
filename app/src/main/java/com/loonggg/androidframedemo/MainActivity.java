package com.loonggg.androidframedemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.loonggg.androidframedemo.app.CustomApp;
import com.loonggg.androidframedemo.injection.GlobalModule;
import com.loonggg.androidframedemo.net.rpc.UiRpcSubscriber;
import com.loonggg.androidframedemo.net.rpc.model.TestInfoModel;
import com.loonggg.androidframedemo.net.rpc.service.AppService;
import com.loonggg.androidframedemo.ui.basic.BasicTitleBarActivity;
import com.loonggg.androidframedemo.ui.serviceinjection.DaggerServiceComponent;
import com.loonggg.androidframedemo.ui.serviceinjection.ServiceModule;

import javax.inject.Inject;


public class MainActivity extends BasicTitleBarActivity {
    @Inject
    AppService mInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        initService();
    }

    @Override
    public boolean initializeTitleBar() {
        setLeftTitleButton(R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitleBarTextColor(Color.BLACK);
        setMiddleTitle(R.string.app_name);
        return true;
    }

    private void inject() {
        DaggerServiceComponent.builder()
                .globalModule(new GlobalModule((CustomApp) getApplication()))
                .serviceModule(new ServiceModule())
                .build()
                .inject(this);
    }

    private void initService(){

        manageRpcCall(mInfo.getTestInfo("444183889347c497b6505fcad0b29793", 1, 10, "json"), new UiRpcSubscriber<TestInfoModel>(this) {
            @Override
            protected void onSuccess(TestInfoModel testInfoModel) {
                showShortToast(testInfoModel.getList().get(0).getTitle());
            }

            @Override
            protected void onEnd() {

            }
        });
    }

}
