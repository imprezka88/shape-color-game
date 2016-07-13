package com.ewareza.shapegame.app.shapeColorGame;

import android.graphics.drawable.Drawable;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.ImageResources;

import java.util.ArrayList;
import java.util.List;

public class GameOverImageFactory {
    private static List<Drawable> gameOverImages = new ArrayList<>();

    static {
        gameOverImages.add(ImageResources.getBaloons());
    }

    public static Drawable getGameOverImage() {
        return GameUtils.getRandomElement(gameOverImages);
    }
}
