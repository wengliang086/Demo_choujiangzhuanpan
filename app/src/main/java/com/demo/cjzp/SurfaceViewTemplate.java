package com.demo.cjzp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2017/2/3.
 * SurfaceView extends View
 * 其实View是在UI线程中进行绘制
 * SurfaceView 是在一个子线程中队自己进行绘制，优势：避免造成UI线程阻塞。
 * 其实，我们SurfaceView中包含一个专门用于绘制的Surface，Surface中包含一个Canvas
 * 那么，如何获得Canvas？
 * getHolder->SurfaceHolder
 * holder->Canvas + 管理SurfaceView的生命周期
 * surfaceCreated
 * surfaceChanged
 * surfaceDestoryed
 */

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    /**
     * 与SurfaceHolder绑定的Canvas
     */
    private Canvas mCanvas;

    /**
     * 线程开关
     */
    private boolean isRunning;
    /**
     * 绘制子线程
     */
    private Thread t;

    public SurfaceViewTemplate(Context context) {
        this(context, null);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        // 可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);

        // 设置常亮
        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isRunning = true;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {
                // draw something
            }
        } catch (Exception e) {
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }
}
