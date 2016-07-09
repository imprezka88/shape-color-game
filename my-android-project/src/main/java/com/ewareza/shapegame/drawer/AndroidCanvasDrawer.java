package com.ewareza.shapegame.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.ewareza.shapegame.domain.generator.ColorFactory;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.resources.ImageResources;

import java.util.logging.Logger;

public class AndroidCanvasDrawer implements Drawer {
    private static final Logger LOGGER = Logger.getLogger(AndroidCanvasDrawer.class.getName());

    @Override
    public void draw(Canvas canvas, Paint myPaint, AbstractShape shape) {
        myPaint.setColor(shape.getColor());

        try {
            Drawable drawable = ImageResources.getInstance().getResource(shape.getName(), shape.getColor());
            drawable.setBounds(shape.getAssociatedRect());
            drawable.draw(canvas);
        } catch (IllegalArgumentException e) {
            LOGGER.warning(String.format("Resource for: %s with color: %s not found", shape.getName(), ColorFactory.ColorWithIndex.asString(shape.getColor())));
        }
    }
}
