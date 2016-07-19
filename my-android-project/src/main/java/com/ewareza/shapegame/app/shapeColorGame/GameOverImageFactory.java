package com.ewareza.shapegame.app.shapeColorGame;

import android.graphics.drawable.Drawable;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.ImageResources;

import java.util.ArrayList;
import java.util.List;

public class GameOverImageFactory {
    private static List<Drawable> gameOverImages = new ArrayList<>();

    static {
        gameOverImages.add(ImageResources.getBalloons());
        gameOverImages.add(ImageResources.getBalloons2());
        gameOverImages.add(ImageResources.getBalloons3());
        gameOverImages.add(ImageResources.getBalloons4());
        gameOverImages.add(ImageResources.getBalloonToy());

        gameOverImages.add(ImageResources.getFlowers());
        gameOverImages.add(ImageResources.getFlowers2());

        gameOverImages.add(ImageResources.getButterflies());
        gameOverImages.add(ImageResources.getButterflies2());

        gameOverImages.add(ImageResources.getSun());
        gameOverImages.add(ImageResources.getHearts());
        gameOverImages.add(ImageResources.getHearts2());

        gameOverImages.add(ImageResources.getBird());
        gameOverImages.add(ImageResources.getBird2());
        gameOverImages.add(ImageResources.getBird3());

        gameOverImages.add(ImageResources.getAirplane());
        gameOverImages.add(ImageResources.getBirthdayCake());
    }

    public static Drawable getGameOverImage() {
        return GameUtils.getRandomElement(gameOverImages);
    }
}
