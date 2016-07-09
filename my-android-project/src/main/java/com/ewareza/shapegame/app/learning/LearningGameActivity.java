package com.ewareza.shapegame.app.learning;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.resources.SoundResources;

public class LearningGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setBackgroundColor(Color.WHITE);

        LearningView learningView = new LearningView(this);
        learningView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ImageView frogView = new ImageView(this);
        frogView.setVisibility(View.VISIBLE);
        frogView.setBackgroundResource(R.drawable.game_over_animation);
        AnimationDrawable frogAnimation = (AnimationDrawable) frogView.getBackground();
        frogView.setLayoutParams(new ViewGroup.LayoutParams(GameUtils.LEARNING_FROG_SMALL_RIGHT, GameUtils.LEARNING_FROG_SMALL_BOTTOM));

        ImageResources.setTalkingFrogAnimation(frogAnimation);

        layout.addView(frogView);
        layout.addView(learningView);

        setContentView(layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundResources.stopPlayingAllLearningSounds();
        SoundResources.stopPlayingMainMenuSoundIfPlaying();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LearningGame.setToInitialState();
        SoundResources.turnDownMainScreenSound();
        SoundResources.resumeMainMenuSound();
        SoundResources.playStartLearningPhaseOneSound();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN)
            LearningGame.onScreenTouched(new Point(x, y));

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
