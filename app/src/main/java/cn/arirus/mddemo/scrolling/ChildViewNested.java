package cn.arirus.mddemo.scrolling;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class ChildViewNested extends AppCompatTextView implements NestedScrollingChild {

  private NestedScrollingChildHelper mChildHelper;

  int mLastX = -1;
  int mLastY = -1;
  int[] consumed = new int[2];
  int[] offsets = new int[2];

  public ChildViewNested(Context context) {
    this(context, null);
  }

  public ChildViewNested(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ChildViewNested(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mChildHelper = new NestedScrollingChildHelper(this);
    mChildHelper.setNestedScrollingEnabled(true);
    setNestedScrollingEnabled(true);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    boolean result = true;
    switch (event.getAction()) {
      case MotionEvent.ACTION_MOVE:
        int deltaX = (int) (event.getRawX() + 0.5) - mLastX;
        int deltaY = (int) (event.getRawY() + 0.5) - mLastY;

        mChildHelper.startNestedScroll(
            Math.abs(deltaX) > Math.abs(deltaY) ? ViewCompat.SCROLL_AXIS_HORIZONTAL
                : ViewCompat.SCROLL_AXIS_VERTICAL);

        mChildHelper.dispatchNestedPreScroll(deltaX, deltaY, consumed, offsets);

        scrollBy(0,-(deltaY-consumed[1]));
    }

    mLastX = (int) (event.getRawX() + 0.5);
    mLastY = (int) (event.getRawY() + 0.5);
    //Log.i(ARIRUS, "onTouchEvent: "+mLastX+" "+mLastY+" "+event.getAction());
    return true;
  }
}
