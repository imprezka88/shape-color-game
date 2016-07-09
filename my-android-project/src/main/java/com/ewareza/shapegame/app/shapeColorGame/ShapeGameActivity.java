package com.ewareza.shapegame.app.shapeColorGame;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.resources.SoundResources;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class ShapeGameActivity extends Activity {
    private final static Logger Log = Logger.getLogger(ShapeGameActivity.class.getName());
    private GameView gameView;
    private CyclicBarrier gameOverCyclicBarrier = new CyclicBarrier(3);
    private CountDownLatch gameStartCountDownLatch = new CountDownLatch(1);
    private GameThread gameThread;
    private AnimationDrawable frogAnimation;
    private Game shapeColorGame;

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
        gameStartCountDownLatch = new CountDownLatch(1);
        Bundle b = getIntent().getExtras();
        String gameType = "";
        if (b != null)
            gameType = b.getString(GameUtils.GAME_TYPE);

        shapeColorGame = new ShapeColorGame(gameType);

        gameThread = new GameThread(gameStartCountDownLatch, gameOverCyclicBarrier);
        gameThread.start();
        gameView = new GameView(this, gameStartCountDownLatch, gameOverCyclicBarrier);

        FrameLayout frameLayout = new FrameLayout(this);

        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        ImageView frogView = new ImageView(this);
        frogView.setVisibility(View.VISIBLE);
        frogView.setBackgroundResource(R.drawable.game_over_animation);
        frogAnimation = (AnimationDrawable) frogView.getBackground();
        ImageResources.setTalkingFrogAnimation(frogAnimation);

        frogView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));

        frameLayout.addView(gameView);
//        frameLayout.addView(frogView);

        setContentView(frameLayout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundResources.stopPlayingMainMenuSoundIfPlaying();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (x < DimenRes.getScreenWidth() && y < DimenRes.getScreenHeight()) {
                if (ShapeColorGame.isGameOver())
                    tryToAwaitOnBarrier();
                else
                    gameThread.onScreenTouched(new Point(x, y));
            }
        }

        return true;
    }

    private void tryToAwaitOnBarrier() {
        try {
            gameOverCyclicBarrier.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            //@TODO
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            gameOverCyclicBarrier.reset();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundResources.stopPlayingShapeGameSounds();
        gameView.stopDisplayThread();
        gameThread.setIsRunning(false);
        GameUtils.StopThread(gameThread);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SoundResources.turnDownMainScreenSound();
        SoundResources.resumeMainMenuSound();
        shapeColorGame.setToInitialState();
        gameThread.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}

