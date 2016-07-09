package com.ewareza.shapegame.domain.generator;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class ShapeFactory {
    private static final List<Integer> sideSizes = new ArrayList<>();

    static
    {
        sideSizes.add(120);
//        sideSizes.add(150);
//        sideSizes.add(180);
//        sideSizes.add(240);
    }

    private final Class<? extends AbstractShape> shapeClass;
    private final Random random = new Random();

    public ShapeFactory(Class<? extends AbstractShape> shapeClass) {
        this.shapeClass = shapeClass;
    }

    public static int getMaxRectSize() {
        return Collections.max(sideSizes);
    }

    protected static int getMinRectSize() {
        return Collections.min(sideSizes);
    }

    public abstract AbstractShape getRandomShape(Rect areaToGenerateShape);

    public abstract AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex);

    public Class<? extends AbstractShape> getShapeClass() {
        return shapeClass;
    }

    protected int getRandomRectSize() {
        return sideSizes.get(random.nextInt(sideSizes.size()));
    }

    protected Point getRandomPointOnCanvas(Rect areaToGenerateShape) {
        int x = GameUtils.getRandomInt(getMaxRectSize() + areaToGenerateShape.left, (int) (areaToGenerateShape.right - getMaxRectSize() * 1.5));
        int y = GameUtils.getRandomInt(getMaxRectSize() + areaToGenerateShape.top, areaToGenerateShape.bottom - getMaxRectSize());

        return new Point(x, y);
    }

    public abstract AbstractShape getGameTitleShape();

    public abstract AbstractShape getLearningShape();
}
