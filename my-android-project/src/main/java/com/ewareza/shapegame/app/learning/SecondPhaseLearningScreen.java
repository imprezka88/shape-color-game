package com.ewareza.shapegame.app.learning;

import android.graphics.Canvas;
import android.graphics.Point;
import com.ewareza.shapegame.domain.shape.AbstractShape;

import java.util.ArrayList;
import java.util.List;

public class SecondPhaseLearningScreen implements LearningScreen {
    private List<AbstractShape> learningShapesEachOnce = new ArrayList<>();

    public SecondPhaseLearningScreen() {
        initLearningShapes();
    }

    private void initLearningShapes() {
//        learningShapesEachOnce = LearningShapesGenerator.generateShapesForSecondLearningPhase();
    }

    @Override
    public void drawShapes(Canvas canvas) {
        /*for (AbstractShape shape : learningShapesEachOnce) {
            shape.draw(canvas);
        }*/
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
        /*for (AbstractShape shape : learningShapesEachOnce) {
            if (shape.contains(point))
                SoundResources.playLearningShapePhaseTwoOnClickSound(shape);
        }*/
    }

    @Override
    public void clearScreen(Canvas canvas) {

    }
}
