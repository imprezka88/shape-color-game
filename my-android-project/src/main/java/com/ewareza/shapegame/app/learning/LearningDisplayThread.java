package com.ewareza.shapegame.app.learning;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import com.ewareza.shapegame.app.DisplayThread;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;

public class LearningDisplayThread extends DisplayThread {
    public static final int TIME_BETWEEN_FRAMES = 100;
    private LearningScreen learningScreen;

    public LearningDisplayThread(SurfaceHolder holder) {
        super(holder);
    }

    @Override
    protected void updatePhysics() {
        learningScreen = LearningGame.getLearningScreen();
        if (LearningGame.getShouldUpdateScreen())
            learningScreen.update();
    }

    @Override
    protected void drawUpdatedView(Canvas canvas) {
        clearScreen(canvas);
//        drawFrog(canvas);
        drawShape(canvas);
    }

    private void drawShape(Canvas canvas) {
        learningScreen.drawShapes(canvas);
    }

    private void drawFrog(Canvas canvas) {
        learningScreen.drawFrog(canvas);
    }

    private void clearScreen(Canvas canvas) {
        canvas.drawRect(0, 0, DimenRes.getScreenWidth(), DimenRes.getScreenHeight(), GameUtils.getGameBackgroundPaint());
    }

    @Override
    protected void tryToSleep() {
        try {
            Thread.sleep(TIME_BETWEEN_FRAMES);
        } catch (InterruptedException e) {
            //@TODO
        }
    }
}
