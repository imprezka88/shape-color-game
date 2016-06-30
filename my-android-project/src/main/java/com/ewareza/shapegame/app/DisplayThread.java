package com.ewareza.shapegame.app;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public abstract class DisplayThread extends Thread {
    protected SurfaceHolder surfaceHolder;
    private boolean running;

    public DisplayThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        while (running) {
            updatePhysics();

            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);

                if (canvas != null) {
                    synchronized (surfaceHolder) {
                        drawUpdatedView(canvas);
                    }
                }
            } finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }

            tryToSleep();
        }
    }

    protected abstract void updatePhysics();

    protected abstract void drawUpdatedView(Canvas canvas);

    protected abstract void tryToSleep();

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
