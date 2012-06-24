package com.mic.throwing.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.WindowManager;

public class Window extends Sprite {
    int middleX;
    
    public enum Position {
        left, center, right
    }
    
    private Window(Bitmap image, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        // TODO Auto-generated constructor stub
    }
    
    public Window(Bitmap image, Context context, Position pos) {
        
        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
            .getDefaultDisplay().getSize(size);
        middleX = size.x / 2;
        frameWidth = size.x / 4;
        frameHeight = size.y / 4;
        
        switch (pos) {
            case center: {
                PositionX = middleX - frameWidth / 2;
                PositionY = 20;
                break;
                
            }
            case left: {
                PositionX = middleX - frameWidth / 2 - (frameWidth + 30);
                PositionY = 20 + frameHeight / 2;
                break;
            }
            case right: {
                PositionX = middleX - frameWidth / 2 + (frameWidth + 30);
                PositionY = 20 + frameHeight / 2;
                break;
            }
        }
        new Sprite(image, 20, 20);
    }
    
}
