package com.ewareza.shapegame.app.shapeColorGame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.learning.LearningGameActivity;
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
        shapeColorGame = ShapeColorGameFactory.getGame(getCurrentGameType());

        gameThread = new GameThread(gameStartCountDownLatch, gameOverCyclicBarrier);
        gameThread.start();

        setContentView(getView());
    }

    private FrameLayout getView() {
        FrameLayout frameLayout = new FrameLayout(this);

        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        gameView = new GameView(this, gameStartCountDownLatch, gameOverCyclicBarrier);

        frameLayout.addView(gameView);
//        frameLayout.addView(getLearningImageButton());
        frameLayout.addView(getNextGameImageButton());
        frameLayout.addView(getFrogView());
        return frameLayout;
    }

    private ImageView getFrogView() {
        ImageView frogView = new ImageView(this);
        frogView.setVisibility(View.VISIBLE);
        frogView.setBackgroundResource(R.drawable.game_over_animation);
        frogAnimation = (AnimationDrawable) frogView.getBackground();
        ImageResources.setTalkingFrogAnimation(frogAnimation);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, DimenRes.getGameTitleHeight());
        params.setMargins(0, 5, 5, 5);

        frogView.setLayoutParams(params);

        frogView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShapeGameActivity.this, LearningGameActivity.class);
                finish();
                startActivity(intent);
            }
        });

        return frogView;
    }

    private ImageButton getLearningImageButton() {
        ImageButton learningButton = new ImageButton(this);
        learningButton.setBackgroundColor(Color.WHITE);
        learningButton.setBackgroundResource(ImageResources.getLearningImageButtonIdentifier());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(200, DimenRes.getGameTitleHeight(), Gravity.LEFT);
        learningButton.setLayoutParams(params);
        learningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShapeGameActivity.this, LearningGameActivity.class);
                finish();
                startActivity(intent);
            }
        });
        return learningButton;
    }

    private ImageButton getNextGameImageButton() {
        ImageButton nextGameButton = new ImageButton(this);
        nextGameButton.setBackgroundColor(Color.WHITE);
        nextGameButton.setBackgroundResource(shapeColorGame.getNextGameImageIdentifier());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(200, DimenRes.getGameTitleHeight() - 20, Gravity.RIGHT);
        params.setMargins(0, 10, 10, 10);
        nextGameButton.setLayoutParams(params);
        nextGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle b = new Bundle();
                b.putString(GameUtils.GAME_TYPE, shapeColorGame.getNextGameName());
                intent.putExtras(b);
                finish();
                startActivity(intent);
            }
        });
        return nextGameButton;
    }

    private String getCurrentGameType() {
        Bundle b = getIntent().getExtras();
        String gameType = "";

        if (b != null)
            gameType = b.getString(GameUtils.GAME_TYPE);
        return gameType;
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
                if (ShapeColorGame.isGameOver()) {
                    tryToAwaitOnBarrier();
                }
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

