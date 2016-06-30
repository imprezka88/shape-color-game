package com.ewareza.shapegame.app.shapeColorGame;

import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import com.ewareza.shapegame.app.DisplayThread;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ShapeGameDisplayThread extends DisplayThread {
    private final static Logger Log = Logger.getLogger(ShapeGameDisplayThread.class.getName());
    private final int INITIAL_FROG_POSITION = 300;
    private AtomicInteger right = new AtomicInteger(INITIAL_FROG_POSITION);
    private AtomicInteger bottom = new AtomicInteger(INITIAL_FROG_POSITION);

    private CyclicBarrier gameOverCyclicBarrier;

    public ShapeGameDisplayThread(SurfaceHolder surfaceHolder, CyclicBarrier gameOverCyclicBarrier) {
        super(surfaceHolder);
        this.gameOverCyclicBarrier = gameOverCyclicBarrier;
    }

    @Override
    protected void updatePhysics() {
        Game.updatePhysics();
    }

    @Override
    protected void drawUpdatedView(Canvas canvas) {
        clearScreen(canvas);
        drawGameTitle(canvas);
        drawShapes(canvas);
        drawFrog(canvas);

        if (Game.isGameOver()) {
            AnimationDrawable drawable = ImageResources.getInstance().getGameOverAnimation();
            drawable.start();
            drawGameOver(canvas);
        }
    }

    private void drawFrog(Canvas canvas) {
        if(!Game.isGameOver()) {
            right.set(INITIAL_FROG_POSITION);
            bottom.set(INITIAL_FROG_POSITION);
        }
        AnimationDrawable drawable = ImageResources.getInstance().getGameOverAnimation();
        drawable.setBounds(0, 0, right.get(), bottom.get());
        drawable.draw(canvas);
    }

    private void drawGameOver(Canvas canvas) {
        if(bottom.get() + 10 < DimenRes.getScreenHeight()) {
            right.addAndGet(10);
            bottom.addAndGet(10);
        }

        /*Drawable drawable = ImageResources.getInstance().getLearningFrog();
        drawable.setBounds(0, 0, screenWidth, screenHeight);
        drawable.draw(canvas);
        /*ImageResources.getGameOverAnimation().start();
        //@TODO refactor
        int screenHeight = DimenRes.getScreenHeight();
        int screenWidth = DimenRes.getScreenWidth();

        drawGameOverImage(canvas, screenHeight, screenWidth);*/
    }

    private void drawGameOverImage(Canvas canvas, int screenHeight, int screenWidth) {
       /* canvas.drawRect(0, screenHeight / 2 - 100, screenWidth,
                screenHeight / 2 + 100, GameUtils.getGameOverBackGroundPaint());*/
        /*Drawable drawable = ImageResources.getInstance().getLearningFrog();
        drawable.setBounds(0, 0, screenWidth, screenHeight);
        drawable.draw(canvas);*/
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

    @Override
    protected void tryToSleep() {
        try {
            if (Game.isGameOver()) {
//                gameOverCyclicBarrier.await(3, TimeUnit.SECONDS);
            } else
                Thread.sleep(Game.getGameSpeedForCurrentGame());
        } catch (InterruptedException ex) {
            //TODO: Log
        } /*catch (BrokenBarrierException e) {
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            gameOverCyclicBarrier.reset();
            right.set(INITIAL_FROG_POSITION);
            bottom.set(INITIAL_FROG_POSITION);
        }*/
    }
}
