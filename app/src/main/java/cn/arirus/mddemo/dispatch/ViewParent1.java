package cn.arirus.mddemo.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/19.
 */

public class ViewParent1 extends ViewGroup {
  public ViewParent1(Context context) {
    super(context);
  }

  public ViewParent1(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ViewParent1(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int childSize = getChildCount();
    for (int i = 0; i < childSize; i++) {
      View view = getChildAt(i);
      view.layout(l, t, r, b);
    }
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    Log.i(ARIRUS, "dispatchTouchEvent: Parent:" + this);
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    Log.i(ARIRUS, "onInterceptTouchEvent: Parent:" + this);
    switch (ev.getAction()){
      case MotionEvent.ACTION_DOWN:
        return true;
    }
    return super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.i(ARIRUS, "onTouchEvent: Parent:" + this);



    return super.onTouchEvent(event);
  }
}
