package cn.arirus.mddemo.scrolling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/20.
 */

public class BehaviorParent extends CoordinatorLayout.Behavior<View> {

  public BehaviorParent() {
    super();
  }

  public BehaviorParent(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
      @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes,
      int type) {
    return (axes & ViewCompat.SCROLL_AXIS_HORIZONTAL) !=0 ;
  }

  //@Override
  //public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
  //  return super.onLayoutChild(parent, child, layoutDirection);
  //}

  @Override
  public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
      @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed,
      int type) {
    Log.i(ARIRUS, "onNestedPreScroll: "+child.getClass()+" "+target.getClass());
    if (Math.abs(dx)<Math.abs(dy)) {
      //拦截
        consumed[0] = dx;
        consumed[1] = dy;
      //consumed[0] = dx;
      //target.scrollBy(-dx, 0);
    }
  }



}
