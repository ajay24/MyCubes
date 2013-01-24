package com.ajay.testgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameStastics {
	public void draw(Canvas canvas, Point spritecharacter, Point[][] points) {
		Paint paint=new Paint();
		paint.setColor(Color.WHITE);
		canvas.drawText(String.valueOf(spritecharacter.getY()), 10, 700, paint);	
		canvas.drawText(String.valueOf(points[0][0].getY()), 100, 700, paint);
		//canvas.drawText(String.valueOf(spritecharacter.getY()), 10, 600, paint);
		//canvas.drawText(String.valueOf(spritecharacter.getY()), 10, 600, paint);
	}
}
