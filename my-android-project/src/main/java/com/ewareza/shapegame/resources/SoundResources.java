package com.ewareza.shapegame.resources;

import android.content.Context;
import android.media.MediaPlayer;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.domain.shape.Shape;

public enum SoundResources implements Resources {
    INSTANCE;

    private static MediaPlayer wonGameSound;
    private static MediaPlayer correctShapeFoundSound;
    private static MediaPlayer wrongShapeFoundSound;
    private static MediaPlayer startNewGameSound;
    private static Context context;
    private MediaPlayer gameTitleSound;

    public static SoundResources getInstance() {
        return INSTANCE;
    }

    @Override
    public void init(Context context) {
        SoundResources.context = context;
        wonGameSound = MediaPlayer.create(context, R.raw.won_game);
        correctShapeFoundSound = MediaPlayer.create(context, R.raw.correct_shape_clicked);
        wrongShapeFoundSound = MediaPlayer.create(context, R.raw.wrong_shape_clicked);
        startNewGameSound = MediaPlayer.create(context, R.raw.start_game);
    }

    public void playWonGame() {
        if (Game.getSoundsEnabled())
            wonGameSound.start();
    }

    public void playCorrectShapeClickedSound() {
        if (Game.getSoundsEnabled()) {
            correctShapeFoundSound = resetSoundToIfIsPlaying(correctShapeFoundSound, R.raw.correct_shape_clicked);
            correctShapeFoundSound.start();
        }
    }

    public void playWrongShapeClickedSound() {
        if (Game.getSoundsEnabled()) {
            wrongShapeFoundSound = resetSoundToIfIsPlaying(wrongShapeFoundSound, R.raw.wrong_shape_clicked);
            wrongShapeFoundSound.start();
        }
    }

    public void playGameTitleSound(Shape currentLookedForShape) {
        if (Game.isSpeakingEnabled()) {
            wonGameSound = resetSoundToIfIsPlaying(wonGameSound, R.raw.won_game);
            gameTitleSound = MediaPlayer.create(context, currentLookedForShape.getGameTitleSoundId());
            gameTitleSound.start();

            gameTitleSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                    Game.learnNextShape();
                }
            });
        }
    }

    private MediaPlayer resetSoundToIfIsPlaying(MediaPlayer mediaPlayer, int resourceId) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(context, resourceId);
        }

        return mediaPlayer;
    }

    public void playStartNewGame() {
        if (Game.getSoundsEnabled())
            startNewGameSound.start();
    }
}
