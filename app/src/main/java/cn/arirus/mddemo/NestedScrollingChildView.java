package cn.arirus.mddemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/12.
 */

public class NestedScrollingChildView extends AppCompatTextView {
  private int lastY;
  private final int[] offset = new int[2];
  private final int[] consumed = new int[2];

  public NestedScrollingChildView(Context context) {
    this(context,null);
  }

  public NestedScrollingChildView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public NestedScrollingChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    vc = ViewConfiguration.get(context);
  }

  @Override
  public Parcelable onSaveInstanceState() {
    return super.onSaveInstanceState();
  }

  @TargetApi(21)
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    VelocityTracker mVelocityTracker = VelocityTracker.obtain();
    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        lastY = (int) event.getRawY();
        break;
      case MotionEvent.ACTION_MOVE:
        int y = (int) (event.getRawY());
        int dy = y - lastY;
        lastY = y;


        if (startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL) //如果找到了支持嵌套滚动的父类
            && dispatchNestedPreScroll(0, dy, consumed, offset)) {//父类进行了一部分滚动

          Log.i(ARIRUS, "onTouchEvent: "+dy+" "+consumed[1]+" "+offset[1]);

          int remain = dy - consumed[1];//获取滚动的剩余距离
          if (remain != 0) {
            Log.i(ARIRUS, "onTouchEvent: "+remain);
            scrollBy(0, -remain);
          }

        } else {
          scrollBy(0, -dy);
        }
        break;
      case MotionEvent.ACTION_UP:
        mVelocityTracker.addMovement(event);
        mVelocityTracker.computeCurrentVelocity(1000, vc.getScaledMaximumFlingVelocity());
        dispatchNestedPreFling(0,mVelocityTracker.getYVelocity());
    }
    return true;
  }
  final ViewConfiguration vc;
  //@Override
  //public void scrollTo(int x, int y) {
  //  if (y>0) y=0;
  //  if (y<-200) y=-200;
  //
  //  super.scrollTo(x, y);
  //}
}
