package cn.arirus.mddemo.scrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/24.
 */

public class ParentViewInter extends ViewGroup {

  int mLastX = -1;
  int mLastY = -1;
  int mLastY1 = -1;

  public ParentViewInter(Context context) {
    this(context, null);
  }

  public ParentViewInter(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ParentViewInter(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      child.layout(l + i * getMeasuredWidth(), t, r + i * getMeasuredWidth(), b);
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    boolean result = false;
    //if (mLastX == -1 || mLastY == -1){
    //  mLastX = (int)(ev.getX()+0.5);
    //  mLastY = (int)(ev.getY()+0.5);
    //}
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mLastX = (int) (ev.getX() + 0.5);
        mLastY = (int) (ev.getY() + 0.5);
        break;
      case MotionEvent.ACTION_MOVE:
        result = (Math.abs(ev.getX() - mLastX) > Math.abs(ev.getY() - mLastY));
        break;
    }

    //mLastX = (int)(ev.getX()+0.5);
    //mLastY = (int)(ev.getY()+0.5);

    return result;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    boolean result = false;
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        result = true;
        break;
      case MotionEvent.ACTION_MOVE:
        scrollBy(-(int) (event.getX() - mLastX), 0);
        mLastX = (int) (event.getX() + 0.5);
        mLastY = (int) (event.getY() + 0.5);
        break;
    }


    return result;
  }
}
