package com.loonggg.androidframedemo.app;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.loonggg.androidframedemo.injection.DaggerGlobalComponent;
import com.loonggg.androidframedemo.injection.GlobalComponent;
import com.loonggg.androidframedemo.injection.GlobalModule;
import com.loonggg.androidframedemo.log.Logger;


public class CustomApp extends Application {

  private GlobalComponent mGlobalComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    Logger.init();
    Fresco.initialize(this);
    mGlobalComponent = DaggerGlobalComponent.builder().globalModule(new GlobalModule(this)).build();
  }

  public GlobalComponent getGlobalComponent() {
    return mGlobalComponent;
  }
}
