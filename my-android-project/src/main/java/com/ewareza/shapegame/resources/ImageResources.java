package com.ewareza.shapegame.resources;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import com.ewareza.android.R;
import com.ewareza.shapegame.domain.generator.ColorFactory;

public enum ImageResources implements Resources {
    INSTANCE;

    private static Drawable gameOverBackground;
    private static AnimationDrawable learningFrog;
    private static AnimationDrawable talkingFrogAnimation;
    private static int colorGameImageIdentifier;
    private static int shapeGameImageIdentifier;
    private static int learningImageButtonIdentifier;
    private static Drawable shapeGameImage;
    private static Drawable colorGameImage;
    private static Drawable baloons;
    private Drawable gameBackground;
    private Context context;

    public static Drawable getBaloons() {
        return baloons;
    }

    public static Drawable getColorGameImage() {
        return colorGameImage;
    }

    public static Drawable getShapeGameImage() {
        return shapeGameImage;
    }

    public static int getShapeGameImageIdentifier() {
        return shapeGameImageIdentifier;
    }

    public static int getLearningImageButtonIdentifier() {
        return learningImageButtonIdentifier;
    }

    public static void setLearningImageButtonIdentifier(int learningImageButtonIdentifier) {
        ImageResources.learningImageButtonIdentifier = learningImageButtonIdentifier;
    }

    public static int getColorGameImageIdentifier() {
        return colorGameImageIdentifier;
    }

    public static ImageResources getInstance() {
        return INSTANCE;
    }

    public static AnimationDrawable getTalkingFrogAnimation() {
        return talkingFrogAnimation;
    }

    public static void setTalkingFrogAnimation(AnimationDrawable talkingFrogAnimation) {
        ImageResources.talkingFrogAnimation = talkingFrogAnimation;
    }

    public static AnimationDrawable getLearningFrog() {
        return learningFrog;
    }

    @Override
    public void init(Context context) {
        this.context = context;
        gameBackground = context.getResources().getDrawable(R.drawable.game_background_clouds);
        gameOverBackground = context.getResources().getDrawable(R.drawable.game_over_animation);
        learningFrog = (AnimationDrawable) context.getResources().getDrawable(R.drawable.game_over_animation);
        colorGameImageIdentifier = R.drawable.color_game_button_imgage;
        shapeGameImageIdentifier = R.drawable.shape_game_button_image;
        learningImageButtonIdentifier = R.drawable.learning_button_image;

        shapeGameImage = context.getResources().getDrawable(shapeGameImageIdentifier);
        colorGameImage = context.getResources().getDrawable(colorGameImageIdentifier);
        baloons = context.getResources().getDrawable(R.drawable.balloons);
    }

    public Drawable getGameBackground() {
        return gameBackground;
    }

    public Drawable getResource(String shapeClassName, int color) {
        String fileName = String.format("%s_%s", shapeClassName, ColorFactory.ColorWithIndex.asString(color));
        int identifier = context.getResources().getIdentifier(fileName, "drawable", "com.ewareza.android");

        if (identifier != 0)
            return context.getResources().getDrawable(identifier);

        throw new IllegalArgumentException(String.format("Identifier for drawable: %s not found", fileName));
    }
}
