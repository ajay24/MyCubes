package com.ajay.testgame;

import android.R.bool;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class Point {

	
	private boolean Down = false;
	private boolean Right = false;
	
	private boolean selected=false;
	private int radius = 10;
	private int id;
	private int x;
	private int y;
	private GameView gv;

	private int state;

	public Point(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}
	public boolean getDwon(){
		return Down;
	}
	public boolean getRight(){
		return Right;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	public void setDown(boolean Down) {
		this.Down = Down;
	}
	public void setRight(boolean Right) {
		this.Right = Right;
	}

	public void draw(Canvas canvas, int squarex, int squarey) {
		Paint paint = new Paint();
		paint.setARGB(100, 255, 0, 0);
		paint.setStrokeWidth(5);
		if(this.selected){
			canvas.drawCircle(x, y, 2*radius, paint);
		}
		else{
			canvas.drawCircle(x, y, radius, paint);
		}	
		if(this.Down){
			canvas.drawLine(x, y, x, y+squarey, paint);
		}
		if(this.Right){
			canvas.drawLine(x, y, x+squarex, y, paint);
		}

	}
	


	public void setselected(boolean boolval) {
		selected=boolval;
		
	}

	public boolean rangecheck(Point previousselected, int squarex, int squarey) {
		if(this.x-previousselected.getX()==squarex && y==previousselected.getY()){
			previousselected.setRight(true);
			return true;
		}
		else if(this.y-previousselected.getY()==squarey && x==previousselected.getX()){
			previousselected.setDown(true);
			return true;
			
		}else if(this.x-previousselected.getX()==-squarex && y==previousselected.getY()){
			this.Right=true;
			return true;
			
		}else if(this.y-previousselected.getY()==-squarey && x==previousselected.getX()){
			this.Down=true;
			return true;
		}
		return false;
	}

}
