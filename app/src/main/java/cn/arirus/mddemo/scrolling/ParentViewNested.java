package cn.arirus.mddemo.scrolling;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ParentViewNested extends ViewGroup implements NestedScrollingParent {


  public ParentViewNested(Context context) {
    this(context, null);
  }

  public ParentViewNested(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ParentViewNested(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }



  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      child.layout(l + i * getMeasuredWidth()/2+50, t+50, r + i * getMeasuredWidth()/2-50, b-50);
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    boolean result = false;

    return result;
  }

  @Override
  public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    return target instanceof ChildViewNested;
  }

  @Override
  public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    if (Math.abs(dy) > Math.abs(dx)) {
      consumed[0] = dx;
    } else {
      scrollBy(-dx, 0);
    }
  }
}
