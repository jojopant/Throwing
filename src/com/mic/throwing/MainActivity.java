package com.mic.throwing;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {
    int                       scene = Constants.SCENE_UNKNOWN;
    int                       mode  = Constants.MODE_UNKNOWN;
    private HeartBeatListener heartBeatListener;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = getIntent().getIntExtra(Constants.MODE, Constants.MODE_UNKNOWN);
        scene = getIntent().getIntExtra(Constants.SCENE,
            Constants.SCENE_UNKNOWN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(new MainView(this));
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (heartBeatListener != null) {
            heartBeatListener.onTouch(event);
        }
        
        return true;
    }
    
    public interface HeartBeatListener {
        public void onTouch(MotionEvent event);
    }
    
    public void registerHeartBeatListrener(HeartBeatListener heartBeatListener) {
        this.heartBeatListener = heartBeatListener;
    }
    
}