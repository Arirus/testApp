package cn.arirus.mddemo.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SquareImageView extends View {

  public SquareImageView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
    int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

    setMeasuredDimension(Math.min(widthMeasureSize, heightMeasureSize),
        Math.min(widthMeasureSize, heightMeasureSize));
  }

  @Override protected void onDraw(Canvas canvas) {
    canvas.drawColor(Color.RED);
  }
}
