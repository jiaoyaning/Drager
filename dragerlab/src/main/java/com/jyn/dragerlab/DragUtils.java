package com.jyn.dragerlab;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jiao on 2019/8/7.
 */
public class DragUtils implements View.OnTouchListener {

    private boolean isSetBorder = false;

    private int viewX;
    private int viewY;

    private int leftBorder;
    private int topBorder;
    private int rightBorder;
    private int bottomBorder;

    /**
     * 判断是否是第一次点击（如果是第一次点击，就记录view位置）
     */
    private boolean isFirstMove = true;
    private float viewOriginalX;
    private float viewOriginalY;

    public DragUtils(View view) {
        view.setOnTouchListener(this);
    }

    public void setBorder(int leftBorder, int topBorder, int rightBorder, int bottomBorder) {
        this.leftBorder = leftBorder;
        this.topBorder = topBorder;
        this.rightBorder = rightBorder;
        this.bottomBorder = bottomBorder;
        isSetBorder = true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取view初始位置
                getOriginViewCoord(view);
                //获取移动后的view位置
                getViewRawCoord(event);
                //获取父view边界
                getParentBorder(view);
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                moveView(x, y, view);
                getViewRawCoord(event);
            default:
        }
        return true;
    }

    /**
     * 获取父view的边界
     */
    private void getParentBorder(View view) {
        if (!isSetBorder) {
            ViewGroup mViewGroup = (ViewGroup) view.getParent();
            if (null != mViewGroup) {
                leftBorder = mViewGroup.getLeft();
                topBorder = mViewGroup.getTop();
                rightBorder = mViewGroup.getRight();
                bottomBorder = mViewGroup.getBottom();
            }
        }
    }

    /**
     * 获取view的坐标
     */
    private void getViewRawCoord(MotionEvent event) {
        viewX = (int) event.getRawX();
        viewY = (int) event.getRawY();
    }

    /**
     * 获取view的初始文字
     */
    private void getOriginViewCoord(View view) {
        if (isFirstMove) {
            viewOriginalX = view.getX();
            viewOriginalY = view.getY();
            isFirstMove = false;
        }
    }

    private void moveView(int x, int y, View view) {
        // 获取手指移动的距离
        int mx = x - viewX;
        int my = y - viewY;

        // 获取View坐标
        int l = view.getLeft();
        int t = view.getTop();
        int r = view.getRight();
        int b = view.getBottom();

        int l1 = l + mx;
        int t1 = t + my;
        int r1 = r + mx;
        int b1 = b + my;

        if (l1 < leftBorder) {
            l1 = leftBorder;
            r1 = leftBorder + view.getWidth();
        }

        if (t1 < topBorder) {
            t1 = topBorder;
            b1 = topBorder + view.getHeight();
        }

        if (r1 > rightBorder) {
            r1 = rightBorder;
            l1 = rightBorder - view.getWidth();
        }

        if (b1 > bottomBorder) {
            b1 = bottomBorder;
            t1 = bottomBorder - view.getHeight();
        }

        view.layout(l1, t1, r1, b1);
    }
}
