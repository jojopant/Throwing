package com.mic.throwing.events;

import android.graphics.Point;
import android.view.MotionEvent;

import com.mic.throwing.MainView;
import com.mic.throwing.sprite.Sprite;

public class MicThrowGameEvent {
    private Sprite sprite;
    private int    event;
    private Point  xy;
    
    public MicThrowGameEvent(MainView body, MotionEvent event) {
        parseEvent(body, event);
    }
    
    private void parseEvent(MainView body, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                parseMoveEvent(body, event);
                break;
            }
            case MotionEvent.ACTION_UP: {
                parseUpEvent(body, event);
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                parseDownEvent(body, event);
                break;
            }
        }
    }
    
    private void parseDownEvent(MainView body, MotionEvent event) {
        this.event = event_test_move;
        //        xy = new Point();
        //        xy.x = 10;
        //        xy.y = 10;
        sprite = body.plate;
    }
    
    private void parseUpEvent(MainView body, MotionEvent event) {
        // TODO Auto-generated method stub
    }
    
    private void parseMoveEvent(MainView body, MotionEvent event) {
        // TODO Auto-generated method stub
    }
    
    public Object obj() {
        return xy == null ? sprite : this;
    }
    
    public int whatEvent() {
        return event;
    }
    
    public Sprite getSprite() {
        return this.sprite;
    }
    
    public Point getXY() {
        return this.xy;
    }
    
    public static final int event_move_left_pie    = 1;
    public static final int event_move_middle_pie  = 2;
    public static final int event_move_right_pie   = 4;
    public static final int event_throw_left_pie   = 10;
    public static final int event_throw_middle_pie = 20;
    public static final int event_throw_right_pie  = 40;
    public static final int event_test_move        = -999;
}
