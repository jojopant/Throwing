package com.mic.throwing.events;

import android.view.MotionEvent;

import com.mic.throwing.MainView;
import com.mic.throwing.sprite.Sprite;

public class MicThrowGameEvent {
    private Sprite obj;
    private int    event;
    
    public MicThrowGameEvent(MainView body, MotionEvent event) {
        parseEvent(body, event);
    }
    
    private void parseEvent(MainView body, MotionEvent event) {
        // TODO Auto-generated method stub
    }
    
    public Sprite obj() {
        return obj;
    }
    
    public int whatEvent() {
        return event;
    }
}
