package com.mic.throwing;

import java.util.ArrayList;

import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;

import com.mic.throwing.MainActivity.HeartBeatListener;
import com.mic.throwing.events.MicThrowGameEvent;
import com.mic.throwing.events.MicThrowGameEventHandler;

public class Heart extends Thread implements HeartBeatListener {
    public boolean                       beating = true;
    private MainView                     body;
    private ArrayList<MicThrowGameEvent> events;
    MicThrowGameEventHandler             handler;
    
    public Heart(MainView view) {
        handler = new MicThrowGameEventHandler();
        body = view;
        events = new ArrayList<MicThrowGameEvent>();
    }
    
    @Override
    public void run() {
        super.run();
        while (beating) {
            dispatchEvents();
            doDraw();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void dispatchEvents() {
        ArrayList<MicThrowGameEvent> removableEvents = new ArrayList<MicThrowGameEvent>();
        for (MicThrowGameEvent event : events) {
            // TODO dispatch event to handler
            // TODO delete dispatched event from events
            Log.i("dj", "dispatchEvents");
            dispatchEvent(event);
            removableEvents.add(event);
        }
        events.removeAll(removableEvents);
        
    }
    
    private void dispatchEvent(MicThrowGameEvent event) {
        Message msg = new Message();
        msg.what = event.whatEvent();
        msg.obj = event.obj();
        handler.sendMessage(msg);
    }
    
    public void doDraw() {
        body.doDraw();
    }
    
    @Override
    public void onTouch(MotionEvent event) {
        events.add(getMicEvent(event));
    }
    
    private MicThrowGameEvent getMicEvent(MotionEvent event) {
        return new MicThrowGameEvent(body, event);
    }
}