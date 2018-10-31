package cn.arirus.mddemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/17.
 */

public class ToolbarRecyclerBehavior extends CoordinatorLayout.Behavior<Toolbar> {
  private Context mContext;

  public ToolbarRecyclerBehavior() {
  }

  public ToolbarRecyclerBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
    mContext = context;

    final TypedArray styledAttributes =
        mContext.getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
    mActionBarSize = (int) styledAttributes.getDimension(0, 0);
    styledAttributes.recycle();
  }

  int mActionBarSize;

  float mStart;

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
    return dependency instanceof RecyclerView;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
    if (mStart == 0) mStart = dependency.getY();

    double perccent = dependency.getY() / mStart;
    int y = (int) (child.getHeight() * (-perccent));
    child.setY(y);
    dependency.setPadding(0, (int) (mActionBarSize * (1 - perccent)), 0, 0);
    dependency.invalidate();

    return true;
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child,
      View directTargetChild, View target, int nestedScrollAxes) {
    Log.i(ARIRUS, "onStartNestedScroll: "+target.getY()+" "+target.getScrollY()+" "+target.getTranslationY());
    return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 && target.getPaddingTop()>=mActionBarSize;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target,
      int dx, int dy, int[] consumed) {
    consumed[1] = 0;
    child.setScrollX(-dy);
  }
}
