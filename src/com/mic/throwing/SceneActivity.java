package com.mic.throwing;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class SceneActivity extends Activity {
    int scene = Constants.SCENE_UNKNOWN;
    int mode  = Constants.MODE_UNKNOWN;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = getIntent().getIntExtra(Constants.MODE, Constants.MODE_UNKNOWN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.scene);
    }
    
    public void random(View view) {
        scene = Constants.SCENE_RANDOM;
        startMainActivity();
    }
    
    public void school(View view) {
        scene = Constants.SCENE_SCHOOL;
        startMainActivity();
    }
    
    public void office(View view) {
        scene = Constants.SCENE_OFFICE;
        startMainActivity();
    }
    
    public void train(View view) {
        scene = Constants.SCENE_TRAIN;
        startMainActivity();
    }
    
    public void back(View view) {
        this.finish();
        startActivity(new Intent(this, LevelActivity.class));
    }
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.MODE, mode);
        intent.putExtra(Constants.SCENE, scene);
        startActivity(intent);
        this.finish();
    }
}