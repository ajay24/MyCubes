package com.ajay.testgame;

import java.util.concurrent.ArrayBlockingQueue;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private int width;
	private int height;
	private int lengthx = 5;
	private int lengthy = 5;
	private int squarex;
	private int squarey;
	private Point points[][];
	private int cursor_x;
	private int cursor_y;
	private Point currentselected=null;
	private Point previousselected=null;

	private boolean selection_changed;
	// private Point spritecharacter;
	private GameLogic mGameLogic;
	private ArrayBlockingQueue<InputObject> inputObjectPool;
	private GameStastics gamestats;

	public GameView(Context context) {
		super(context);

		createInputObjectPool();

		mGameLogic = new GameLogic(getHolder(), this);
		gamestats = new GameStastics();
		getHolder().addCallback(this);

		// TODO Auto-generated constructor stub
	}

	private void createInputObjectPool() {
		inputObjectPool = new ArrayBlockingQueue<InputObject>(20);
		for (int i = 0; i < 20; i++) {
			inputObjectPool.add(new InputObject(inputObjectPool));
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		for (int i = 0; i < lengthx; i++) {
			for (int j = 0; j < lengthy; j++) {
				points[i][j].draw(canvas, squarex, squarey);

			}
		}

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		width = getWidth();
		height = getHeight();
		points = new Point[lengthx][lengthy];
		squarex = width / (lengthx);
		squarey = height / (lengthy);

		points = new Point[lengthx][lengthy];
		for (int i = 0; i < lengthx; i++) {
			for (int j = 0; j < lengthy; j++) {
				int pointx = squarex / 2 + i * squarex;
				int pointy = squarey / 2 + j * squarey;
				points[i][j] = new Point(pointx, pointy);
				

			}
		}

		mGameLogic.setGameState(GameLogic.RUNNING);
		mGameLogic.start();

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		mGameLogic.setGameState(GameLogic.PAUSE);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		try {
			int hist = event.getHistorySize();
			if (hist > 0) {
				for (int i = 0; i < hist; i++) {
					InputObject input = inputObjectPool.take();
					input.useEventHistory(event, i);
					mGameLogic.feedInput(input);
				}
			}
			InputObject input = inputObjectPool.take();
			input.useEvent(event);
			mGameLogic.feedInput(input);

		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
		}
		return true;

		// TODO Auto-generated method stub
		// sprite.setX((int) event.getX());
		// sprite.setY((int) event.getY());

	}

	public void processMotionEvent(InputObject input) {
		if (input.action == InputObject.ACTION_TOUCH_DOWN) {

		}
		if (input.action == InputObject.ACTION_TOUCH_UP) {
			selection_changed = true;
			cursor_x = input.x;
			cursor_y = input.y;

		}
	}

	public void processKeyEvent(InputObject input) {
	}

	public void update() {

		if (selection_changed == true) {

			if (currentselected != null) {
				previousselected = currentselected;
			}
			for (int i = 0; i < lengthx; i++) {
				for (int j = 0; j < lengthy; j++) {
					if (cursor_x - points[i][j].getX() < 20
							&& cursor_x - points[i][j].getX() > -20
							&& cursor_y - points[i][j].getY() < 20
							&& cursor_y - points[i][j].getY() > -20) {
						
						currentselected = points[i][j];

					}
				}
			}

			currentselected.setselected(true);
			if (previousselected != null) {
				if(currentselected.rangecheck(previousselected, squarex, squarey)){
					currentselected.setselected(false);
					currentselected=null;
					previousselected.setselected(false);
					previousselected=null;
				}
				else{
					previousselected.setselected(false);
					previousselected=null;
				}
			}
			
			selection_changed=false;

		}

	}

}
