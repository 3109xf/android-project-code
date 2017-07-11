package com.myapp.map.demo;

import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class MyApplication extends Application{

    private ActivityBackStack mBackStack;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        mBackStack=new ActivityBackStack();
        registerActivityLifecycleCallbacks(mBackStack);
    }

    public ActivityBackStack getBackHelper(){
        return mBackStack;
    }





    }
