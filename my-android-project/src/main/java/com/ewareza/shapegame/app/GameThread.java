package com.ewareza.shapegame.app;

import android.graphics.Point;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameThread extends Thread {
    private CountDownLatch gameStartCountDownLatch;
    private CyclicBarrier gameOverCyclicBarrier;
    private GameEngine gameEngine;
    private AtomicBoolean gameStarting = new AtomicBoolean(true);
    private AtomicBoolean screenTouched = new AtomicBoolean(false);
    private Point currentTouchedPoint;
    private AtomicBoolean isRunning = new AtomicBoolean(true);

    public boolean isRunning() {
        return isRunning.get();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning.set(isRunning);
    }

    public GameThread(CountDownLatch gameStartCountDownLatch, CyclicBarrier gameOverCyclicBarrier) {
        this.gameStartCountDownLatch = gameStartCountDownLatch;
        this.gameOverCyclicBarrier = gameOverCyclicBarrier;
        gameEngine = Game.getEngine();
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
                    gameEngine.playWonGame();
                    Game.setGameOver(true);
                    tryToAwaitWithTimeoutOnBarrier(gameOverCyclicBarrier, 3, TimeUnit.SECONDS);
                    Game.setGameOver(false);
                    newGame();
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
        } catch (TimeoutException e) {
        }
        finally {
            gameOverCyclicBarrier.reset();
        }
    }

    private void startNewGame() {
        Game.setToFirstGame();
        newGame();
        gameEngine.playStartNewGame();
        gameStarting.set(false);
    }

    private void newGame() {
        gameEngine.generateNewGame();
    }

    public synchronized void onScreenTouched(Point point) {
        screenTouched.set(true);
        currentTouchedPoint = point;
    }

    public void onStart() {
        gameStarting.set(true);
    }
}
