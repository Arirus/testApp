package cn.arirus.mddemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.OverScroller;
import android.widget.Scroller;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/17.
 */

public class Widget extends View implements GestureDetector.OnGestureListener {

  float mLastX;
  float mLastY;

  int mTouchSlop;

  Scroller mScroller;
  OverScroller mOverScroller;

  GestureDetector mGestureDetector;

  public Widget(Context context) {
    this(context, null);
  }

  public Widget(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public Widget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    mOverScroller = new OverScroller(context, new AccelerateDecelerateInterpolator());
    Log.i(ARIRUS,
        "Weiget: " + mTouchSlop + " " + ViewConfiguration.get(context).getScaledPagingTouchSlop());

    //mGestureDetector = new GestureDetector(context,this);
  }

  float mXV;
  float mYV;
  VelocityTracker v;

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        v = VelocityTracker.obtain();
        mLastX = event.getX();
        mLastY = event.getY();
        mOverScroller.forceFinished(true);
        break;
      case MotionEvent.ACTION_MOVE:
        float deltaX = event.getX() - mLastX;
        float deltaY = event.getY() - mLastY;

        v.addMovement(event);
        v.computeCurrentVelocity(1000);

        mXV = v.getXVelocity();
        mYV = v.getYVelocity();

        if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) > mTouchSlop) {
          setX(getX() + deltaX);
          setY(getY() + deltaY);
          return true;
        }
        break;
      case MotionEvent.ACTION_UP:
        //mOverScroller.startScroll((int)getX(),(int)getY(),-(int)getX(),-(int)getY(),2000);
        //mOverScroller.springBack((int)getX(),(int)getY(),0, ((View)getParent()).getWidth()/2-getWidth(),0,((View)getParent()).getHeight()/2-getHeight());
        //if (getX()+getWidth()/2<((View)getParent()).getWidth()/2)
        //  mOverScroller.springBack((int) getX(),(int) getY(),0,0,(int) getY(),(int) getY());
        //else
        //  mOverScroller.springBack((int) getX(),(int) getY(),(((View)getParent()).getWidth()-getWidth()),0,(int) getY(),(int) getY());

        Log.i(ARIRUS, "onTouchEvent: " + v.getXVelocity() + " " + v.getYVelocity());

        mOverScroller.fling((int) getX(), (int) getY(), (int) mXV, (int) mYV, 0,
            ((View) getParent()).getWidth() / 2 - getWidth(), 0,
            ((View) getParent()).getHeight() / 1 - getHeight(), mXV > 0 ? (int) (getX()) : 0,
            mYV > 0 ? (int) (getY()) : 0);

        v.clear();
        v.recycle();

        Log.i(ARIRUS, "onTouchEvent: " + getX() + " " + getY());
        //mOverScroller.springBack((int)getX(),(int)getY(),(int)getX(), (int)getX(),(int)getY(),(int)getY());
        invalidate();
        break;
      default:
        break;
    }
    return true;
  }

  @Override
  public boolean onDown(MotionEvent e) {
    Log.i(ARIRUS, "onDown: ");
    return false;
  }

  @Override
  public void onShowPress(MotionEvent e) {

  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    return false;
  }

  @Override
  public void onLongPress(MotionEvent e) {
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return false;
  }

  //@Override
  //public void onClick(View v) {
  //  View viewGroup = ((View) getParent());
  //  mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(), getWidth(), getHeight());
  //}

  @Override
  protected void onDraw(Canvas canvas) {

    int width = getWidth();
    int height = getHeight();
    canvas.drawCircle(width/2,height/2,width/2,new Paint());

  }

  @Override
  public void setOnClickListener(@Nullable OnClickListener l) {

  }

  @Override
  public void computeScroll() {
    Log.i(ARIRUS, "computeScroll: ");
    if (mOverScroller.computeScrollOffset()) {
      setX(mOverScroller.getCurrX());
      setY(mOverScroller.getCurrY());
      Log.i(ARIRUS, "onTouchEvent: " + getX() + " " + getY());

      //((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
      Log.i(ARIRUS, "computeScroll: " + mOverScroller.getCurrX() + " " + mOverScroller.getCurrY());
      invalidate();
    }
  }
}
