package cn.arirus.mddemo.layout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class TagLayout extends ViewGroup {

  int maxWidth = 0;
  int maxHeight = 0;

  int usedWithd = 0;
  int usedHeight = 0;

  public TagLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    for (int i = 0; i < TagView.citys.size(); i++) {
      TagView tagView = new TagView(context);
      tagView.setCity(i);
      tagView.setLayoutParams(
          new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
      addView(tagView);
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
    int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

    Log.i(ARIRUS, "onMeasure: " + widthMeasureSize);
    Log.i(ARIRUS, "onMeasure: " + widthMeasureMode);
    Log.i(ARIRUS, "onMeasure: " + heightMeasureSize);
    Log.i(ARIRUS, "onMeasure: " + heightMeasureMode);

    int curMaxHeight = 0;

    for (int i = 0; i < getChildCount(); i++) {
      measureChildWithMargins(getChildAt(i), widthMeasureSpec, usedWithd, heightMeasureSpec,
          usedHeight);
      if (getChildAt(i).getMeasuredWidth() + usedWithd <= widthMeasureSize) {
        usedWithd += getChildAt(i).getMeasuredWidth();
        curMaxHeight = Math.max(curMaxHeight,getChildAt(i).getMeasuredHeight());
      } else {
        usedWithd = 0;
        usedHeight += curMaxHeight;
        curMaxHeight = getChildAt(i).getMeasuredHeight();
      }

      maxWidth = Math.max(maxWidth, usedWithd);
      maxHeight = Math.max(maxHeight, usedHeight);
    }

    setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec),
        resolveSize(maxHeight, heightMeasureSpec));

    //setMeasuredDimension((maxWidth),
    //    (maxHeight));
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    if (!changed) return;
    //for (int i = 0; i < getChildCount(); i++) {
    //  View child = getChildAt(i);
    //  //Rect childBounds = childrenBounds.get(i);
    //  child.layout(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
    //  Log.i(ARIRUS, "onLayout: ");
    //}

    usedWithd = 0;
    usedHeight = 0;

    int curMaxHeight = 0;
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      if (l+usedWithd+child.getMeasuredWidth()<r) {
        child.layout(l + usedWithd, t + usedHeight, l + usedWithd + child.getMeasuredWidth(),
            l + usedHeight + child.getMeasuredHeight());
        usedWithd += child.getMeasuredWidth();
        curMaxHeight = Math.max(curMaxHeight,child.getMeasuredHeight());
      }else {
        usedWithd = 0;
        usedHeight += curMaxHeight;
        child.layout(l + usedWithd, t + usedHeight, l + usedWithd + child.getMeasuredWidth(),
            l + usedHeight + child.getMeasuredHeight());
        usedWithd += child.getMeasuredWidth();
        curMaxHeight = child.getMeasuredHeight();
      }
    }
  }
}
