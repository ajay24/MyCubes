package com.ajay.testgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameStastics {
	public void draw(Canvas canvas, int score_a,int score_b) {
		Paint paint=new Paint();
		paint.setColor(Color.WHITE);
		canvas.drawText(String.valueOf(score_a), 10, 700, paint);	
		canvas.drawText(String.valueOf(score_b), 100, 700, paint);
		//canvas.drawText(String.valueOf(spritecharacter.getY()), 10, 600, paint);
		//canvas.drawText(String.valueOf(spritecharacter.getY()), 10, 600, paint);
	}
}
