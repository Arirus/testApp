package cn.arirus.mddemo.draw;

import android.content.res.Resources;
import android.util.TypedValue;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class Utils {
  public static float dp2px(int dp){
    return TypedValue.applyDimension(COMPLEX_UNIT_DIP,dp,Resources.getSystem().getDisplayMetrics());
  }

  public static float sp2px(int sp){
    return TypedValue.applyDimension(COMPLEX_UNIT_SP,sp,Resources.getSystem().getDisplayMetrics());
  }
}
