package com.ewareza.shapegame.app.learningGame;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.app.learningGame.LearningThread;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.generator.RandomShapesGenerator;
import com.ewareza.shapegame.shape.objects.Shape;

import java.util.List;

public class LearningView extends SurfaceView implements SurfaceHolder.Callback {
    private List<Shape> shapes;
    private LearningThread learningThread;
    private SurfaceHolder holder;

    public LearningView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);

        shapes = RandomShapesGenerator.generateLearningShapes();
        learningThread = new LearningThread(holder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!learningThread.isRunning()) {
            //@TODO check if needed
            learningThread = new LearningThread(holder);
            learningThread.setRunning(true);
            learningThread.start();
        } else {
            learningThread.setRunning(true);
            learningThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        learningThread.setRunning(false);
        GameUtils.StopThread(learningThread);
    }
}
