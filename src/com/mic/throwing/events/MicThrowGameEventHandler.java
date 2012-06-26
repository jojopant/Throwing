package com.mic.throwing.events;

import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mic.throwing.sprite.Sprite;

public class MicThrowGameEventHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Log.i("dj", "handling message: " + msg);
        switch (msg.what) {
            case MicThrowGameEvent.event_test_move: {
                ((Sprite) msg.obj).move(10, 10);
                break;
            }
            case MicThrowGameEvent.event_move_left_pie: {
                MicThrowGameEvent event = (MicThrowGameEvent) msg.obj;
                Sprite sprite = event.getSprite();
                Point xy = event.getXY();
                sprite.move(xy.x, xy.y);
                break;
            }
        }
    }
}
