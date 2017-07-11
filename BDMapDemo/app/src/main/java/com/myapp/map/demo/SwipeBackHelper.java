package com.myapp.map.demo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/7/11 0011.
 */

public class SwipeBackHelper {
    private final static String TAG="Gmw";
    private Window mWindow;
    private Activity mCurrentActivity;
    private Activity mPreviousActivity;
    private FrameLayout mCurrentContentView;
    private View mPreviousContentView;
    private float mLastX;
    private float mDistanceX;
    private boolean isSliding;
    private boolean mSlidAnimLock;
    private int mWidth;

    private static final int DEFAULT_POINT_X=40;
    private static final int DEFAULT_CANCEL_DURATION=200;
    private static final int DEFAULT_FINISH_DURATION=400;
    private static final int DEFAULT_BACK_DURATION=600;

    public SwipeBackHelper(Window window){
        mWindow=window;
//        mCurrentContentView=
    }
    /**
     * 把activity栈中的上一个activity的contentview拿出来添加到当前的activity的contentview中
     * 并且index=0；即在framelayout的最底层
     */
    public boolean addPreviousView(){
        if (mCurrentContentView.getChildCount()==0){
            mCurrentContentView=null;
            mPreviousActivity=null;
            return false;
        }
        MyApplication application= (MyApplication) mWindow.getContext().getApplicationContext();
        mPreviousActivity=application.getBackHelper().getPerActivity();
        if (mPreviousActivity==null){
            mCurrentContentView=null;
            mPreviousActivity=null;
            return false;
        }

//        ViewGroup preeviousContainer=getContentView()
       return false;





    }

    /**
     * 根据@android.R.id.content 从window中获取activity的根布局
     *
     */
    private FrameLayout getContentView(Window window){
        if (window==null){
            return null;
        }
        return (FrameLayout) window.getDecorView().findViewById(android.R.id.content);
    }
















}
