package cn.arirus.mddemo.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.arirus.mddemo.draw.Utils;

public class AnimCircleView extends View {
  PointF ARC_POINT;
  PointF mDstPoint;
  Paint mPaint;
  final static float EDGE =Utils.dp2px(10);

  {
    ARC_POINT = new PointF(EDGE,EDGE);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(Utils.dp2px(2));
    mDstPoint = new PointF(EDGE*2,EDGE*2);
  }

  public AnimCircleView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public PointF getDstPoint() {
    return mDstPoint;
  }

  public void setDstPoint(PointF dstPoint) {
    mDstPoint = dstPoint;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    float rad = (mDstPoint.length()-ARC_POINT.length())/2;

    float x = (ARC_POINT.x+mDstPoint.x)/2;
    float y = (ARC_POINT.y+mDstPoint.y)/2;

    canvas.drawCircle(x,y,rad,mPaint);
    canvas.drawCircle(ARC_POINT.x,ARC_POINT.y,Utils.dp2px(2),mPaint);
    canvas.drawCircle(mDstPoint.x,mDstPoint.y,Utils.dp2px(2),mPaint);
  }
}
