package com.mic.throwing.events;

import android.view.MotionEvent;


public class MicThrowGameEvent {
    int mEventType;
    MotionEvent mEvent;
    //��֪С��������
    public MicThrowGameEvent(int eventType, MotionEvent event){
        mEventType =eventType;
        mEvent=event;
        // mv.xxxx �Ƿ�touch��С��
        
    }
}
