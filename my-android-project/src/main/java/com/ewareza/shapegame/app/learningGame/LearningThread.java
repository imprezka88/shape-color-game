package com.ewareza.shapegame.app.learningGame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import com.ewareza.shapegame.app.DisplayThread;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.generator.RandomShapesGenerator;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.generator.ShapeGenerator;
import com.ewareza.shapegame.domain.shape.Shape;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;
import com.sun.java.accessibility.util.GUIInitializedListener;

import java.util.ArrayList;
import java.util.List;

public class LearningThread extends DisplayThread {
    private List<Shape> learningShapes = new ArrayList<>();

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
        learningShapes.get(Game.getCurrentLearningShapeNumber().get()).growAndFallDown();
    }

    @Override
    protected void drawUpdatedView(Canvas canvas) {
        clearScreen(canvas);
        drawFrog(canvas);
        drawShape(canvas);
    }

    private void drawShape(Canvas canvas) {
        if(Game.getShowAllLearningShapes().get()) {
            for (ShapeGenerator shapeGenerator : RandomShapesGenerator.getShapeGenerators()) {
                shapeGenerator.generateLearningShape().draw(canvas, GameUtils.getFilledPaint());
            }
        }
        else {
            learningShapes.get(Game.getCurrentLearningShapeNumber().get()).draw(canvas, GameUtils.getFilledPaint());
        }
    }

    private void drawFrog(Canvas canvas) {
        Drawable learningFrog = ImageResources.getLearningFrog();
        if(Game.getShowAllLearningShapes().get()) {
            learningFrog.setBounds(0, DimenRes.getScreenHeight()/2, DimenRes.getScreenWidth()/2, DimenRes.getScreenHeight());
        }
        else {
            learningFrog.setBounds(0, 0, DimenRes.getScreenWidth(), DimenRes.getScreenHeight());
        }
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
