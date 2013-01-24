package com.ajay.testgame;

import java.util.concurrent.ArrayBlockingQueue;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLogic extends Thread {

	private SurfaceHolder surfaceHolder;
	private GameView mGameView;
	private int game_state;
	public static final int PAUSE = 0;
	public static final int READY = 1;
	public static final int RUNNING = 2;
	private ArrayBlockingQueue<InputObject> inputQueue = new ArrayBlockingQueue<InputObject>(
			20);
	private Object inputQueueMutex = new Object();

	public GameLogic(SurfaceHolder surfaceHolder, GameView gameView) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.mGameView = gameView;
		Log.d("hi","hulk");
	}

	public void setGameState(int game_state) {
		this.game_state = game_state;

	}

	public int getGameState() {
		return game_state;

	}

	@Override
	public void run() {
		Canvas canvas;
		
		while (game_state == RUNNING) {
			canvas = null;
			try {

				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					
					mGameView.update();
					processInput();
					mGameView.onDraw(canvas);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
		// TODO Auto-generated method stub

	}

	public void feedInput(InputObject input) {
		synchronized (inputQueueMutex) {
			try {
				inputQueue.put(input);
			} catch (InterruptedException e) {
			}
		}
	}

	private void processInput() {
		synchronized (inputQueueMutex) {
			ArrayBlockingQueue<InputObject> inputQueue = this.inputQueue;
			while (!inputQueue.isEmpty()) {
				try {
					InputObject input = inputQueue.take();
					if (input.eventType == InputObject.EVENT_TYPE_KEY) {
						mGameView.processKeyEvent(input);
					} else if (input.eventType == InputObject.EVENT_TYPE_TOUCH) {
						mGameView.processMotionEvent(input);
					}
					input.returnToPool();
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
