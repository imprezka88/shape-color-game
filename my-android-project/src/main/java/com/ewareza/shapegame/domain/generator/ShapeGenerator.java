package com.ewareza.shapegame.domain.generator;

import android.graphics.Point;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.domain.objects.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class ShapeGenerator {
    private final List<Integer> sideSizes = new ArrayList<>();
    private final Class<? extends Shape> shapeClass;
    private final Random random = new Random();

    {
        sideSizes.add(120);
        sideSizes.add(160);
        sideSizes.add(200);
    }

    public ShapeGenerator(Class<? extends Shape> shapeClass) {
        this.shapeClass = shapeClass;
    }

    public abstract Shape generateRandomShape();

    public Class<? extends Shape> getShapeClass() {
        return shapeClass;
    }

    protected int getMaxRectSize() {
        return Collections.max(sideSizes);
    }

    protected int getRandomRectSize() {
        return sideSizes.get(random.nextInt(sideSizes.size()));
    }

    protected Point generateRandomPointOnCanvas() {
        int x = GameUtils.getRandomInt(getMaxRectSize(), DimenRes.getScreenWidth() - getMaxRectSize());
        int y = GameUtils.getRandomInt(getMaxRectSize() + DimenRes.getGameTitleHeight(), DimenRes.getScreenHeight() - getMaxRectSize());

        return new Point(x, y);
    }

    public abstract Shape generateGameTitleShape();
    public abstract Shape generateLearningShape();
}
