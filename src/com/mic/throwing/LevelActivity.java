package com.mic.throwing;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;


public class LevelActivity extends Activity {
    int mode = Constants.MODE_UNKNOWN;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.level);
    }
     @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
    
    public void easy(View view) {
        mode = Constants.MODE_EASY;
        startSceneActivity();
    }
    
    public void normal(View view) {
        mode = Constants.MODE_NORMAL;
        startSceneActivity();
    }
    
    public void hard(View view) {
        mode = Constants.MODE_HARD;
        startSceneActivity();
    }
    
    public void help(View view) {
        startActivity(new Intent(this, HelpActivity.class));
    }
    
    public void quit(View view) {
        this.finish();
    }
    
    public void startSceneActivity() {
        Intent intent = new Intent(this, SceneActivity.class);
        intent.putExtra(Constants.MODE, mode);
        startActivity(intent);
        
    }
}