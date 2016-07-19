package com.ewareza.shapegame.app.learning;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import com.ewareza.shapegame.app.GameSettings;
import com.ewareza.shapegame.domain.factory.ShapeFactory;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.resources.SoundResources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstPhaseLearningScreen implements LearningScreen {
    static final List<ShapeFactory> shapeFactories = GameSettings.getShapeFactories();
    static AtomicInteger currentLearningShapeNumber = new AtomicInteger(0);
    private java.util.List<AbstractShape> learningShapes = new ArrayList<>();

    public FirstPhaseLearningScreen() {
        initLearningShapes();
    }

    static AbstractShape getCurrentLearningShape() {
        ShapeFactory shapeFactory = shapeFactories.get(currentLearningShapeNumber.get());
        return shapeFactory.getGameTitleShape();
    }

    public static void learnNextShape() {
        if (isFirstLearningPhase()) {
            currentLearningShapeNumber.incrementAndGet();
            SoundResources.playLearningShapePhaseOneDescriptionSound(getCurrentLearningShape());
        } else
            LearningGame.onPhaseOneFinished();
    }

    public static boolean isFirstLearningPhase() {
        return currentLearningShapeNumber.get() < shapeFactories.size() - 1;
    }

    private void initLearningShapes() {
        currentLearningShapeNumber.set(0);
        learningShapes = LearningShapesGenerator.generateShapesForFirstLearningPhase();
    }

    @Override
    public void drawShapes(Canvas canvas) {
        learningShapes.get(currentLearningShapeNumber.get()).draw(canvas);
    }

    @Override
    public void update() {
        learningShapes.get(currentLearningShapeNumber.get()).growAndFallDown();
    }

    @Override
    public void setToInitialState() {
        initLearningShapes();
    }

    @Override
    public void onScreenTouched(Point point) {
        //@TODO implement touch events
    }

    @Override
    public void clearScreen(Canvas canvas) {
        Drawable drawable = ImageResources.getInstance().getLearningBackgroundImage();
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
    }

}
