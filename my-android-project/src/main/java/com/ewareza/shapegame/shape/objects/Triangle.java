package com.ewareza.shapegame.shape.objects;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.android.R;

public class Triangle extends Shape {
    private static final String TRIANGLE = "triangle";
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c, int color) {
        super(color, new Rect(Math.min(a.x, Math.min(b.x, c.x)), Math.min(a.y, Math.min(b.y, c.y)),
                Math.max(a.x, Math.max(b.x, c.x)), Math.max(a.y, Math.max(b.y, c.y))));
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean contains(Point point) {
        boolean b1, b2, b3;

        b1 = sign(point, a, b) < 0.0f;
        b2 = sign(point, b, c) < 0.0f;
        b3 = sign(point, c, a) < 0.0f;

        return ((b1 == b2) && (b2 == b3));
    }

    @Override
    protected void updateShapesCoordinates() {
        //update in moving
    }

    float sign(Point examinedPoint, Point p1, Point p2) {
        return (examinedPoint.x - p2.x) * (p1.y - p2.y) - (p1.x - p2.x) * (examinedPoint.y - p2.y);
    }

    @Override
    public void moveRight() {
        super.moveRight();
        a.x += stepForCurrentGame;
        b.x += stepForCurrentGame;
        c.x += stepForCurrentGame;
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        a.x -= stepForCurrentGame;
        b.x -= stepForCurrentGame;
        c.x -= stepForCurrentGame;
    }

    @Override
    public int getGameTitleSoundId() {
        return R.raw.find_triangle;
    }

    @Override
    public String getName() {
        return TRIANGLE;
    }


    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }
}
