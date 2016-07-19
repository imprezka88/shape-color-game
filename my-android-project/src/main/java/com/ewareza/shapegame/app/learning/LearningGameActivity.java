package com.ewareza.shapegame.app.learning;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.GameSettings;
import com.ewareza.shapegame.app.shapeColorGame.ShapeGameActivity;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.factory.CircleFactory;
import com.ewareza.shapegame.domain.factory.ColorFactory;
import com.ewareza.shapegame.domain.factory.ShapeFactory;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.resources.SoundResources;

import java.util.ArrayList;
import java.util.List;

public class LearningGameActivity extends Activity {
    private List<ImageView> shapes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_screen);
        LearningGame.setLearningActivity(this);
        initView();
    }

    private void initView() {
        if (isFirstPhase())
            initShapesView();
        else
            initShapeAnimations();

        initStartShapeGameButton();
        initStartColorGameButton();
        setFrogViewAnimation();
    }

    private boolean isFirstPhase() {
        Bundle extras = getIntent().getExtras();
        return extras.get(GameUtils.GAME_TYPE).equals(GameUtils.PHASE_ONE);
    }

    private void initShapeAnimations() {
        for (ShapeFactory shapeFactory : GameSettings.getShapeFactories()) {
//            addTalkingShapeAnimation(shapeFactory);
        }
    }

    private void addTalkingShapeAnimation(final ShapeFactory shapeFactory) {
        String shapeName = shapeFactory.getShapeName();
        int shapeId = getResources().getIdentifier(shapeName, GameUtils.RESOURCE_TYPE_ID, GameUtils.RESOURCE_PACKAGE);
        ImageView shape = (ImageView) findViewById(shapeId);
        ColorFactory.Color learningShapeColor = LearningShapesGenerator.getLearningShapeColor(shapeFactory);
        Drawable shapeImage = ImageResources.getResource(shapeName, learningShapeColor);

        if (hasLearningShapeAnimation(shapeName, learningShapeColor)) {
            shapeImage = createTalkingShapeAnimation(shapeName, learningShapeColor);
    }

        shape.setImageDrawable(shapeImage);

        shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundResources.playLearningShapePhaseTwoOnClickSound(shapeName, shapeFactory.getShapeClass());
            }
        });

        shapes.add(shape);
        shape.setVisibility(View.VISIBLE);
        LearningGame.addTalkingShapeImageView(shape);
    }

    private AnimationDrawable createTalkingShapeAnimation(String shapeName, ColorFactory.Color learningShapeColor) {
        AnimationDrawable talkingShapeAnimation = new AnimationDrawable();
        talkingShapeAnimation.setOneShot(false);
        talkingShapeAnimation.setVisible(true, true);
        talkingShapeAnimation.addFrame(ImageResources.getResource(getMouthOpenedFrameName(shapeName), learningShapeColor), GameUtils.LEARNING_SHAPE_ANIMATION_FRAME_DURATION);
        talkingShapeAnimation.addFrame(ImageResources.getResource(getMouthClosedFrameName(shapeName), learningShapeColor), GameUtils.LEARNING_SHAPE_ANIMATION_FRAME_DURATION);

        ImageResources.setTalkingShapeAnimation(CircleFactory.getInstance().getShapeClass(), talkingShapeAnimation);

        return talkingShapeAnimation;
    }

    private boolean hasLearningShapeAnimation(String shapeName, ColorFactory.Color learningShapeColor) {
        return ImageResources.hasResource(getMouthOpenedFrameName(shapeName), learningShapeColor) && ImageResources.hasResource(getMouthClosedFrameName(shapeName), learningShapeColor);
    }

    private String getMouthOpenedFrameName(String shapeName) {
        return String.format("%s_mouth_opened", shapeName);
    }

    private String getMouthClosedFrameName(String shapeName) {
        return String.format("%s_mouth_closed", shapeName);
    }

    private void initShapesView() {
        FrameLayout learningScreen = (FrameLayout) findViewById(R.id.learningScreen);
        learningScreen.addView(new LearningView(this), 0);
    }

    private void initStartShapeGameButton() {
        ImageButton startGameButton = (ImageButton) findViewById(R.id.startShapeGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearningGameActivity.this, ShapeGameActivity.class);
                Bundle b = new Bundle();
                b.putString(GameUtils.GAME_TYPE, GameUtils.SHAPE);
                intent.putExtras(b);
                finish();
                startActivity(intent);
            }
        });
    }

    private void initStartColorGameButton() {
        ImageButton startGameButton = (ImageButton) findViewById(R.id.startColorGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearningGameActivity.this, ShapeGameActivity.class);
                Bundle b = new Bundle();
                b.putString(GameUtils.GAME_TYPE, GameUtils.COLOR);
                intent.putExtras(b);
                finish();
                startActivity(intent);
            }
        });
    }

    private void setFrogViewAnimation() {
        ImageView frogView = (ImageView) findViewById(R.id.learningFrog);
        ImageResources.setTalkingFrogAnimation((AnimationDrawable) frogView.getBackground());
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

        if (isFirstPhase())
            SoundResources.playStartLearningPhaseOneSound();
        else
            SoundResources.playStartLearningPhaseTwoSound();
    }

/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       */
/* int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN)
            LearningGame.onScreenTouched(new Point(x, y));*//*


        return true;
    }
*/

    @Override
    protected void onStart() {
        super.onStart();
    }
}
