package com.ewareza.shapegame.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.ewareza.shapegame.domain.shape.AbstractShape;

public interface Drawer {
    void draw(Canvas canvas, Paint myPaint, AbstractShape shape);
}
