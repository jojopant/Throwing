package com.mic.throwing;

import android.view.SurfaceView;

public class Heart extends Thread {
    public boolean      beating = true;
    private SurfaceView body;
    
    public Heart(SurfaceView view) {
        body = view;
    }
    
    @Override
    public void run() {
        super.run();
        while (beating) {
            input();
            logic();
            doDraw();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
    public void input() {
        // hero.nextFrame();
    }
    
    public void logic() {
        
    }
    
    public void doDraw() {
        ((MainView) body).doDraw();
    }
}