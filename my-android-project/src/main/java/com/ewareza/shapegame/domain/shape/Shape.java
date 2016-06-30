package com.ewareza.shapegame.domain.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.resources.ImageResources;
import com.ewareza.shapegame.domain.generator.ColorGenerator;

import java.util.logging.Logger;

public abstract class Shape {
    private static final Logger LOGGER = Logger.getLogger(Shape.class.getName());

    private static final int GROW_STEP = 10;
    private static final int MOVE_DOWN_STEP = 5;
    private static final int MOVE_RIGHT_STEP = 1;
    protected final int color;

    protected final Rect associatedRect;
    protected final int stepForCurrentGame = Game.getStepForCurrentGame();
    private boolean moveRight = true;
    public Shape(Rect associatedRect, int color) {
        this.associatedRect = associatedRect;
        this.color = color;
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

    public int getColor() {
        return color;
    }

    public abstract boolean contains(Point point);

    public boolean intersects(Shape shape) {
        return Rect.intersects(associatedRect, shape.associatedRect);
    }

    public void move() {
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

    public void growAndFallDown() {
        if(canMoveAndGrow()) {
            associatedRect.left += MOVE_RIGHT_STEP;
            associatedRect.top += MOVE_DOWN_STEP;
            associatedRect.right += MOVE_RIGHT_STEP + GROW_STEP;
            associatedRect.bottom += MOVE_DOWN_STEP + GROW_STEP;

            updateShapesCoordinates();
        }
    }

    private boolean canMoveAndGrow() {
        return associatedRect.bottom < DimenRes.getScreenHeight() - 150 && associatedRect.right < DimenRes.getScreenWidth() - 20;
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

    public Rect getAssociatedRect() {
        return associatedRect;
    }
}
