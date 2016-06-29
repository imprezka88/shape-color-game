package com.ewareza.shapegame.resources;

import android.content.Context;
import com.ewareza.android.R;

public enum DimenRes implements Resources {
    INSTANCE;

    private static int screenHeightInPixels;
    private static int screenWidthInPixels;
    private static int gameTitleHeight;
    private static int gameOverFontSize;

    public static int getGameOverFontSize() {
        return gameOverFontSize;
    }

    public static int getScreenHeight() {
        return screenHeightInPixels;
    }

    public static int getScreenWidth() {
        return screenWidthInPixels;
    }

    public static int getGameTitleHeight() {
        return gameTitleHeight;
    }

    public static DimenRes getInstance() {
        return INSTANCE;
    }

    @Override
    public void init(Context context) {
        gameTitleHeight = context.getResources().getDimensionPixelSize(R.dimen.gameTitleHeight);
        gameOverFontSize = context.getResources().getDimensionPixelSize(R.dimen.gameOverFontSize);
        screenWidthInPixels = context.getResources().getDisplayMetrics().widthPixels;
        screenHeightInPixels = context.getResources().getDisplayMetrics().heightPixels;
    }
}
