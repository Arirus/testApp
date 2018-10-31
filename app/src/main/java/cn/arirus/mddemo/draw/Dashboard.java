package cn.arirus.mddemo.draw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;

public class Dashboard extends View {

  private Paint mPaint = new Paint();
  private static final float START_ANGLE = 150;
  private static final float SWEEP_ANGLE = 240;
  private RectF mRectF = new RectF();
  private static final float EDGE = Utils.dp2px(20);
  private static final Path mPath = new Path();
  private static final Path mMarkPath = new Path();
  private float mArcLength = 0;
  private int MAX_MARK = 20;
  private double CUR_MARK = 0d;
  private float MARK_WIDTH = Utils.dp2px(1);
  private float MARK_HEIGH = Utils.dp2px(10);
  private float MARK_HANDS_LENGTH = Utils.dp2px(60);

  {
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(Utils.dp2px(1));
    mMarkPath.addRect(0, 0, MARK_WIDTH, MARK_HEIGH, Path.Direction.CCW);
  }

  @SuppressLint("CheckResult")
  public Dashboard(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    Observable.intervalRange(1, 20, 1, 2, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(aLong -> {
          CUR_MARK++;
          invalidate();
        });
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (h>w)
    mRectF.set(EDGE, (h - w) * 0.5f + EDGE, w - EDGE, (h + w) * 0.5f - EDGE);
    else
      mRectF.set((w - h) * 0.5f + EDGE,EDGE,(h + w) * 0.5f - EDGE,h-EDGE);
    mPath.addArc(mRectF, START_ANGLE, SWEEP_ANGLE);
    PathMeasure pathMeasure = new PathMeasure(mPath, false);
    mArcLength = pathMeasure.getLength();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    //canvas.drawArc(mRectF,START_ANGLE,SWEEP_ANGLE,false,mPaint);
    mPaint.setPathEffect(null);
    canvas.drawPath(mPath, mPaint);

    mPaint.setPathEffect(new PathDashPathEffect(mMarkPath, (mArcLength - MARK_WIDTH) / MAX_MARK, 0,
        PathDashPathEffect.Style.ROTATE));
    ////canvas.drawLine();
    canvas.drawPath(mPath, mPaint);

    canvas.save();
    canvas.rotate(START_ANGLE, getWidth() / 2, getHeight() / 2);

    canvas.drawLine(getWidth() / 2, getHeight() / 2,
        getWidth() / 2 + (float) (MARK_HANDS_LENGTH * Math.cos(
            Math.toRadians(SWEEP_ANGLE/MAX_MARK*CUR_MARK))),
        getHeight() / 2 + (float) (MARK_HANDS_LENGTH * Math.sin(
            Math.toRadians(SWEEP_ANGLE/MAX_MARK*CUR_MARK))), mPaint);
    canvas.restore();
  }


}
