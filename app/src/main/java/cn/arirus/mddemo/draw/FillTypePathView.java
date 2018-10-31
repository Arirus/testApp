package cn.arirus.mddemo.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class FillTypePathView extends View {
  Paint mPaint = new Paint();
  Path mPath = new Path();
  static final float EDGE = Utils.dp2px(50);
  {
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  public FillTypePathView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
    mPath.addCircle(getWidth()/2-EDGE,getHeight()/2,Utils.dp2px(100),Path.Direction.CCW);
    //mPath.addCircle(getWidth()/2+EDGE,getHeight()/2,Utils.dp2px(100),Path.Direction.CCW);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    int save = canvas.saveLayer(null,null);
    canvas.drawPath(mPath,mPaint);
    canvas.restoreToCount(save);
  }
}
