package com.ewareza.shapegame.resources;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.ewareza.android.R;
import com.ewareza.shapegame.shape.generator.ColorGenerator;

public enum ImageResources implements Resources {
    INSTANCE;

    private Drawable gameBackground;
    private Context context;

    public static ImageResources getInstance() {
        return INSTANCE;
    }

    @Override
    public void init(Context context) {
        this.context = context;
        gameBackground = context.getResources().getDrawable(R.drawable.game_background_clouds);
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
}
