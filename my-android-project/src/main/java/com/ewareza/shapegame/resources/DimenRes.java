package com.ewareza.shapegame.resources;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.ewareza.android.R;

public enum DimenRes implements Resources {
    INSTANCE;

    private static int screenHeightInPixels;
    private static int screenWidthInPixels;
    private static int gameTitleHeight;

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

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        screenWidthInPixels = metrics.widthPixels;
        screenHeightInPixels = metrics.heightPixels;
    }
}
