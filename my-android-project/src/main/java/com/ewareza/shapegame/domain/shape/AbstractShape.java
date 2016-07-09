package com.ewareza.shapegame.domain.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.shapegame.drawer.Drawer;
import com.ewareza.shapegame.mover.Mover;
import com.ewareza.shapegame.resources.DimenRes;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public abstract class AbstractShape implements Shape {
    private static final Logger LOGGER = Logger.getLogger(AbstractShape.class.getName());

    private static final int GROW_STEP = 8;
    private static final int MOVE_DOWN_STEP = 5;
    private static final int MOVE_RIGHT_STEP = 3;
    protected final Rect associatedRect;
    public Drawer drawer;
    public Mover mover;
    private int color;
    private boolean moveRight;

    {
        Random random = new Random();
        moveRight = random.nextBoolean();
    }

    public AbstractShape(Rect associatedRect, int color, Drawer drawer) {
        this.associatedRect = associatedRect;
        this.color = color;
        this.drawer = drawer;
    }

    public AbstractShape(Rect associatedRect, int color, Drawer drawer, Mover mover) {
        this.associatedRect = associatedRect;
        this.color = color;
        this.drawer = drawer;
        this.mover = mover;
    }

    @Override
    public void draw(Canvas canvas, Paint myPaint) {
        drawer.draw(canvas, myPaint, this);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean contains(Point point) {
        return associatedRect.contains(point.x, point.y);
    }

    public boolean intersects(AbstractShape shape) {
        return Rect.intersects(associatedRect, shape.associatedRect);
    }

    public void move(List<AbstractShape> shapes) {
        mover.move(shapes, this);
    }

    public void growAndFallDown() {
        if(canMoveAndGrow()) {
//            associatedRect.left -= GROW_STEP/2 + MOVE_RIGHT_STEP;
            associatedRect.top += MOVE_DOWN_STEP;
            associatedRect.right += GROW_STEP;
            associatedRect.bottom += MOVE_DOWN_STEP + GROW_STEP;
        }
    }

    private boolean canMoveAndGrow() {
        return associatedRect.bottom < DimenRes.getScreenHeight() - 250 && associatedRect.right < DimenRes.getScreenWidth() - 20;
    }

    public abstract String getName();

    public Rect getAssociatedRect() {
        return associatedRect;
    }

    public boolean getMoveRight() {
        return moveRight;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
}
