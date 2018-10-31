package cn.arirus.mddemo.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PieChart extends View {
  private Paint mPaint;
  private RectF mRectF;
  private float mStartDegree;
  private float MAX_TRANS_LENGTH = Utils.dp2px(20);
  private static final float EDGE = Utils.dp2px(20);
  private int[] mColors = new int[]{android.R.color.black,
      android.R.color.holo_red_dark,android.R.color.holo_blue_dark,
      android.R.color.holo_orange_dark,android.R.color.holo_green_dark
  };
  private float[] mDegrees = new float[]{
      30f,60f,30f,75f,165f
  };

  {
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.FILL);
    mRectF = new RectF();
    mStartDegree =0;
  }


  public PieChart(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    if (h>w)
    mRectF.set(EDGE, (h - w) * 0.5f + EDGE, w - EDGE, (h + w) * 0.5f - EDGE);
    else
      mRectF.set((w-h) * 0.5f + EDGE,EDGE, (h + w) * 0.5f - EDGE,  h - EDGE);

  }

  @Override
  protected void onDraw(Canvas canvas) {
    for (int i = 0; i < mColors.length; i++) {
      mPaint.setColor(getResources().getColor(mColors[i]));
      canvas.save();
      if (i == 3) canvas.translate((float) (MAX_TRANS_LENGTH*Math.cos(Math.toRadians(mStartDegree+mDegrees[i]/2)))
      ,(float) (MAX_TRANS_LENGTH*Math.sin(Math.toRadians(mStartDegree+mDegrees[i]/2))));
      canvas.drawArc(mRectF,mStartDegree,mDegrees[i],true,mPaint);

      //if (i == 3) canvas.translate(-(float) (MAX_TRANS_LENGTH*Math.cos(Math.toRadians(mStartDegree+mDegrees[i]/2)))
      //    ,-(float) (MAX_TRANS_LENGTH*Math.sin(Math.toRadians(mStartDegree+mDegrees[i]/2))));
      canvas.restore();
      mStartDegree+=mDegrees[i];
    }
  }
}
