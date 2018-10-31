package cn.arirus.mddemo.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class QuadPathView extends View {
  Paint mPaint = new Paint();
  Path mPath = new Path();
  Point mPoint = new Point();
  static final float EDGE = Utils.dp2px(50);

  public QuadPathView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }
  {
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setAntiAlias(true);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mPoint.set(getWidth()/2,getHeight()/2);
    mPath.moveTo(EDGE,getHeight()/2);

  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    mPoint.set((int) event.getX(),(int)event.getY());
    if ((event.getAction() & (MotionEvent.ACTION_MOVE | MotionEvent.ACTION_DOWN))!=0)
      invalidate();
    return true;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPath.reset();
    mPath.moveTo(EDGE,getHeight()/2);
    mPath.quadTo(mPoint.x,mPoint.y,getWidth() - EDGE,getHeight()/2);
    canvas.drawPath(mPath,mPaint);

  }
}
