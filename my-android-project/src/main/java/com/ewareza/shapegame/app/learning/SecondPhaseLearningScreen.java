package com.ewareza.shapegame.app.learning;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.resources.SoundResources;

import java.util.ArrayList;
import java.util.List;

public class SecondPhaseLearningScreen implements LearningScreen {
    private List<AbstractShape> learningShapesEachOnce = new ArrayList<>();

    public SecondPhaseLearningScreen() {
        initLearningShapes();
    }

    private void initLearningShapes() {
        learningShapesEachOnce = LearningShapesGenerator.generateShapesForSecondLearningPhase();
    }

    @Override
    public void drawShapes(Canvas canvas) {
        for (AbstractShape shape : learningShapesEachOnce) {
            shape.draw(canvas, GameUtils.getFilledPaint());
        }
    }

    @Override
    public void drawFrog(Canvas canvas) {
        AnimationDrawable learningFrog = ImageResources.getLearningFrog();
        learningFrog.setBounds(GameUtils.LEARNING_FROG_SMALL_LEFT, GameUtils.LEARNING_FROG_SMALL_TOP, GameUtils.LEARNING_FROG_SMALL_RIGHT, GameUtils.LEARNING_FROG_SMALL_BOTTOM);
        learningFrog.draw(canvas);
    }

    @Override
    public void update() {
        //@TODO moving from frog's hand
    }

    @Override
    public void setToInitialState() {
        initLearningShapes();
    }

    @Override
    public void onScreenTouched(Point point) {
        for (AbstractShape shape : learningShapesEachOnce) {
            if (shape.contains(point))
                SoundResources.playLearningShapePhaseTwoOnClickSound(shape);
        }

    }
}
