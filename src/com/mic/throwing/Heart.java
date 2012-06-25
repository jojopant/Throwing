package com.mic.throwing;

import java.util.ArrayList;

import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.mic.throwing.MainActivity.HeartBeatListener;
import com.mic.throwing.events.MicThrowGameEvent;
import com.mic.throwing.heart.UpdateSprites;
import com.mic.throwing.sprite.Sprite;

public class Heart extends Thread implements HeartBeatListener {
    public boolean                       beating = true;
    private SurfaceView                  body;
    private ArrayList<MicThrowGameEvent> events;
    Sprite                               plate;
    UpdateSprites                        updateHandler;
    
    public Heart(SurfaceView view, Sprite plate) {// Looper.prepare();
        updateHandler = new UpdateSprites();
        body = view;
        events = new ArrayList<MicThrowGameEvent>();
        this.plate = plate;
        
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
        // for (MicThrowGameEvent event : events) {
        // TODO dispatch event to handler
        // TODO delete dispatched event from events
        Log.i("dj", "dispatchEvents");
//        updateHandler.obtainMessage(1, plate);
        Message msg = new Message();
        msg.what=1;
        msg.obj=plate;
        updateHandler.sendMessage(msg);
        // }
    }
    
    public void doDraw() {
        ((MainView) body).doDraw();
    }
    
    @Override
    public void onTouch(MotionEvent event) {
        // TODO using event to build MicThrowGameEvents
        Log.i("dj", "Heard::onTouch");
        events.add(parseEvent(event));
        
    }
    
    // 知道小饼的坐标
    private MicThrowGameEvent parseEvent(MotionEvent event) {
        Log.i("dj", "MicThrowGameEvent parseEventh");
        int action = event.getAction();
        float lastX = 0;
        float lastY = 0;
        int micEventType = Constants.EVENT_UNKNOWN;
        if (action == MotionEvent.ACTION_DOWN) {
            lastX = event.getX();
            lastY = event.getY();
        } else if (action == MotionEvent.ACTION_MOVE) {
            micEventType = Constants.EVENT_MOVE;
        } else if (action == MotionEvent.ACTION_UP) {
            micEventType = Constants.EVENT_FLY;
        }
        
        return new MicThrowGameEvent(micEventType, event);
    }
}