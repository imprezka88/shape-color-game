package com.ewareza.shapegame.app.learning;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.SoundResources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class LearningGame {
    private static final FirstPhaseLearningScreen firstPhaseScreen = new FirstPhaseLearningScreen();
    private static final SecondPhaseLearningScreen secondPhaseScreen = new SecondPhaseLearningScreen();
    private static AtomicBoolean shouldUpdateScreen = new AtomicBoolean(false);
    private static LearningScreen learningScreen;
    private static LearningDisplayThread firstPhaseDisplayThread;
    private static List<ImageView> learningShapes = new ArrayList<>();
    private static LearningGameActivity learningActivity;

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
        firstPhaseDisplayThread.setRunning(false);
        GameUtils.StopThread(firstPhaseDisplayThread);

        learningActivity.finish();
        Intent intent = learningActivity.getIntent();
        Bundle bundle = new Bundle();
        bundle.putString(GameUtils.GAME_TYPE, "secondPhase");

        intent.putExtras(bundle);

        learningActivity.startActivity(intent);

        for (ImageView learningShape : learningShapes) {
            learningShape.setVisibility(View.VISIBLE);
            learningShape.invalidate();
        }


        /*firstPhaseDisplayThread.setRunning(false);
        GameUtils.StopThread(firstPhaseDisplayThread);*/
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

    public static LearningDisplayThread getFirstPhaseDisplayThread() {
        return firstPhaseDisplayThread;
    }

    public static void setFirstPhaseDisplayThread(LearningDisplayThread firstPhaseDisplayThread) {
        LearningGame.firstPhaseDisplayThread = firstPhaseDisplayThread;
    }

    public static LearningGameActivity getLearningActivity() {
        return learningActivity;
    }

    public static void setLearningActivity(LearningGameActivity learningActivity) {
        LearningGame.learningActivity = learningActivity;
    }

    public static void addTalkingShapeImageView(ImageView shape) {
        learningShapes.add(shape);
    }

    public abstract int getStartGameImageIdentifier();

    public abstract String getStartGameName();
}
