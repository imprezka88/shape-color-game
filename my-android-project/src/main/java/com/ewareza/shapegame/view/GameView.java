package com.ewareza.shapegame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.ewareza.shapegame.app.DisplayThread;
import com.ewareza.shapegame.app.GameUtils;

import java.util.concurrent.CyclicBarrier;
import java.util.logging.Logger;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private final static Logger logger = Logger.getLogger(GameView.class.getName());
    private CyclicBarrier gameOverCyclicBarrier;
    private DisplayThread displayThread;
    private SurfaceHolder holder;

    public GameView(Context context, CyclicBarrier gameOverCyclicBarrier) {
        super(context);
        this.gameOverCyclicBarrier = gameOverCyclicBarrier;
        init(gameOverCyclicBarrier);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init(CyclicBarrier gameOverCyclicBarrier) {
        holder = getHolder();
        holder.addCallback(this);

        displayThread = new DisplayThread(holder, gameOverCyclicBarrier);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!displayThread.isRunning()) {
            //@TODO check if needed
            displayThread = new DisplayThread(holder, gameOverCyclicBarrier);
            displayThread.start();
        } else {
            displayThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopDisplayThread();
    }

    private void stopDisplayThread() {
        displayThread.setIsRunning(false);
        GameUtils.StopThread(displayThread);
    }
}
