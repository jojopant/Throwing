package com.mic.throwing.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Sprite {

	private int length = 0;
	private int currentPosition = 0;
	private int[][] framesXY = null;
	protected int frameWidth = 0;
	protected int frameHeight = 0;
	private Bitmap bigFrames = null;
	private int cols = 0;
	private int rows = 0;
	protected int PositionX = 0;
	protected int PositionY = 0;

	private int[] frameSequence = null;
	private int sequencePosition = 0;

	public Sprite() {
		// TODO Auto-generated constructor stub
	}

	public Sprite(Bitmap image, int frameWidth, int frameHeight) {
		this.bigFrames = image;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;

		framesXY = initSprite(image, frameWidth, frameHeight);
		this.length = framesXY.length;
	}

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

	public int getFrame() {
		return this.currentPosition;
	}

	public int getFrameSequenLength() {
		return framesXY.length;
	}

	public void nextFrame() {
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

	public void setFrame(int sequenceIndex) {
		if (sequenceIndex >= 0 && sequenceIndex < length) {
			this.currentPosition = sequenceIndex;
		}
	}

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

	public void setFrameSequence(int[] sequence) {

		if (this.frameSequence != null) {
			currentPosition = sequence[0];
			sequencePosition = 0;
		} else {
			this.frameSequence = sequence;
		}
	}

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