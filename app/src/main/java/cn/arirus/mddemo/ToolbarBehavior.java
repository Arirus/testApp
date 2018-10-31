package cn.arirus.mddemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

/**
 * Created by Arirus on 2018/9/17.
 */

public class ToolbarBehavior extends CoordinatorLayout.Behavior<Toolbar> {

  private int mScrolly;

  public ToolbarBehavior() {
  }

  public ToolbarBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
    return dependency instanceof AppBarLayout;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
    if (mScrolly == 0) mScrolly = dependency.getHeight();

    double percent = dependency.getY()/mScrolly;

    Log.i(ARIRUS, "onDependentViewChanged: "+dependency.getTranslationY()+" "+dependency.getScrollY()+" "+dependency.getY()+" "+percent);

    int y = (int) (child.getHeight()*(-percent-1));

    Log.i(ARIRUS, "onDependentViewChanged: "+y);
    
    child.setY(y);


    return true;
  }
}
