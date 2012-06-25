package com.mic.throwing.events;

import android.view.MotionEvent;


public class MicThrowGameEvent {
    int mEventType;
    MotionEvent mEvent;
    //已知小饼的坐标
    public MicThrowGameEvent(int eventType, MotionEvent event){
        mEventType =eventType;
        mEvent=event;
        // mv.xxxx 是否touch到小饼
        
    }
}
