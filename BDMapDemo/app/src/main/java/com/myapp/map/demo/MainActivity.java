package com.myapp.map.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {
    public BDLocationListener myListener = new MyLocationListener();
    public LocationClient mLocationClient = null;
    MapView mMapView = null;
    BaiduMap mBaiduMap;
    double mLatitude=34.793942;
    double mLongitude=113.599854;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
      //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option=new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setScanSpan(2000);     //多久定位一次
        mLocationClient.start();
        setMapCenter(mLatitude,mLongitude);
        Intent intent=new Intent(this,MarkerClusterDemo.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
         //   Log.e("zuobiao",mLatitude+"-----"+mLongitude);
            setMapCenter(mLatitude,mLongitude);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }


    private void mapPoint(double mLatitude,
            double mLongitude){

        //定义Maker坐标点
        LatLng point = new LatLng(mLatitude, mLongitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
         //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
         //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

       // mLocationClient.setLocOption(option);
    }
    private void setMapCenter(double mLatitude,double mLongitude){
        LatLng cenpt = new LatLng(mLatitude, mLongitude);
        MapStatus mapStatus = new MapStatus.Builder().target(cenpt).zoom(18)
                .build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory
                .newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);
        mapPoint(mLatitude,mLongitude);
    }


}
