package com.mic.throwing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.mic.throwing.sprite.Sprite;

public class MainView extends SurfaceView implements Callback {
    int           screenWidth;
    int           keyCode     = 0;
    String        keyAction   = "";
    Heart         heart       = null;
    SurfaceHolder holder      = null;
    Sprite        window1     = null;
    // Window window2 = null;
    // Window window3 = null;
    Paint         backPaint   = null;
    Paint         forePaint   = null;
    int           i           = 0;
    
    public int    downb_seq[] = { 9, 10, 11, 12 };
    
    public MainView(Context context) {
        super(context);
        
        setFocusable(true);
        getHolder().addCallback(this);
        holder = this.getHolder();
        Bitmap imageTemp = BitmapFactory.decodeResource(getResources(),
                R.drawable.window);
        Bitmap image = Bitmap.createScaledBitmap(imageTemp, 200, 170, true);
        
        // window2 = new Window(image, context, Position.center);
        window1 = new Sprite(image, 100, 100);
        // window3 = new Window(image, context, Position.right);
        backPaint = new Paint();
        backPaint.setColor(Color.BLACK);
        
        forePaint = new Paint();
        forePaint.setColor(Color.BLUE);
        
    }
    
    public void start() {
        if (heart == null) {
            heart = new Heart(this);
            heart.start();
        }
    }
    
    public void stop() {
        heart.beating = false;
        if (heart != null) {
            try {
                heart.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void doDraw() {
        Canvas c = null;
        try {
            c = holder.lockCanvas();
            synchronized (holder) {
                paint(c);
            }
        } finally {
            if (c != null) {
                holder.unlockCanvasAndPost(c);
            }
        }
    }
    
    public void paint(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), backPaint);
        window1.paint(canvas, forePaint);
        window1.move(i++, i++);
        // window2.paint(canvas, forePaint);
        // window3.paint(canvas, forePaint);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        start();
        
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        stop();
        
    }
    
}
