package cn.arirus.mddemo.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.arirus.mddemo.draw.Utils;

public class CircleView extends View {
  final static float PADDING = Utils.dp2px(50);
  final static float RADIUS = Utils.dp2px(100);
  Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  {
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.FILL);
  }

  public CircleView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = (int) ((PADDING + RADIUS) * 2);
    int height = (int) ((PADDING + RADIUS) * 2);
    //setMeasuredDimension(resolveSize(width, widthMeasureSpec),
    //    resolveSize(height, heightMeasureSpec));

    setMeasuredDimension((width),
        (height));

  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawCircle(getWidth() / 2, getHeight() / 2,
        Math.min(getWidth() / 2, getHeight() / 2) - PADDING, mPaint);
  }
}
