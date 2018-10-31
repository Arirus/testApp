package cn.arirus.mddemo.dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/19.
 */

public class ChildView extends View {
  public ChildView(Context context) {
    super(context);
  }

  public ChildView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    Log.i(ARIRUS, "dispatchTouchEvent: child");
    return super.dispatchTouchEvent(event);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.i(ARIRUS, "onTouchEvent: child");
    //switch (event.getAction()) {
    //  case MotionEvent.ACTION_DOWN:
    //    return true;
    //  default:
    //    return false;
    //}
    return super.onTouchEvent(event);
  }
}
