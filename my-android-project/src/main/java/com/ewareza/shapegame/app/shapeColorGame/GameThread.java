package com.ewareza.shapegame.app.shapeColorGame;

import android.graphics.Point;
import com.ewareza.shapegame.app.GameEngine;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameThread extends Thread {
    private CountDownLatch gameStartCountDownLatch;
    private CyclicBarrier gameOverCyclicBarrier;
    private GameEngine gameEngine;
    private AtomicBoolean gameStarting = new AtomicBoolean(false);
    private AtomicBoolean screenTouched = new AtomicBoolean(false);
    private Point currentTouchedPoint;
    private AtomicBoolean isRunning = new AtomicBoolean(true);

    public GameThread(CountDownLatch gameStartCountDownLatch, CyclicBarrier gameOverCyclicBarrier) {
        this.gameStartCountDownLatch = gameStartCountDownLatch;
        this.gameOverCyclicBarrier = gameOverCyclicBarrier;
        gameEngine = ShapeColorGame.getEngine();
    }

    public boolean isRunning() {
        return isRunning.get();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning.set(isRunning);
    }

    @Override
    public void run() {
        while(isRunning.get()) {
            if(gameStarting.get()) {
                startNewGame();
                gameStartCountDownLatch.countDown();
            }
            else if(screenTouched.get()){
                screenTouched.set(false);
                gameEngine.onScreenTouched(currentTouchedPoint);

                if (allShapesFound()) {
                    ShapeColorGame.setGameOver(true);
                    gameEngine.playWonGame();
                    tryToAwaitWithTimeoutOnBarrier(gameOverCyclicBarrier, 3, TimeUnit.SECONDS);
                    ShapeColorGame.setGameOver(false);
                    gameEngine.generateNewGame();
                }
            }
        }
    }

    private boolean allShapesFound() {
        return gameEngine.getNumberOfLookedForShapesOnScreen() == 0;
    }

    private void tryToAwaitWithTimeoutOnBarrier(CyclicBarrier cyclicBarrier, int timeout, TimeUnit timeUnit) {
        try {
            cyclicBarrier.await(timeout, timeUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            //@TODO
        } catch (TimeoutException e) {
            //@TODO
        }
        finally {
            gameOverCyclicBarrier.reset();
        }
    }

    private void startNewGame() {
        ShapeColorGame.setToFirstGame();
        gameEngine.generateNewGame();
        gameEngine.playStartNewGame();
        gameStarting.set(false);
    }

    public synchronized void onScreenTouched(Point point) {
        screenTouched.set(true);
        currentTouchedPoint = point;
    }

    public void onStart() {
        gameStarting.set(true);
    }
}
