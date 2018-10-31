package cn.arirus.mddemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/12.
 */

public class NestedScrollingParentLayout extends LinearLayout implements NestedScrollingParent {

  private int imgHeight;

  public NestedScrollingParentLayout(Context context) {
    super(context);
  }

  public NestedScrollingParentLayout(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public NestedScrollingParentLayout(Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    Log.i(ARIRUS, "onStartNestedScroll: "+target.getScrollY());
    if (target instanceof RecyclerView) return true;
    return false;
  }


  @Override
  public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    Log.i(ARIRUS, "onNestedPreScroll: "+dy+ "  "+getScrollY()+" "+target.getScrollY());
    if (dy>0 && (getScrollY()<144) || dy<0 &&(getScrollY()>0 )) {
      scrollBy(0, dy);
      consumed[1] = dy;
    }
  }


  @Override
  public void scrollTo(int x, int y) {
    if (y>144) y=144;
    if (y<0) y = 0;
    super.scrollTo(x, y);
  }
}
