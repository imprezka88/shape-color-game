package com.ewareza.shapegame.app.utils;

import android.graphics.Color;
import android.graphics.Paint;
import com.ewareza.shapegame.resources.DimenRes;

import java.util.Random;

public class GameUtils {
    private static Random random = new Random();

    public static Paint getGameBackgroundPaint() {
        return gameBackgroundPaint;
    }

    private static Paint gameBackgroundPaint = initBackgroundPaint();
    private static Paint gameOverBackGroundPaint = initGameOverBackgroundPaint();
    private static Paint gameOverTextPaint = initGameOverTextPaint();
    private static Paint gameTitleLinePaint = initLinePaint();
    private static Paint filledPaint = initFilledPaint();

    private GameUtils() {
    }

    private static Paint initFilledPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);

        return paint;
    }

    private static Paint initBackgroundPaint() {
        gameBackgroundPaint = new Paint();
        gameBackgroundPaint.setColor(Color.BLACK);

        return gameBackgroundPaint;
    }

    public static void StopThread(Thread displayThread) {
        boolean retry = true;
        while (retry) {
            try {
                displayThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public static int getRandomInt(int from, int to) {
        return random.nextInt(1 + to - from) + from;
    }

    public static int getDefaultShapeColor() {
        return Color.BLUE;
    }

    public static Paint getFilledPaint() {
        return filledPaint;
    }

    public static Paint initGameOverBackgroundPaint() {
        gameOverBackGroundPaint = new Paint();
        gameOverBackGroundPaint.setColor(Color.DKGRAY);

        return gameOverBackGroundPaint;
    }

    public static Paint initLinePaint() {
        gameTitleLinePaint = new Paint();
        gameTitleLinePaint.setColor(Color.WHITE);
        gameTitleLinePaint.setStrokeWidth(5);

        return gameTitleLinePaint;
    }

    public static Paint initGameOverTextPaint() {
        gameOverTextPaint = new Paint();
        gameOverTextPaint.setColor(Color.MAGENTA);
        gameOverTextPaint.setTextAlign(Paint.Align.CENTER);
        gameOverTextPaint.setTextSize(DimenRes.getInstance().getGameOverFontSize());

        return gameOverTextPaint;
    }

    public static Paint getGameOverBackGroundPaint() {
        return gameOverBackGroundPaint;
    }

    public static Paint getGameOverTextPaint() {
        return gameOverTextPaint;
    }

    public static Paint getGameTitleLinePaint() {
        return gameTitleLinePaint;
    }
}
