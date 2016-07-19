package com.ewareza.shapegame.resources;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.ewareza.android.R;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.factory.ColorFactory;
import com.ewareza.shapegame.domain.shape.Shape;

import java.util.HashMap;
import java.util.Map;

public enum ImageResources implements Resources {
    INSTANCE;

    private static Map<Class<? extends Shape>, AnimationDrawable> shapeToAnimationMap = new HashMap<>();

    private static AnimationDrawable talkingFrogAnimation;
    private static int colorGameImageIdentifier;
    private static int shapeGameImageIdentifier;
    private static int learningImageButtonIdentifier;
    private static Drawable shapeGameImage;
    private static Drawable colorGameImage;
    private static Drawable balloons;
    private static Drawable startGameButtonBorder;
    private static Context context;
    private static Drawable balloons2;
    private static Drawable flowers;
    private static Drawable learningBackgroundImage;
    private static Drawable flowers2;
    private static Drawable sun;
    private static Drawable butterflies;
    private static Drawable butterflies2;
    private static Drawable bird;
    private static Drawable hearts;
    private static Drawable balloons3;
    private static Drawable balloons4;
    private static Drawable balloonToy;
    private static Drawable hearts2;
    private static Drawable bird2;
    private static Drawable bird3;
    private static Drawable airplane;
    private static Drawable birthdayCake;
    private static ImageView learningFrogView;
    private Drawable gameBackground;

    public static ImageView getLearningFrogView() {
        return learningFrogView;
    }

    public static void setLearningFrogView(ImageView learningFrogView) {
        ImageResources.learningFrogView = learningFrogView;
        talkingFrogAnimation = (AnimationDrawable) learningFrogView.getBackground();
    }

    public static Drawable getBalloons3() {
        return balloons3;
    }

    public static Drawable getBalloons4() {
        return balloons4;
    }

    public static Drawable getBalloonToy() {
        return balloonToy;
    }

    public static Drawable getHearts2() {
        return hearts2;
    }

    public static Drawable getBird2() {
        return bird2;
    }

    public static Drawable getBird3() {
        return bird3;
    }

    public static Drawable getAirplane() {
        return airplane;
    }

    public static Drawable getBirthdayCake() {
        return birthdayCake;
    }

    public static Drawable getFlowers2() {
        return flowers2;
    }

    public static Drawable getSun() {
        return sun;
    }

    public static Drawable getButterflies() {
        return butterflies;
    }

    public static Drawable getButterflies2() {
        return butterflies2;
    }

    public static Drawable getBird() {
        return bird;
    }

    public static Drawable getHearts() {
        return hearts;
    }

    public static Drawable getLearningBackgroundImage() {
        return learningBackgroundImage;
    }

    public static Drawable getBalloons() {
        return balloons;
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

    public static Drawable getBalloons2() {
        return balloons2;
    }

    public static Drawable getFlowers() {
        return flowers;
    }

    public static Drawable getResource(String shapeName, ColorFactory.Color color) {
        String fileName = String.format("%s_%s", shapeName, color.getColorName());
        int identifier = context.getResources().getIdentifier(fileName, GameUtils.RESOURCE_TYPE_DRAWABLE, GameUtils.RESOURCE_PACKAGE);

        if (identifier != 0)
            return context.getResources().getDrawable(identifier);

        throw new IllegalArgumentException(String.format("Identifier for drawable: %s not found", fileName));
    }

    public static boolean hasResource(String shapeClassName, ColorFactory.Color color) {
        String fileName = String.format("%s_%s", shapeClassName, color.getColorName());
        int identifier = context.getResources().getIdentifier(fileName, GameUtils.RESOURCE_TYPE_DRAWABLE, GameUtils.RESOURCE_PACKAGE);

        return identifier != 0;
    }

    public static Drawable getStartGameButtonBorder() {
        return startGameButtonBorder;
    }

    public static void setTalkingShapeAnimation(Class<? extends Shape> shapeClass, AnimationDrawable animationDrawable) {
        shapeToAnimationMap.put(shapeClass, animationDrawable);
    }

    public static AnimationDrawable getTalkingShapeAnimation(Class<? extends Shape> shapeClass) {
        return shapeToAnimationMap.get(shapeClass);
    }

    @Override
    public void init(Context context) {
        ImageResources.context = context;
        gameBackground = context.getResources().getDrawable(R.drawable.game_background_clouds);
        colorGameImageIdentifier = R.drawable.color_game_button_imgage;

        shapeGameImageIdentifier = R.drawable.shape_game_button_image;
        learningImageButtonIdentifier = R.drawable.learning_button_image;

        shapeGameImage = context.getResources().getDrawable(shapeGameImageIdentifier);
        colorGameImage = context.getResources().getDrawable(colorGameImageIdentifier);
        initGameOverImages(context);
        learningBackgroundImage = context.getResources().getDrawable(R.drawable.learning_background);
        startGameButtonBorder = context.getResources().getDrawable(R.drawable.new_game_border);
    }

    private void initGameOverImages(Context context) {
        balloons = context.getResources().getDrawable(R.drawable.game_over_balloons);
        balloons2 = context.getResources().getDrawable(R.drawable.game_over_balloons2);
        balloons3 = context.getResources().getDrawable(R.drawable.game_over_balloons3);
        balloons4 = context.getResources().getDrawable(R.drawable.game_over_balloons4);
        balloonToy = context.getResources().getDrawable(R.drawable.game_over_balloon_toy);

        flowers = context.getResources().getDrawable(R.drawable.game_over_flowers);
        flowers2 = context.getResources().getDrawable(R.drawable.game_over_flowers2);

        sun = context.getResources().getDrawable(R.drawable.game_over_sun);

        butterflies = context.getResources().getDrawable(R.drawable.game_over_butterflies);
        butterflies2 = context.getResources().getDrawable(R.drawable.game_over_butterflies2);

        bird = context.getResources().getDrawable(R.drawable.game_over_bird);
        bird2 = context.getResources().getDrawable(R.drawable.game_over_bird2);
        bird3 = context.getResources().getDrawable(R.drawable.game_over_bird3);

        airplane = context.getResources().getDrawable(R.drawable.game_over_airplane);

        hearts = context.getResources().getDrawable(R.drawable.game_over_hearts);
        hearts2 = context.getResources().getDrawable(R.drawable.game_over_hearts2);

        birthdayCake = context.getResources().getDrawable(R.drawable.game_over_birthday_cake);
    }

    public Drawable getGameBackground() {
        return gameBackground;
    }
}
