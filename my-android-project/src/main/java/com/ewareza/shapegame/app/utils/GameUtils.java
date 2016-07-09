package com.ewareza.shapegame.app.utils;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.resources.DimenRes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameUtils {
    public static final int LEARNING_SHAPE_TOP = 40;
    public static final int LEARNING_SHAPE_LEFT = 0;

    public static final int LEARNING_FROG_SMALL_LEFT = 0;
    public static final int LEARNING_FROG_SMALL_RIGHT = 400;
    public static final int LEARNING_FROG_SMALL_BOTTOM = 400;
    public static final int LEARNING_FROG_SMALL_TOP = 0;

    public static final int LEARNING_FROG_BIG_LEFT = 0;
    public static final int LEARNING_FROG_BIG_TOP = 0;
    public static final int LEARNING_FROG_BIG_RIGHT = DimenRes.getScreenWidth() - 100;
    public static final int LEARNING_FROG_BIG_BOTTOM = DimenRes.getScreenHeight() - 100;
    public static final String GAME_TYPE = "gameType";
    public static final String SHAPE = "shape";
    public static final String COLOR = "color";
    private static final Random random = new Random();

    private static Paint gameBackgroundPaint = initBackgroundPaint();

    private static Paint gameOverBackGroundPaint = initGameOverBackgroundPaint();
    private static Paint gameOverTextPaint = initGameOverTextPaint();
    private static Paint gameTitleLinePaint = initLinePaint();
    private static Paint filledPaint = initFilledPaint();
    private static int paddingBetweenShapes;

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
                //@TODO
            }
        }
    }

    public static int getRandomInt(int from, int to) {
        return random.nextInt(1 + to - from) + from;
    }

    public static int getGameTitleShapeColor() {
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
        gameOverTextPaint.setTextSize(DimenRes.getGameOverFontSize());

        return gameOverTextPaint;
    }

    public static Paint getGameBackgroundPaint() {
        return gameBackgroundPaint;
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

    public static int getLearningShapeColor() {
        return Color.BLUE;
    }

    public static boolean collidesWithExistingShapes(AbstractShape shapeToExamine, List<AbstractShape> shapes) {
        for (AbstractShape shape : shapes) {
            if (shape.intersects(shapeToExamine)) {
                return true;
            }
        }

        return false;
    }

    public static <E> E getRandomElement(Collection<E> collection) {
        int elementIndex = random.nextInt(collection.size());
        Iterator<E> iterator = collection.iterator();
        E element = null;

        for (int i = 0; i <= elementIndex; i++) {
            if (iterator.hasNext())
                element = iterator.next();
        }

        return element;
    }


    public static Rect getRectWithPadding(Rect rect, int padding) {
        return new Rect(rect.left + padding, rect.top + padding, rect.right - padding, rect.bottom - padding);
    }

    public static int getPaddingBetweenShapes() {
        return paddingBetweenShapes;
    }

    public static void setPaddingBetweenShapes(int paddingBetweenShapes) {
        GameUtils.paddingBetweenShapes = paddingBetweenShapes;
    }
}
