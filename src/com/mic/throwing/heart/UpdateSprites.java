package com.mic.throwing.heart;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mic.throwing.sprite.Sprite;

public class UpdateSprites extends Handler {
      @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        super.handleMessage(msg);
        Log.i("dj", "handleMessage");
        Log.i("dj", "what:"+msg.what);
        Log.i("dj", "obj:"+msg.obj);
        switch(msg.what){
            case 1:{
                Log.i("dj", "case 1:{");
                ((Sprite)msg.obj).move(1, 1);
                break;
            }
        }
    }
    
}
