package com.ewareza.shapegame.app.learning;

import android.graphics.Point;
import com.ewareza.shapegame.resources.SoundResources;

import java.util.concurrent.atomic.AtomicBoolean;

public class LearningGame {
    private static final FirstPhaseLearningScreen firstPhaseScreen = new FirstPhaseLearningScreen();
    private static final SecondPhaseLearningScreen secondPhaseScreen = new SecondPhaseLearningScreen();
    private static AtomicBoolean shouldUpdateScreen = new AtomicBoolean(false);
    private static LearningScreen learningScreen;

    public static void setToInitialState() {
        shouldUpdateScreen.set(false);
        firstPhaseScreen.setToInitialState();
        secondPhaseScreen.setToInitialState();
        learningScreen = firstPhaseScreen;
    }

    public static void learnNextShape() {
        FirstPhaseLearningScreen.learnNextShape();
    }

    public static void onPhaseOneFinished() {
        learningScreen = secondPhaseScreen;
        SoundResources.playStartLearningPhaseTwoSound();
    }

    public static boolean getShouldUpdateScreen() {
        return shouldUpdateScreen.get();
    }

    public static void onScreenTouched(Point point) {
        getLearningScreen().onScreenTouched(point);
    }

    public static void startPresentingShapes() {
        shouldUpdateScreen.set(true);
        SoundResources.playLearningShapePhaseOneDescriptionSound(FirstPhaseLearningScreen.getCurrentLearningShape());
    }

    public static LearningScreen getLearningScreen() {
        return learningScreen;
    }
}
