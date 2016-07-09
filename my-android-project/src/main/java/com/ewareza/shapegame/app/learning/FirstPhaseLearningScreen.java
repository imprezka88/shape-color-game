package com.ewareza.shapegame.app.learning;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import com.ewareza.shapegame.app.GameSettings;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.generator.ShapeFactory;
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
        learningShapes.get(currentLearningShapeNumber.get()).draw(canvas, GameUtils.getFilledPaint());
    }

    @Override
    public void drawFrog(Canvas canvas) {
        AnimationDrawable learningFrog = ImageResources.getLearningFrog();
        learningFrog.setBounds(GameUtils.LEARNING_FROG_BIG_LEFT, GameUtils.LEARNING_FROG_BIG_TOP, GameUtils.LEARNING_FROG_BIG_RIGHT, GameUtils.LEARNING_FROG_BIG_BOTTOM);
        learningFrog.draw(canvas);

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

}
