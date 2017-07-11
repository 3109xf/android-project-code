package com.myapp.map.demo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by Administrator on 2017/7/11 0011.
 */

public class ActivityBackStack implements Application.ActivityLifecycleCallbacks{

    private Stack<Activity> mActivityStack;
    public ActivityBackStack(){
        mActivityStack=new Stack<>();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
       if (activity!=null){
           mActivityStack.remove(activity);
       }
    }
    /**
     * 获取栈顶的activity
     */
    public Activity getLastActivity(){
        return mActivityStack.lastElement();
    }
    /**
     * 获取倒数第二个activity
     */
    public Activity getPerActivity(){
        int size=mActivityStack.size();
        if (size<2){
            return null;
        }
        return mActivityStack.get(size-2);

    }
    public int getSize(){
        return mActivityStack.size();
    }










}
