package com.ewareza.shapegame.domain.generator;

import android.graphics.Point;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.domain.objects.Shape;
import com.ewareza.shapegame.domain.objects.Triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriangleGenerator extends ShapeGenerator {
    private static TriangleGenerator INSTANCE = new TriangleGenerator(Triangle.class);
    private Random random = new Random();
    private List<Triangle> trianglePatterns = new ArrayList<Triangle>();
    private int screenWidth;
    private int screenHeight;

    {
        initTrianglePatterns();
        init();
    }

    public TriangleGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static TriangleGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generateRandomShape() {
        Triangle trianglePattern = trianglePatterns.get(random.nextInt(trianglePatterns.size()));
        int size = getRandomRectSize() / 2;
        Point point = generateRandomPointOnCanvas();
        int startX = point.x;
        int startY = point.y;

        Point a = new Point(startX + size * trianglePattern.getA().x, startY + size * trianglePattern.getA().y);
        Point b = new Point(startX + size * trianglePattern.getB().x, startY + size * trianglePattern.getB().y);
        Point c = new Point(startX + size * trianglePattern.getC().x, startY + size * trianglePattern.getC().y);

        Triangle triangle = new Triangle(a, b, c, ColorGenerator.generateColor());

        return triangle;
    }

    private void init() {
        screenWidth = DimenRes.getInstance().getScreenWidth();
        screenHeight = DimenRes.getInstance().getScreenHeight();
    }

    private void initTrianglePatterns() {
        Point a = new Point(0, 2);
        Point b = new Point(2, 2);
        Point c = new Point(1, 0);
        Triangle triangleUp = new Triangle(a, b, c, ColorGenerator.generateColor());

        a = new Point(0, 0);
        b = new Point(1, 2);
        c = new Point(2, 0);
        Triangle triangleDown = new Triangle(a, b, c, ColorGenerator.generateColor());

        a = new Point(0, 0);
        b = new Point(0, 2);
        c = new Point(2, 1);
        Triangle triangleRight = new Triangle(a, b, c, ColorGenerator.generateColor());

        a = new Point(2, 0);
        b = new Point(2, 2);
        c = new Point(0, 1);
        Triangle triangleLeft = new Triangle(a, b, c, ColorGenerator.generateColor());

        trianglePatterns.add(triangleUp);
        trianglePatterns.add(triangleDown);
        trianglePatterns.add(triangleLeft);
        trianglePatterns.add(triangleRight);
    }

    @Override
    public Shape generateGameTitleShape() {
        int screenWidth = DimenRes.getScreenWidth();
        int gameTitleHeight = DimenRes.getGameTitleHeight();

        Point a = new Point(screenWidth / 2 - gameTitleHeight / 2, gameTitleHeight);
        Point b = new Point(screenWidth / 2 + gameTitleHeight / 2, gameTitleHeight);
        Point c = new Point(screenWidth / 2, 0);

        return new Triangle(a, b, c, GameUtils.getDefaultShapeColor());
    }
}