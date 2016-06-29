package com.ewareza.shapegame.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.GameEngine;
import com.ewareza.shapegame.view.GameView;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class ShapeGameActivity extends Activity {
    private final static Logger Log = Logger.getLogger(ShapeGameActivity.class.getName());
    private GameView gameView;
    private GameEngine gameEngine;
    private CyclicBarrier gameOverCyclicBarrier = new CyclicBarrier(2);

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle intersects the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this, gameOverCyclicBarrier);
        setContentView(gameView);

        gameEngine = Game.getEngine();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (Game.isGameOver()) {
                tryToAwaitOnBarrier();
            } else {
                int x = (int) event.getX();
                int y = (int) event.getY();

                gameEngine.onScreenTouched(new Point(x, y));

                if (allShapesFound()) {
                    gameEngine.playWonGame();
                    Game.setGameOver(true);
//                newGame();
                }
            }
        }

        return true;
    }

    private void tryToAwaitOnBarrier() {
        try {
            Log.warning(String.format("Thread: %s, calling await on barrier", Thread.currentThread().getName()));
//            Log.warning(String.format("Barrier is broken: %s", gameOverCyclicBarrier.isBroken(), Thread.currentThread().getName()));

            gameOverCyclicBarrier.await(5, TimeUnit.SECONDS);
            newGame();
            Game.setGameOver(false);
            Log.warning(String.format("New game After await Barrier is broken: %s", gameOverCyclicBarrier.isBroken(), Thread.currentThread().getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            Log.warning("Broken Barrier Exception" + e);
        } catch (TimeoutException e) {
            gameOverCyclicBarrier.reset();
            Game.setGameOver(false);
            newGame();
            Log.warning("New game from Timeout Exception" + e);
        }
    }

    private boolean allShapesFound() {
        return gameEngine.getNumberOfLookedForShapesOnScreen() == 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Game.setToFirstGame();
        newGame();
        gameEngine.playStartNewGame();
    }

    private void newGame() {
        gameEngine.generateNewGame();
    }
}

