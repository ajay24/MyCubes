package com.ajay.testgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

public class MyButton extends Button {
	 private Paint paint = new Paint();
	private int width;
	private int height;
	private String text;

	public MyButton(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawColor(Color.BLACK);		
		paint.setARGB(100, 255, 0, 0);	
		paint.setAntiAlias(true);
		width=getWidth();
		height=getHeight();
		text=(String) getText();
		
		canvas.drawCircle(width/2, height/2, height/2, paint);
		
		
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(this.getTextColors().getDefaultColor());
		paint.setTextSize(this.getTextSize());
		
		canvas.drawText(text, width/2, height/2, paint );
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case (MotionEvent.ACTION_DOWN):
			     this.setScaleX(2);
				this.setScaleY(2);
			break;
		case (MotionEvent.ACTION_UP):
				this.setScaleX(1);
				this.setScaleY(1);
			break;

		default:
			break;
		}
		super.onTouchEvent(event);
		// TODO Auto-generated method stub
		return true;
	}
	
}
