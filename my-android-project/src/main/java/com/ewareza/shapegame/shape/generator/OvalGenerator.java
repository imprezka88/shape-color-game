package com.ewareza.shapegame.shape.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.GameUtils;
import com.ewareza.shapegame.shape.objects.Oval;
import com.ewareza.shapegame.shape.objects.Rectangle;
import com.ewareza.shapegame.shape.objects.Shape;

public class OvalGenerator extends ShapeGenerator {
    private static final OvalGenerator INSTANCE = new OvalGenerator(Oval.class);

    public OvalGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static ShapeGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generate() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().generate();
        Rect rect = new Rect(rectangle.getLeft(), rectangle.getTop(), rectangle.getRight(), rectangle.getBottom());
        Oval oval = new Oval(ColorGenerator.generateColor(), rect);

        return oval;
    }

    @Override
    public Shape getTitleShape() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().getTitleShape();
        Rect rect = rectangle.asRect();

        return new Oval(GameUtils.getDefaultShapeColor(), rect);
    }
}
