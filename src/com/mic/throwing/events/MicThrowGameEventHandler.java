package com.mic.throwing.events;

import com.mic.throwing.sprite.Sprite;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MicThrowGameEventHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        super.handleMessage(msg);
        Log.i("dj", "handling message: " + msg);
        switch (msg.what) {
            case 1: {
                ((Sprite) msg.obj).move(1, 1);
                break;
            }
        }
    }
}
