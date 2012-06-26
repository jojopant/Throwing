package com.mic.throwing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.mic.throwing.sprite.Sprite;

public class MainView extends SurfaceView implements Callback {
    int           screenWidth;
    int           keyCode     = 0;
    String        keyAction   = "";
    Heart         heart       = null;
    SurfaceHolder holder      = null;
    int           centerX;
    int           windowWidth;
    int           windowHeight;
    Sprite        window1     = null;
    Sprite        window2     = null;
    Sprite        window3     = null;
    Sprite        plate       = null;
    Paint         backPaint   = null;
    Paint         forePaint   = null;
    int           i           = 0;
    public int    downb_seq[] = { 9, 10, 11, 12 };
    
    public MainView(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);
        holder = this.getHolder();
        Point size = calculateCenterX(context);
        windowWidth = size.x / 4;
        windowHeight = size.y / 3;
        Bitmap imageTemp = BitmapFactory.decodeResource(getResources(), R.drawable.window);
        Bitmap image = Bitmap.createScaledBitmap(imageTemp, windowWidth, windowHeight, true);
        window2 = new Sprite(image, windowWidth, windowHeight);
        window2.setPosition(centerX - windowWidth / 2, 20);
        window1 = new Sprite(image, windowWidth, windowHeight);
        window1.setPosition(centerX - windowWidth / 2 - (windowWidth + 30), 20 + windowHeight / 2);
        window3 = new Sprite(image, windowWidth, windowHeight);
        window3.setPosition(centerX - windowWidth / 2 + (windowWidth + 30), 20 + windowHeight / 2);
        imageTemp = BitmapFactory.decodeResource(getResources(), R.drawable.plate);
        image = Bitmap.createScaledBitmap(imageTemp, windowWidth / 3, windowHeight / 3, true);
        plate = new Sprite(image, windowWidth / 3, windowHeight / 3);
        plate.setPosition(centerX - windowWidth / 6, size.y - windowHeight / 3 - 20);
        backPaint = new Paint();
        backPaint.setColor(Color.BLACK);
        forePaint = new Paint();
        forePaint.setColor(Color.BLUE);
    }
    
    public void start() {
        if (heart == null) {
            heart = new Heart(this);
            ((MainActivity) getContext()).registerHeartBeatListrener(heart);
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
        window2.paint(canvas, forePaint);
        window3.paint(canvas, forePaint);
        plate.paint(canvas, forePaint);
        // plate.move(x, y);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
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
    
    public Point calculateCenterX(Context context) {
        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        centerX = size.x / 2;
        return size;
    }
}
