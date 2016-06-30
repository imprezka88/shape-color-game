package com.ewareza.shapegame.app.learningGame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import com.ewareza.shapegame.app.DisplayThread;
import com.ewareza.shapegame.app.GameUtils;
import com.ewareza.shapegame.app.singleGame.generator.RandomShapesGenerator;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.shape.generator.ShapeGenerator;
import com.ewareza.shapegame.shape.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LearningThread extends DisplayThread {
    private List<Shape> learningShapes = new ArrayList<>();
    private AtomicInteger currentShapeNumber = new AtomicInteger(0);

    public LearningThread(SurfaceHolder holder) {
        super(holder);
        initLearningShapes();
    }

    private void initLearningShapes() {
        for (ShapeGenerator shapeGenerator : RandomShapesGenerator.getShapeGenerators()) {
            learningShapes.add(shapeGenerator.generateLearningShape());
        }
    }

    @Override
    protected void updatePhysics() {
        learningShapes.get(currentShapeNumber.get()).growAndFallDown();
    }

    @Override
    protected void drawUpdatedView(Canvas canvas) {
        clearScreen(canvas);
        drawFrog(canvas);
        drawShape(canvas);
    }

    private void drawShape(Canvas canvas) {
        learningShapes.get(currentShapeNumber.get()).draw(canvas, GameUtils.getFilledPaint());
    }

    private void drawFrog(Canvas canvas) {
        Drawable learningFrog = ImageResources.getLearningFrog();
        learningFrog.setBounds(0, 0, DimenRes.getScreenWidth(), DimenRes.getScreenHeight());
        learningFrog.draw(canvas);
    }

    private void clearScreen(Canvas canvas) {
        canvas.drawRect(0, 0, DimenRes.getScreenWidth(), DimenRes.getScreenHeight(), GameUtils.getGameBackgroundPaint());
    }

    @Override
    protected void tryToSleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}
