package cn.arirus.mddemo.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import cn.arirus.mddemo.draw.Utils;
import java.util.Arrays;
import java.util.List;

public class TagView extends View {

  Rect rect = new Rect();

  static final float TEXT_PADDING = Utils.dp2px(8);
  static final float RAD = Utils.dp2px(8);
  public static List<String> citys =
      Arrays.asList("New York City", "Los Angeles", "Chicago", "Dallas–Fort Worth", "Houston",
          "Washington, D.C.", "Miami", "Philadelphia", "Atlanta", "Boston", "Phoenix",
          "San Francisco", "Riverside–San Bernardino", "Detroit", "Seattle", "Minneapolis–St. Paul",
          "San Diego", "Tampa–St. Petersburg");

  float[] sizes = new float[] {
      Utils.sp2px(18), Utils.sp2px(22), Utils.sp2px(26), Utils.sp2px(30)
  };

  int[] colors = new int[] {
      Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW
  };

  String city ;

  public void setCity(int index) {
    this.city = citys.get(index);
  }

  Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  {
    mPaint.setStyle(Paint.Style.FILL);
  }

  public TagView(Context context) {
    super(context);
  }

  public TagView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    mPaint.setColor(colors[(int) ((Math.random() * 1000) % 4)]);
    mPaint.setTextSize(sizes[(int) ((Math.random() * 1000) % 4)]);

    mPaint.getTextBounds(city, 0, city.length(), rect);

    setMeasuredDimension((int)(rect.right - rect.left + TEXT_PADDING * 2),
        (int)(rect.bottom - rect.top + TEXT_PADDING * 2));
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override protected void onDraw(Canvas canvas) {
    canvas.drawRoundRect(0,0,getWidth(),getHeight(),RAD,RAD,mPaint);
    mPaint.setColor(Color.BLACK);
    canvas.drawText(city,TEXT_PADDING,TEXT_PADDING+rect.bottom-rect.top,mPaint);
    //canvas.drawText(city,TEXT_PADDING,getHeight()/2-(rect.bottom+rect.top)*0.5f,mPaint);

  }
}
