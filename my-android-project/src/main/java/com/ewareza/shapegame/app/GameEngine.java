package com.ewareza.shapegame.app;

import android.graphics.Canvas;
import android.graphics.Point;
import com.ewareza.shapegame.app.singleGame.SingleGame;
import com.ewareza.shapegame.app.singleGame.generator.SingleGameFactory;
import com.ewareza.shapegame.resources.SoundResources;

public class GameEngine {
    private final Object lock = new Object();
    private final SoundResources soundResources = SoundResources.INSTANCE;
    private SingleGame currentSingleGame;

    public void generateNewGame() {
        Game.incrementGameNumber();
        currentSingleGame = SingleGameFactory.createNewSingleGame();
    }

    public void update() {
        synchronized (lock) {
            currentSingleGame.update();
        }
    }

    public void draw(Canvas canvas) {
        synchronized (lock) {
            currentSingleGame.draw(canvas);
        }
    }

    public void onScreenTouched(Point point) {
        synchronized (lock) {
            currentSingleGame.onScreenTouched(point);
        }
    }

    public int getNumberOfLookedForShapesOnScreen() {
        return currentSingleGame.getNumberOfLookedForShapesOnScreen();
    }

    public void playWonGame() {
        soundResources.playWonGame();
    }

    public void playStartNewGame() {
        soundResources.playStartNewGame();
    }

    public void drawGameTitleShape(Canvas canvas) {
        currentSingleGame.getGameTitleShape().draw(canvas, GameUtils.getFilledPaint());
    }
}
