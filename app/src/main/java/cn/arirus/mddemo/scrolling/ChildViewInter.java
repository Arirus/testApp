package cn.arirus.mddemo.scrolling;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Arirus on 2018/9/24.
 */

public class ChildViewInter extends AppCompatTextView {

  int mLastX = -1;
  int mLastY = -1;

  public ChildViewInter(Context context) {
    this(context,null);
  }

  public ChildViewInter(Context context, AttributeSet attrs) {
    this(context, attrs,0);
  }

  public ChildViewInter(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    boolean result = false;
    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        result = true;
        break;
      case MotionEvent.ACTION_MOVE:
        scrollBy(0,-(int)(event.getY()-mLastY));
        break;
    }
    mLastX = (int)(event.getX()+0.5);
    mLastY = (int)(event.getY()+0.5);

    return result;
  }
}
