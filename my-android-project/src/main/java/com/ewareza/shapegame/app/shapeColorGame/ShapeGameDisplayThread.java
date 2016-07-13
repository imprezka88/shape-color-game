package com.ewareza.shapegame.app.shapeColorGame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import com.ewareza.shapegame.app.DisplayThread;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ShapeGameDisplayThread extends DisplayThread {
    private final static Logger Log = Logger.getLogger(ShapeGameDisplayThread.class.getName());
    private AtomicInteger left;
    private AtomicInteger top;
    private AtomicInteger right;
    private AtomicInteger bottom;

    public ShapeGameDisplayThread(SurfaceHolder surfaceHolder) {
        super(surfaceHolder);
        initGameOverImagePosition();
    }

    @Override
    protected void updatePhysics() {
        if (!ShapeColorGame.isGameOver())
            ShapeColorGame.updatePhysics();
    }

    @Override
    protected void drawUpdatedView(Canvas canvas) {
        clearScreen(canvas);
        drawGameTitle(canvas);
        drawShapes(canvas);

        if (ShapeColorGame.isGameOver()) {
            Drawable drawable = GameOverImageFactory.getGameOverImage();
            drawable.setBounds(left.addAndGet(10), top.addAndGet(-10), right.addAndGet(10), bottom.addAndGet(-10));
            drawable.draw(canvas);
        } else {
            initGameOverImagePosition();
        }
    }

    private void initGameOverImagePosition() {
        left = new AtomicInteger(0);
        top = new AtomicInteger(DimenRes.getScreenHeight() - 200);
        right = new AtomicInteger(200);
        bottom = new AtomicInteger(DimenRes.getScreenHeight());
    }

    private void drawShapes(Canvas canvas) {
        ShapeColorGame.getEngine().draw(canvas);
    }

    private void drawGameTitle(Canvas canvas) {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        ShapeColorGame.drawGameTitleShape(canvas);
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
            if (!ShapeColorGame.isGameOver())
                Thread.sleep(ShapeColorGame.getGameSpeedForCurrentGame());
        } catch (InterruptedException ex) {
            //TODO: Log
        }
    }
}
