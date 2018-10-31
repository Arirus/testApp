package cn.arirus.mddemo.scrolling;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/20.
 */

public class ChildView extends AppCompatTextView {

  Scroller mScroller;
  int mLastX = -1;
  int mLastY = -1;

  public ChildView(Context context) {
    this(context,null);
  }

  public ChildView(Context context, AttributeSet attrs) {
    this(context, attrs,0);
  }

  public ChildView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @TargetApi(21)
  protected void init(Context context){
    mScroller = new Scroller(context);
    setNestedScrollingEnabled(true);
  }


  @TargetApi(21)
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    boolean result = false;

    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        result = startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
        mLastX = (int)(event.getX()+0.5);
        mLastY = (int)(event.getY()+0.5);
        break;
      case MotionEvent.ACTION_MOVE:
        float dx = event.getX() - mLastX;
        float dy = event.getY() - mLastY;
        int[] mScrollConsumed = new int[2];
        int[] mNestedOffsets = new int[2];

        result =!dispatchNestedPreScroll((int)dx,(int) dy,mScrollConsumed,mNestedOffsets);
        //Log.i(ARIRUS, "onTouchEvent: ChildView "+result+" "+dx+" "+dy);

        if (result){
          scrollBy(0,-(int)dy);
        }else {
        }
        mLastX = (int)(event.getX()+0.5);
        mLastY = (int)(event.getY()+0.5);
        break;
    }



    return result || super.onTouchEvent(event);
  }
}
