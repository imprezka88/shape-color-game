package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;
import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.generator.ColorFactory;
import com.ewareza.shapegame.domain.generator.ShapeFactory;
import com.ewareza.shapegame.domain.generator.SquareFactory;

public class Circle extends AbstractShape {
    private final static String CIRCLE = "circle";

    private Circle(Rect rect, int color) {
        super(rect, color, Game.getDrawer(), Game.getMover());
    }

    @Override
    public String getName() {
        return CIRCLE;
    }

    //@TODO inner class or separate class??
    public static class CircleFactory extends ShapeFactory {
        private static final CircleFactory INSTANCE = new CircleFactory(Circle.class);

        public CircleFactory(Class<? extends AbstractShape> shapeClass) {
            super(shapeClass);
        }

        public static CircleFactory getInstance() {
            return INSTANCE;
        }

        @Override
        public AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex) {
            Square square = (Square) SquareFactory.getInstance().getRandomShapeInNextRow(areaToGenerateShape, shapeIndex);
            return new Circle(square.getAssociatedRect(), ColorFactory.generateColor());
        }

        @Override
        public AbstractShape getRandomShape(Rect areaToGenerateShape) {
            Square square = (Square) SquareFactory.getInstance().getRandomShape(areaToGenerateShape);
            return new Circle(square.getAssociatedRect(), ColorFactory.generateColor());
        }

        @Override
        public AbstractShape getGameTitleShape() {
            Square square = (Square) SquareFactory.getInstance().getGameTitleShape();

            return new Circle(square.getAssociatedRect(), GameUtils.getGameTitleShapeColor());
        }

        @Override
        public AbstractShape getLearningShape() {
            int left = GameUtils.LEARNING_SHAPE_LEFT;
            int top = GameUtils.LEARNING_SHAPE_TOP;
            return new Circle(new Rect(left, top, left + 5, top + 10), GameUtils.getLearningShapeColor());
        }
    }
}
