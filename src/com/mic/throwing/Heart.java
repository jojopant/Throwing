package com.mic.throwing;

import java.util.ArrayList;

import android.view.MotionEvent;
import android.view.SurfaceView;

import com.mic.throwing.MainActivity.HeartBeatListener;
import com.mic.throwing.events.MicThrowGameEvent;

public class Heart extends Thread implements HeartBeatListener {
    public boolean                       beating = true;
    private SurfaceView                  body;
    private ArrayList<MicThrowGameEvent> events;
    
    public Heart(SurfaceView view) {
        body = view;
        events = new ArrayList<MicThrowGameEvent>();
        
    }
    
    @Override
    public void run() {
        super.run();
        while (beating) {
            dispatchInputEvents();
            dispatchUpdateSprites();
            dispatchCollision();
            doDraw();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private void dispatchCollision() {
        // TODO Auto-generated method stub
        
    }
    
    private void dispatchUpdateSprites() {
        // TODO Auto-generated method stub
        
    }
    
    private void dispatchInputEvents() {
        for (MicThrowGameEvent event : events) {
            // TODO dispatch event to handler
            // TODO delete dispatched event from events
        }
    }
    
    public void doDraw() {
        ((MainView) body).doDraw();
    }
    
    @Override
    public void onTouch(MotionEvent event) {
        // TODO using event to build MicThrowGameEvents
        events.add(parseEvent(event));
    }
    
    private MicThrowGameEvent parseEvent(MotionEvent event) {
        
        return new MicThrowGameEvent();
    }
}