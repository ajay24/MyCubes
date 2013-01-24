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
	private int i;
	private int j;
	private int player=-1;
	private GameView gv;

	private int state;

	public Point(int x, int y, int i, int j) {

		this.x = x;
		this.y = y;
		this.i=i;
		this.j=j;
	}

	public boolean getSelected()
	{
		return selected;
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
	public boolean getDown(){
		return Down;
	}
	public boolean getRight(){
		return Right;
	}
	public int geti(){
		return i;
	}
	public int getj(){
		return j;
	}

    public boolean setPlayer(boolean player){
		if(this.player==-1){
			if(player)
		this.player=1;
		else this.player=0;
		return true;
		}
		return false;
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
		paint.setStrokeWidth(20);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(30);
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
		if (player==0){
			canvas.drawText("a",x+squarex/2,y+squarey/2,paint);
		}
		if(player==1){
			canvas.drawText("b",x+squarex/2,y+squarey/2,paint);
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
