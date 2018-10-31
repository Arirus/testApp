package cn.arirus.mddemo.scrolling;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import static android.support.v4.view.ViewCompat.TYPE_TOUCH;
import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/20.
 */

public class ParentView extends ViewGroup {

  Scroller mScroller;


  boolean isVerticalDrag;

  int mLastX = -1;
  int mLastY = -1;

  public ParentView(Context context) {
    this(context, null);
  }

  public ParentView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @TargetApi(21)
  protected void init(Context context) {
    mScroller = new Scroller(context,new AccelerateDecelerateInterpolator());
    setNestedScrollingEnabled(true);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int count =getChildCount();
    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      child.layout(l+i*getMeasuredWidth(), t, r+i*getMeasuredWidth(), b);
    }
  }

  //@TargetApi(21)
  //@Override
  //public boolean onInterceptTouchEvent(MotionEvent ev) {
  //
  //  boolean result = false;
  //
  //  switch (ev.getAction()) {
  //    case MotionEvent.ACTION_DOWN:
  //      break;
  //    case MotionEvent.ACTION_MOVE:
  //      float dx = ev.getX() - mLastX;
  //      float dy = ev.getX() - mLastX;
  //      int[] mScrollConsumed = new int[2];
  //      int[] mNestedOffsets = new int[2];
  //
  //      result = dispatchNestedPreScroll((int)dx,(int) dy,mScrollConsumed,mNestedOffsets);
  //      if (true){
  //
  //      }else {
  //        scrollBy(-(int)dx,0);
  //      }
  //    case MotionEvent.ACTION_UP:
  //      break;
  //    default:
  //      break;
  //  }
  //
  //  return result || super.onInterceptTouchEvent(ev);
  //}


  @TargetApi(21)
  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    boolean result = false;

    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        mLastX = (int)(event.getX()+0.5);
        mLastY = (int)(event.getY()+0.5);
        startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL);
        break;
      case MotionEvent.ACTION_MOVE:
        if (isVerticalDrag) return false;

        float dx = event.getX() - mLastX;
        float dy = event.getY() - mLastY;
        int[] mScrollConsumed = new int[2];
        int[] mNestedOffsets = new int[2];

        //有人消费是 true 没人消费是 false
        result = !dispatchNestedPreScroll((int)dx,(int) dy,mScrollConsumed,mNestedOffsets);
        Log.i(ARIRUS, "onInterceptTouchEvent: ParentView "+result);
        isVerticalDrag = Math.abs(dy)> ViewConfiguration.getTouchSlop();

        break;
      case MotionEvent.ACTION_UP:
        isVerticalDrag = false;
    }
    return result ;
  }

  @Override
  public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    return child instanceof ChildView && (nestedScrollAxes&ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
  }

  @Override
  public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    if (Math.abs(dx)>Math.abs(dy)) {
      //拦截
      consumed[0] = dx;
      consumed[1] = dy;
      //consumed[0] = dx;
      //target.scrollBy(-dx, 0);
    }
  }

  @TargetApi(21)
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    boolean result = false;

    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        result = startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL);
        break;
      case MotionEvent.ACTION_MOVE:
        float dx = event.getX() - mLastX;
        float dy = event.getY() - mLastY;
        int[] mScrollConsumed = new int[2];
        int[] mNestedOffsets = new int[2];


        result = !dispatchNestedPreScroll((int)dx,(int) dy,mScrollConsumed,mNestedOffsets);
        if (result){
          scrollBy(-(int)dx,0);
        }else {

        }

        mLastX = (int)(event.getX()+0.5);
        mLastY = (int)(event.getY()+0.5);
        break;

    }



    return result;
  }

  @Override
  public void computeScroll() {
    if (mScroller.computeScrollOffset()){
      scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
      invalidate();
    }
  }
}
