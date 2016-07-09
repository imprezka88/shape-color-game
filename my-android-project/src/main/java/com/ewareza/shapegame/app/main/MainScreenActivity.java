package com.ewareza.shapegame.app.main;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.learning.LearningGameActivity;
import com.ewareza.shapegame.app.shapeColorGame.ShapeGameActivity;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.SoundResources;

public class MainScreenActivity extends Activity {
    TextToSpeech tts;

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundResources.pauseMainMenuSoundIfPlaying();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundResources.stopPlayingMainMenuSoundIfPlaying();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SoundResources.playMainMenuSound();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        ImageButton learningModeButton = (ImageButton) findViewById(R.id.newLearningGameButton);
        learningModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, LearningGameActivity.class);
                startActivity(intent);
            }
        });

        ImageButton shapesGameButton = (ImageButton) findViewById(R.id.newShapesGameButton);
        shapesGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, ShapeGameActivity.class);
                Bundle b = new Bundle();
                b.putString(GameUtils.GAME_TYPE, GameUtils.SHAPE);
                intent.putExtras(b);
                startActivity(intent);
                startActivity(intent);
            }
        });

        ImageButton colorGameButton = (ImageButton) findViewById(R.id.newColorGameButton);
        colorGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, ShapeGameActivity.class);
                Bundle b = new Bundle();
                b.putString(GameUtils.GAME_TYPE, GameUtils.COLOR);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        addOnClickListenersToButtons();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void addOnClickListenersToButtons() {
        addOnClickListenerToSoundsButton();
        addOnClickListenerToSpeakingButton();
    }

    private void addOnClickListenerToSpeakingButton() {
        final ImageButton speakingOnOffButton = (ImageButton) findViewById(R.id.speaking_on_off);

        speakingOnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Game.getSpeakingEnabled()) {
                    Game.setSpeakingEnabled(false);
                    speakingOnOffButton.setImageResource(R.drawable.speaking_off);
                } else {
                    Game.setSpeakingEnabled(true);
                    speakingOnOffButton.setImageResource(R.drawable.speaking_on);

                }
            }
        });
    }

    private void addOnClickListenerToSoundsButton() {
        final ImageButton soundsOnOffButton = (ImageButton) findViewById(R.id.sounds_on_off);

        soundsOnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Game.getSoundsEnabled()) {
                    Game.setSoundsEnabled(false);
                    SoundResources.pauseMainMenuSoundIfPlaying();
                    soundsOnOffButton.setImageResource(R.drawable.sounds_off);
                } else {
                    Game.setSoundsEnabled(true);
                    SoundResources.resumeMainMenuSound();
                    soundsOnOffButton.setImageResource(R.drawable.sounds_on);
                }
            }
        });
    }
}