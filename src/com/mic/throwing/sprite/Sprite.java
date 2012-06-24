package com.mic.throwing.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Sprite {
    
    private int     length           = 0;    // 帧序列长度
    private int     currentPosition  = 0;    // 当前帧序列下标
    private int[][] framesXY         = null; // 各个帧序列在图片的坐标
    protected int   frameWidth       = 0;    // 帧的宽度
    protected int   frameHeight      = 0;    // 帧的高度
    private Bitmap  bigFrames        = null; // 大的图片
    private int     cols             = 0;
    private int     rows             = 0;
    protected int   PositionX        = 0;    // 精灵的X坐标
    protected int   PositionY        = 0;    // 精灵的Y坐标
                                             
    private int[]   frameSequence    = null; // 加入的帧序列
    private int     sequencePosition = 0;    // 加入的帧序列的显示下标
     public Sprite() {
        // TODO Auto-generated constructor stub
    }
    public Sprite(Bitmap image, int frameWidth, int frameHeight) {
        // 初始化
        this.bigFrames = image;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        
        framesXY = initSprite(image, frameWidth, frameHeight);
        this.length = framesXY.length;
    }
    
    // 初始化精灵
    private int[][] initSprite(Bitmap bitmap, int frameWidth, int frameHeight) {
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        this.cols = Math.round(imageWidth / frameWidth);// 列数
        this.rows = Math.round(imageHeight / frameHeight);// 行数
        
        int[][] tempXYs = new int[cols * rows][2];
        for (int i = 0, k = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++, k++) {
                tempXYs[k][0] = j * frameWidth;
                tempXYs[k][1] = i * frameHeight;
            }
        }
        
        return tempXYs;
    }
    
    // 获得当前帧的下标
    public int getFrame() {
        return this.currentPosition;
    }
    
    // 获得帧序列的长度
    public int getFrameSequenLength() {
        return framesXY.length;
    }
    
    // 获得当前帧序列的下一个帧
    public void nextFrame() {
        // 如果自定义帧序列为空，则使用默认的
        if (frameSequence == null) {
            currentPosition++;
            if (currentPosition > length - 1) {
                currentPosition = 0;
            }
        } else {
            sequencePosition++;
            if (sequencePosition > frameSequence.length - 1) {
                sequencePosition = 0;
            }
            currentPosition = frameSequence[sequencePosition];
        }
    }
    
    // 获得当前帧序列的上一个帧
    public void prevFrame() {
        if (this.frameSequence == null) {
            currentPosition--;
            if (currentPosition < 0) {
                currentPosition = length - 1;
            }
        } else {
            sequencePosition--;
            if (sequencePosition < 0) {
                sequencePosition = frameSequence.length - 1;
            }
            currentPosition = frameSequence[sequencePosition];
        }
        
    }
    
    // 设置当前帧
    public void setFrame(int sequenceIndex) {
        if (sequenceIndex >= 0 && sequenceIndex < length) {
            this.currentPosition = sequenceIndex;
        }
    }
    
    // 设置位置
    public void setPosition(int positionX, int positionY) {
        this.PositionX = positionX;
        this.PositionY = positionY;
    }
    
    public int getPositionX() {
        return PositionX;
    }
    
    public int getPositionY() {
        return PositionY;
    }
    
    // 设置帧序列
    public void setFrameSequence(int[] sequence) {
        
        if (this.frameSequence != null) {
            currentPosition = sequence[0];
            sequencePosition = 0;
        } else {
            this.frameSequence = sequence;
        }
    }
    
    // 移动
    public void move(int x, int y) {
        PositionX += x;
        PositionY += y;
    }
    
    public void paint(Canvas canvas, Paint paint) {
        Log.d("ddd", "" + this.PositionX);
        Log.d("ddd", "" + this.PositionY);
        Bitmap bitmap = Bitmap.createBitmap(this.bigFrames,
            this.framesXY[currentPosition][0],
            this.framesXY[currentPosition][1], this.frameWidth,
            this.frameHeight);
        canvas.drawBitmap(bitmap, PositionX, PositionY, paint);
    }
}