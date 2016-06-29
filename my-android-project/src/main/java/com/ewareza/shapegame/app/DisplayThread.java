package com.ewareza.shapegame.app;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.resources.TextResources;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class DisplayThread extends Thread {
    private final static Logger Log = Logger.getLogger(DisplayThread.class.getName());
    private final int GAME_OVER_DELAY = 3000;

    private SurfaceHolder surfaceHolder;
    private CyclicBarrier gameOverCyclicBarrier;

    private boolean isRunning = true;
    private boolean gameOver = false;

    public DisplayThread(SurfaceHolder surfaceHolder, CyclicBarrier gameOverCyclicBarrier) {
        this.surfaceHolder = surfaceHolder;
        this.gameOverCyclicBarrier = gameOverCyclicBarrier;
    }

    @Override
    public void run() {
        while (isRunning) {
            updatePhysics();

            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);

                if (canvas != null) {
                    synchronized (surfaceHolder) {
                        drawUpdatedView(canvas);
                    }
                }
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }

            tryToSleep();
        }
    }

    private void updatePhysics() {
        Game.updatePhysics();
    }

    private void drawUpdatedView(Canvas canvas) {
        clearScreen(canvas);
        drawGameTitle(canvas);
        drawShapes(canvas);

        if (gameOver) {
            drawGameOver(canvas);
        }
    }

    private void drawGameOver(Canvas canvas) {
        //@TODO refactor
        int screenHeight = DimenRes.getScreenHeight();
        int screenWidth = DimenRes.getScreenWidth();

        drawGameOverBackground(canvas, screenHeight, screenWidth);
        drawGameOverText(canvas, screenHeight, screenWidth);
    }

    private void drawGameOverText(Canvas canvas, int screenHeight, int screenWidth) {
        canvas.drawText(TextResources.getGameOverText(), screenWidth / 2,
                screenHeight / 2, GameUtils.getGameOverTextPaint());
    }

    private void drawGameOverBackground(Canvas canvas, int screenHeight, int screenWidth) {
        canvas.drawRect(0, screenHeight / 2 - 100, screenWidth,
                screenHeight / 2 + 100, GameUtils.getGameOverBackGroundPaint());
    }

    private void drawShapes(Canvas canvas) {
        Game.getEngine().draw(canvas);
    }

    private void drawGameTitle(Canvas canvas) {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        Game.drawGameTitleShape(canvas);
        canvas.drawLine(0, gameTitleHeight, screenWidth, gameTitleHeight + 1, GameUtils.getGameTitleLinePaint());
    }

    private void clearScreen(Canvas canvas) {
        Drawable drawable = ImageResources.getInstance().getGameBackground();
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
    }

    private void tryToSleep() {
        try {
            if (Game.isGameOver()) {
                Log.warning(String.format("Thread: %s, calling await on barrier", Thread.currentThread().getName()));
//                Log.warning(String.format("Barrier is broken: %s", gameOverCyclicBarrier.isBroken(), Thread.currentThread().getName()));
                gameOverCyclicBarrier.await(5, TimeUnit.SECONDS);
                Log.warning(String.format("After await Barrier is broken: %s", gameOverCyclicBarrier.isBroken(), Thread.currentThread().getName()));
                Game.setGameOver(false);
            } else
                Thread.sleep(Game.getGameSpeedForCurrentGame());
        } catch (InterruptedException ex) {
            //TODO: Log
        } catch (BrokenBarrierException e) {
            gameOverCyclicBarrier.reset();
            Log.warning("Broken Barrier Exception" + e);
        } catch (TimeoutException e) {
            gameOverCyclicBarrier.reset();
            Game.setGameOver(false);
            Log.warning("Timeout Exception" + e);

        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean state) {
        isRunning = state;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
