package com.ewareza.shapegame.shape.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.shape.generator.ColorGenerator;

import java.util.logging.Logger;

public abstract class Shape {
    private final static Logger LOGGER = Logger.getLogger(Shape.class.getName());
    protected final int color;
    protected final Rect associatedRect;
    protected final int stepForCurrentGame = Game.getStepForCurrentGame();
    private boolean moveRight = true;

    public Shape(int color, Rect associatedRect) {
        this.color = color;
        this.associatedRect = associatedRect;
    }

    public int getColor() {
        return color;
    }

    public void draw(Canvas canvas, Paint myPaint) {
        myPaint.setColor(color);

        try {
            Drawable drawable = ImageResources.getInstance().getResource(getName(), color);
            drawable.setBounds(associatedRect);
            drawable.draw(canvas);
        } catch (IllegalArgumentException e) {
            LOGGER.warning(String.format("Resource for: %s with color: %s not found", getName(), ColorGenerator.ColorWithIndex.asString(color)));
        }
    }

    public abstract boolean contains(Point point);

    public boolean intersects(Shape shape) {
        return Rect.intersects(associatedRect, shape.associatedRect);
    }

    public void update() {
        //@TODO use states instead of if else?
        if (moveRight) {
            if (!canMoveRight()) {
                moveRight = false;
            } else {
                moveRight();
            }
        } else {
            if (!canMoveLeft()) {
                moveRight = true;
            } else {
                moveLeft();
            }
        }
        updateShapesCoordinates();
    }

    protected abstract void updateShapesCoordinates();

    public void moveRight() {
        associatedRect.right += stepForCurrentGame;
        associatedRect.left += stepForCurrentGame;
    }

    public void moveLeft() {
        associatedRect.right -= stepForCurrentGame;
        associatedRect.left -= stepForCurrentGame;
    }

    private boolean canMoveLeft() {
        return associatedRect.left - stepForCurrentGame >= 0;
    }

    private boolean canMoveRight() {
        return associatedRect.right + stepForCurrentGame <= DimenRes.getInstance().getScreenWidth();
    }

    public abstract int getGameTitleSoundId();

    public abstract String getName();
}
