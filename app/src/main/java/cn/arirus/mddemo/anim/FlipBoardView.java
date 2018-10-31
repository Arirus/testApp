package cn.arirus.mddemo.anim;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.arirus.mddemo.R;
import cn.arirus.mddemo.draw.Utils;

public class FlipBoardView extends View {
  final static float EDGE = Utils.dp2px(100);
  RectF mRectF = new RectF();
  RectF mClipRectL = new RectF();
  RectF mClipRectR = new RectF();
  Camera mCameraR = new Camera();
  Camera mCameraL = new Camera();

  float leftDeg = 0;
  float rightDeg = 0;
  float rotDeg = 0;

  Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  {
    mCameraR.rotateY(-rightDeg);
    mCameraL.rotateY(-leftDeg);
    mCameraR.setLocation(0,0,-4*Resources.getSystem().getDisplayMetrics().density);
    mCameraL.setLocation(0,0,-4*Resources.getSystem().getDisplayMetrics().density);
  }


  public FlipBoardView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mRectF.set(getWidth() / 2f - EDGE, (getHeight()) / 2f - EDGE, getWidth() / 2f + EDGE,
        (getHeight()) / 2f + EDGE);

    mClipRectL.set((float) ( - EDGE * 2),
        (float) ( - EDGE * 2), 0,
        (float) ( EDGE * 2));

    mClipRectR.set(0, (float) ( - EDGE * 2),
        (float) ( EDGE * 2),
        (float) ( EDGE *2));
    }

  public float getLeftDeg() {
    return leftDeg;
  }

  public void setLeftDeg(float leftDeg) {
    this.leftDeg = leftDeg;
    invalidate();
  }

  public float getRightDeg() {
    return rightDeg;
  }

  public void setRightDeg(float rightDeg) {
    this.rightDeg = rightDeg;
    invalidate();
  }

  public float getRotDeg() {
    return rotDeg;
  }

  public void setRotDeg(float rotDeg) {
    this.rotDeg = rotDeg;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    //canvas.save();
    //canvas.clipRect(mClipRectL);
    //canvas.drawBitmap(getAvatar(),mRectF.left,mRectF.top,mPaint);
    //canvas.restore();
    mCameraL.save();
    canvas.save();
    canvas.translate(getWidth()/2,getHeight()/2);
    canvas.rotate(-rotDeg);
    canvas.clipRect(mClipRectL);
    mCameraL.rotateY(-leftDeg);
    mCameraL.applyToCanvas(canvas);
    canvas.rotate(rotDeg);
    canvas.translate(-getWidth()/2,-getHeight()/2);
    canvas.drawBitmap(getAvatar(),mRectF.left,mRectF.top,mPaint);
    canvas.restore();
    mCameraL.restore();


    mCameraR.save();
    canvas.save();
    canvas.translate(getWidth()/2,getHeight()/2);
    canvas.rotate(-rotDeg);
    canvas.clipRect(mClipRectR);
    mCameraR.rotateY(-rightDeg);
    mCameraR.applyToCanvas(canvas);
    canvas.rotate(rotDeg);
    canvas.translate(-getWidth()/2,-getHeight()/2);
    canvas.drawBitmap(getAvatar(),mRectF.left,mRectF.top,mPaint);
    canvas.restore();
    mCameraR.restore();

  }

  public Bitmap getAvatar() {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
    options.inJustDecodeBounds = false;
    options.inDensity = options.outWidth;
    options.inTargetDensity = 500;
    return BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
  }
}
