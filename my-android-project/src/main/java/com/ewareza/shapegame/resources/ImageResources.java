package com.ewareza.shapegame.resources;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import com.ewareza.android.R;
import com.ewareza.shapegame.domain.generator.ColorGenerator;

public enum ImageResources implements Resources {
    INSTANCE;

    private Drawable gameBackground;
    private Context context;
    private static Drawable gameOverBackground;
    private static Drawable learningFrog;
    private static AnimationDrawable gameOverAnimation;

    public static ImageResources getInstance() {
        return INSTANCE;
    }

    public static AnimationDrawable getGameOverAnimation() {
        return gameOverAnimation;
    }

    public static void setGameOverAnimation(AnimationDrawable gameOverAnimation) {
        ImageResources.gameOverAnimation = gameOverAnimation;
    }

    @Override
    public void init(Context context) {
        this.context = context;
        gameBackground = context.getResources().getDrawable(R.drawable.game_background_clouds);
        gameOverBackground = context.getResources().getDrawable(R.drawable.game_over_animation);
        learningFrog = context.getResources().getDrawable(R.drawable.frog_wave_up_mouth_closed);
    }

    public Drawable getGameBackground() {
        return gameBackground;
    }

    public Drawable getResource(String shapeClassName, int color) {
        String fileName = String.format("%s_%s", shapeClassName, ColorGenerator.ColorWithIndex.asString(color));
        int identifier = context.getResources().getIdentifier(fileName, "drawable", "com.ewareza.android");

        if (identifier != 0)
            return context.getResources().getDrawable(identifier);

        throw new IllegalArgumentException(String.format("Identifier for drawable: %s not found", fileName));
    }

    public static Drawable getGameOverBackground() {
        return gameOverBackground;
    }

    public static Drawable getLearningFrog() {
        return learningFrog;
    }

    public static void setLearningFrog(Drawable learningFrog) {
        ImageResources.learningFrog = learningFrog;
    }
}
