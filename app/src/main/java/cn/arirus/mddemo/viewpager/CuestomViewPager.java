package cn.arirus.mddemo.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/21.
 */

public class CuestomViewPager extends ViewPager {
  public CuestomViewPager(@NonNull Context context) {
    super(context);
  }

  public CuestomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {

    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    boolean in = super.onInterceptTouchEvent(ev);
    Log.i(ARIRUS, "CuestomViewPager onInterceptTouchEvent: "+ev.getAction()+" "+in);

    return in;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    boolean in = super.onTouchEvent(ev);
    Log.i(ARIRUS, "CuestomViewPager onTouchEvent: "+ev.getAction()+" "+in);

    return in;
  }
}
