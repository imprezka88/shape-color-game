package com.ewareza.shapegame.app.learningGame;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.view.LearningView;

public class LearningGameActivity extends Activity {
    private LearningView learningView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        learningView = new LearningView(this);
        setContentView(learningView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
