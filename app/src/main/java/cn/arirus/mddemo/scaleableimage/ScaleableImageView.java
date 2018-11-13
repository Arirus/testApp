package cn.arirus.mddemo.scaleableimage;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;
import cn.arirus.mddemo.draw.Utils;
import utils.BitmapUtils;

public class ScaleableImageView extends View {

  private static final float IMG_WIDTH = Utils.dp2px(200);

  Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  GestureDetectorCompat mGestureDetectorCompat;
  ScaleGestureDetector mScaleGestureDetector;
  private Bitmap mBitmap;
  private ObjectAnimator mAnimator;
  private OverScroller mScroller;
  float scaleSmall;
  float scaleBig;
  float scale = 1f;
  float offsetX = 0f;
  float offsetY = 0f;
  boolean isBig = false;

  {
    mPaint.setStyle(Paint.Style.STROKE);
  }

  public ScaleableImageView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mGestureDetectorCompat = new GestureDetectorCompat(context, new CustomOnGestureListener());
    mScaleGestureDetector = new ScaleGestureDetector(context,new CustomOnScaleGestureListener());
    mScroller = new OverScroller(context);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    boolean result = mScaleGestureDetector.onTouchEvent(event);
    if (!mScroller.isFinished() || !mScaleGestureDetector.isInProgress())
      result = mGestureDetectorCompat.onTouchEvent(event);
    return result;
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mBitmap = BitmapUtils.getAvator(getContext(), IMG_WIDTH);

    scaleSmall = w > h ? h / IMG_WIDTH : w / IMG_WIDTH;
    scaleBig = w < h ? h / IMG_WIDTH : w / IMG_WIDTH;
    //scaleBig*=2;
  }

  ObjectAnimator getAnimator() {
    if (mAnimator == null) {
      PropertyValuesHolder scaleHolder = PropertyValuesHolder.ofFloat("scale", scaleBig);
      //PropertyValuesHolder offsetXHolder = PropertyValuesHolder.ofFloat("offsetX",0,offsetX);
      //PropertyValuesHolder offsetYHolder = PropertyValuesHolder.ofFloat("offsetY",0,offsetY);
      mAnimator = ObjectAnimator.ofPropertyValuesHolder(this, scaleHolder);
    }
    return mAnimator;
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
    invalidate();
  }

  public float getOffsetX() {
    return offsetX;
  }

  public void setOffsetX(float offsetX) {
    this.offsetX = offsetX;
  }

  public float getOffsetY() {
    return offsetY;
  }

  public void setOffsetY(float offsetY) {
    this.offsetY = offsetY;
  }

  @Override protected void onDraw(Canvas canvas) {

    float fraction = (scale -1) /(Math.max(scale,scaleBig)-1);

    canvas.translate(offsetX *fraction, offsetY*fraction);

    canvas.scale(scale, scale, getWidth()/2, getHeight()/2);
    canvas.drawBitmap(mBitmap, getWidth() / 2 - IMG_WIDTH / 2f, getHeight() / 2 - IMG_WIDTH / 2f,
        mPaint);
  }

  class CustomOnGestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override public boolean onDown(MotionEvent e) {
      return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      if (!isBig) return false;
      offsetX -= distanceX;
      offsetY -= distanceY;
      fixOffset(Math.max(scale,scaleBig));
      invalidate();
      return true;
    }



    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      if (!isBig) return false;
      mScroller.fling((int)offsetX, (int)offsetY, (int) velocityX, (int)velocityY,
          (int)(-IMG_WIDTH * Math.max(scale,scaleBig) / 2f + getWidth() / 2f), (int)(IMG_WIDTH * Math.max(scale,scaleBig) / 2f - getWidth() / 2f),
          (int)(-IMG_WIDTH * Math.max(scale,scaleBig) / 2f + getHeight() / 2f),(int)(IMG_WIDTH * Math.max(scale,scaleBig) / 2f - getHeight() / 2f)
      );

      return true;
    }

    @Override public boolean onDoubleTap(MotionEvent e) {
      if (!isBig) {
        offsetX = e.getX()-getWidth()/2 - (e.getX()-getWidth()/2)*scaleBig;
        offsetY = e.getY()-getHeight()/2 - (e.getY()-getHeight()/2)*scaleBig;
        fixOffset(scaleBig);
        getAnimator().start();
      } else {
        getAnimator().reverse();
        //offsetY = offsetX = 0;
      }
      isBig = !isBig;
      return true;
    }
  }

  class CustomOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
    PointF mPointF = new PointF();

    @Override public boolean onScale(ScaleGestureDetector detector) {
      Log.i("CustomOnScaleGestur", "Arirus onScale: "+scale * detector.getScaleFactor());
      if (scale * detector.getScaleFactor()<scaleBig) return false;
      scale *= detector.getScaleFactor();
      offsetX = mPointF.x-getWidth()/2 - (mPointF.x-getWidth()/2)*scale;
      offsetY = mPointF.y-getHeight()/2 - (mPointF.y-getHeight()/2)*scale;
      fixOffset(Math.max(scale,scaleBig));
      invalidate();
      return true;
    }

    @Override public boolean onScaleBegin(ScaleGestureDetector detector) {
      if (scale * detector.getScaleFactor()<scaleBig) return false;
      mPointF.set(detector.getFocusX(),detector.getFocusY());
      return super.onScaleBegin(detector);
    }
  }

  private void fixOffset(float scale) {
    Log.i("ScaleableImageView", "Arirus fixOffset: "+offsetX+ " "+ (-IMG_WIDTH * scale / 2f + getWidth() / 2f)+" "+ (IMG_WIDTH * scale / 2f - getWidth() / 2f));
    offsetX = Math.min(offsetX, IMG_WIDTH * scale / 2f - getWidth() / 2f);
    offsetX = Math.max(offsetX, -IMG_WIDTH * scale / 2f + getWidth() / 2f);

    offsetY = Math.min(offsetY, IMG_WIDTH * scale / 2f - getHeight() / 2f);
    offsetY = Math.max(offsetY, -IMG_WIDTH * scale / 2f + getHeight() / 2f);
  }

  @Override public void computeScroll() {
    if (!mScroller.computeScrollOffset()) return;
    offsetX = mScroller.getCurrX();
    offsetY = mScroller.getCurrY();
    invalidate();
  }
}
