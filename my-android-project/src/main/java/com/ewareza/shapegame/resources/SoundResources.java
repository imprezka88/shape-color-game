package com.ewareza.shapegame.resources;

import android.content.Context;
import android.media.MediaPlayer;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.learning.LearningGame;
import com.ewareza.shapegame.domain.generator.ColorFactory;
import com.ewareza.shapegame.domain.shape.AbstractShape;

//@TODO cache media players retrieved dynamically, one play method with sound enability check
public enum SoundResources implements Resources {
    INSTANCE;

    private static MediaPlayer wonGameSound;
    private static MediaPlayer correctShapeFoundSound;
    private static MediaPlayer wrongShapeFoundSound;
    private static MediaPlayer startNewGameSound;
    private static Context context;
    private static MediaPlayer learningShapeVoice;
    private static MediaPlayer mainMenuSound;
    private static MediaPlayer startLearningPhaseOneSound;
    private static MediaPlayer startLearningPhaseTwoSound;
    private static boolean stopPlayingLearningSounds = false;
    private static MediaPlayer shapeGameTitleSound;
    private static MediaPlayer colorGameTitleSound;

    public static SoundResources getInstance() {
        return INSTANCE;
    }

    public static void play() {

    }

    public static void playLearningShapePhaseOneDescriptionSound(AbstractShape shape) {
        if (Game.getSpeakingEnabled()) {
            if (!stopPlayingLearningSounds) {
                int identifier = getLearningShapeDescriptionIdentifier(shape);

                if (identifier != 0) {
                    learningShapeVoice = MediaPlayer.create(context, identifier);
                    learningShapeVoice.start();
                    ImageResources.getTalkingFrogAnimation().start();

                    learningShapeVoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            try {
                                ImageResources.getTalkingFrogAnimation().stop();
                                Thread.sleep(2000);
                                LearningGame.learnNextShape();
                            } catch (InterruptedException e) {
                                //@TODO
                            }
                        }
                    });
                }
            }
        }
    }

    public static void playLearningShapePhaseTwoOnClickSound(AbstractShape shape) {
        if (Game.getSpeakingEnabled()) {
            int identifier = getLearningShapeDescriptionIdentifier(shape);

            if (identifier != 0) {
                learningShapeVoice = MediaPlayer.create(context, identifier);
                learningShapeVoice.start();
            }
        }
    }

    private static int getLearningShapeDescriptionIdentifier(AbstractShape shape) {
        String fileName = String.format("its_%s", shape.getName());
        return context.getResources().getIdentifier(fileName, "raw", "com.ewareza.android");
    }

    private static MediaPlayer resetSoundIfIsPlaying(MediaPlayer mediaPlayer, int resourceId) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(context, resourceId);

        return mediaPlayer;
    }

    private static void stopPlayingSoundIfPlaying(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    public static void playMainMenuSound() {
        if (Game.getSoundsEnabled()) {
            mainMenuSound = resetSoundIfIsPlaying(mainMenuSound, R.raw.main_menu1);
            mainMenuSound = MediaPlayer.create(context, R.raw.main_menu1);
            mainMenuSound.setVolume((float) 1, (float) 1);
            mainMenuSound.setLooping(true);
            mainMenuSound.start();
        }
    }

    public static void stopPlayingMainMenuSoundIfPlaying() {
        stopPlayingSoundIfPlaying(mainMenuSound);
    }

    public static void turnDownMainScreenSound() {
        mainMenuSound.setVolume((float) 0.1, (float) 0.1);
    }

    public static void turnUpMainScreenSound() {
        mainMenuSound.setVolume((float) 1, (float) 1);
    }

    public static void playStartLearningPhaseOneSound() {
        if (Game.getSpeakingEnabled()) {
            stopPlayingLearningSounds = false;
            startLearningPhaseOneSound = resetSound(startLearningPhaseOneSound, R.raw.learning_phase1_on_start);
            startLearningPhaseOneSound.start();
            ImageResources.getTalkingFrogAnimation().start();

            startLearningPhaseOneSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ImageResources.getTalkingFrogAnimation().stop();
                    LearningGame.startPresentingShapes();
                }
            });
        }
    }

    private static MediaPlayer resetSound(MediaPlayer mediaPlayer, int identifier) {
        mediaPlayer.reset();
        return MediaPlayer.create(context, identifier);
    }

    public static void playStartLearningPhaseTwoSound() {
        if (Game.getSpeakingEnabled()) {
            startLearningPhaseTwoSound.start();
        }
    }

    public static void stopPlayingAllLearningSounds() {
        stopPlayingLearningSounds = true;
        stopPlayingSoundIfPlaying(startLearningPhaseOneSound);
        stopPlayingSoundIfPlaying(startLearningPhaseTwoSound);
        stopPlayingSoundIfPlaying(learningShapeVoice);
    }

    public static void resumeMainMenuSound() {
        if (!mainMenuSound.isPlaying() && Game.getSoundsEnabled())
            mainMenuSound.start();
    }

    public static void pauseMainMenuSoundIfPlaying() {
        if (mainMenuSound.isPlaying())
            mainMenuSound.pause();
    }

    public static void stopPlayingShapeGameSounds() {
        stopPlayingSoundIfPlaying(colorGameTitleSound);
        stopPlayingSoundIfPlaying(shapeGameTitleSound);
        stopPlayingSoundIfPlaying(wonGameSound);
    }

    @Override
    public void init(Context context) {
        SoundResources.context = context;
        wonGameSound = MediaPlayer.create(context, R.raw.won_game);
        correctShapeFoundSound = MediaPlayer.create(context, R.raw.correct_shape_clicked);
        wrongShapeFoundSound = MediaPlayer.create(context, R.raw.wrong_shape_clicked);
        startNewGameSound = MediaPlayer.create(context, R.raw.start_game);
        mainMenuSound = MediaPlayer.create(context, R.raw.main_menu1);
        startLearningPhaseOneSound = MediaPlayer.create(context, R.raw.learning_phase1_on_start);
        startLearningPhaseTwoSound = MediaPlayer.create(context, R.raw.learning_phase2_on_start);
    }

    public void playWonGame() {
        if (Game.getSoundsEnabled())
            wonGameSound.start();
    }

    public void playCorrectShapeClickedSound() {
        if (Game.getSoundsEnabled()) {
            correctShapeFoundSound = resetSoundIfIsPlaying(correctShapeFoundSound, R.raw.correct_shape_clicked);
            correctShapeFoundSound.start();
        }
    }

    public void playWrongShapeClickedSound() {
        if (Game.getSoundsEnabled()) {
            wrongShapeFoundSound = resetSoundIfIsPlaying(wrongShapeFoundSound, R.raw.wrong_shape_clicked);
            wrongShapeFoundSound.start();
        }
    }

    //@TODO cache sounds
    public void playShapeGameTitleSound(AbstractShape currentLookedForShape) {
        if (Game.getSpeakingEnabled()) {
            wonGameSound = resetSoundIfIsPlaying(wonGameSound, R.raw.won_game);
            String fileName = String.format("find_%s", currentLookedForShape.getName());
            int identifier = context.getResources().getIdentifier(fileName, "raw", "com.ewareza.android");
            shapeGameTitleSound = MediaPlayer.create(context, identifier);
            shapeGameTitleSound.start();
        }
    }

    public void playStartNewGame() {
        if (Game.getSoundsEnabled())
            startNewGameSound.start();
    }

    public void playColorGameTitleSound(int color) {
        if (Game.getSpeakingEnabled()) {
            String fileName = String.format("find_%s", ColorFactory.ColorWithIndex.asString(color));
            int identifier = context.getResources().getIdentifier(fileName, "raw", "com.ewareza.android");
            colorGameTitleSound = MediaPlayer.create(context, identifier);
            colorGameTitleSound.start();
        }
    }

    enum Speech {
        START_LEARNING_PHASE_TWO(R.raw.learning_phase2_on_start);

        Speech(int soundIdentifier) {

        }
    }

    enum Sounds {
        WON_GAME(R.raw.won_game);

        Sounds(int soundIdentifier) {

        }
    }
}
