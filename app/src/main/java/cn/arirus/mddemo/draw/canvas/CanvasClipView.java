package cn.arirus.mddemo.draw.canvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import cn.arirus.mddemo.R;
import cn.arirus.mddemo.draw.Utils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.concurrent.TimeUnit;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class CanvasClipView extends View {
  Paint mPaint = new Paint();
  RectF mRectF = new RectF();

  Matrix mMatrix = new Matrix();
  Camera mCamera = new Camera();
  static final float EDGE = Utils.dp2px(50);
  static final float RADIUS = Utils.dp2px(200);

  float rotDeg = 0;

  int step = 0;

  {
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setStrokeWidth(Utils.dp2px(5));
    mPaint.setStrokeCap(Paint.Cap.ROUND);
  }

  public CanvasClipView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    Observable.intervalRange(0, 3600, 1, 10, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(aLong -> {
          rotDeg = aLong % 360;
          Log.i(ARIRUS, "CanvasClipView: " + rotDeg);
          invalidate();
        });
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    //mRectF.set(EDGE,(getHeight()-getWidth())/2+EDGE,getWidth()-EDGE,(getHeight()+getWidth())/2-EDGE);
    mRectF.set(getWidth() / 2 - 2 * EDGE, (getHeight() - getWidth()) / 2 + 2 * EDGE,
        getWidth() / 2 + 2 * EDGE, (getHeight() + getWidth()) / 2 - 2 * EDGE);

    //mRectF.set(0,0,EDGE*2,EDGE*2);
  }

  static int i = 0;

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    mCamera.save();
    mCamera.rotateX(20);
    mCamera.setLocation(0, 0, -8 * Resources.getSystem().getDisplayMetrics().density);

    //canvas.save();
    //canvas.rotate(rotDeg,getWidth()/2,getHeight()/2);
    ////canvas.translate(getWidth()/2,getHeight()/2);
    //canvas.clipRect(0,0,getWidth(),getHeight()/2);
    ////canvas.translate(-getWidth()/2,-getHeight()/2);
    //canvas.rotate(-rotDeg,getWidth()/2,getHeight()/2);
    //canvas.drawBitmap(getAvatar(),mRectF.left,mRectF.top, mPaint);
    //canvas.restore();

    canvas.translate(getWidth()/2,getHeight()/2);
    canvas.rotate(rotDeg);
    mCamera.applyToCanvas(canvas);
    //canvas.clipRect(0,getHeight()/2,getWidth(),getHeight());
    canvas.clipRect(-getWidth()/2,0,getWidth()/2,getHeight()/2);
    canvas.rotate(-rotDeg);
    canvas.translate(-getWidth()/2,-getHeight()/2);
    //canvas.drawRect(mRectF,mPaint);
    canvas.drawBitmap(getAvatar(),mRectF.left,mRectF.top, mPaint);
    //
    //canvas.drawPoint(getWidth()/2,getHeight()/2+EDGE*4,mPaint);

    //canvas.translate(-EDGE,-EDGE);
    //canvas.scale(1f,2f,EDGE,EDGE);
    //canvas.translate(EDGE,EDGE*3);
    //canvas.drawRect(mRectF,mPaint);

    //mMatrix.postScale(1.2f,1.2f,EDGE*1.5f,EDGE*1.5f);
    //mMatrix.postTranslate(100,100);

    //mCamera.save();
    ////mCamera.translate(EDGE*2f,-EDGE*2f,0);
    ////mCamera.translate(-EDGE * 1.5f, EDGE * 1.5f, 0);
    //
    //Log.i(ARIRUS, "onDraw: " + i);
    //i++;
    //
    ////mMatrix.preScale(1.2f,1.2f,EDGE*1.5f,EDGE*1.5f);
    ////if (step == 0) {
    ////  canvas.save();
    ////  canvas.drawRect(mRectF,mPaint);
    ////  canvas.restore();
    ////} else if (step == 1){
    ////  canvas.save();
    ////  canvas.translate(EDGE*1.5f,EDGE*1.5f);
    ////  canvas.drawRect(mRectF,mPaint);
    ////  canvas.restore();
    ////} else if (step == 2){
    ////  canvas.save();
    ////  canvas.translate(EDGE*1.5f,EDGE*1.5f);
    ////  canvas.rotate(45);
    ////  canvas.drawRect(mRectF,mPaint);
    ////  canvas.restore();
    ////} else if (step == 3){
    ////  canvas.save();
    ////  canvas.translate(EDGE*1.5f,EDGE*1.5f);
    ////  canvas.rotate(45);
    ////  canvas.translate(-EDGE*1.5f,-EDGE*1.5f);
    ////  canvas.drawRect(mRectF,mPaint);
    ////  canvas.restore();
    ////}
    //
    //canvas.save();
    ////canvas.rotate(45,EDGE*1.5f+getWidth()/2,EDGE*1.5f+getHeight()/2);
    ////canvas.scale(0.5f,2f,EDGE*1.5f+getWidth()/2,EDGE*1.5f+getHeight()/2);
    ////canvas.scale(1.2f,1.2f,EDGE*1.5f,EDGE*1.5f);
    ////canvas.translate(100,100);
    //
    ////canvas.concat(mMatrix);
    ////canvas.translate(EDGE*1.5f,EDGE*1.5f);
    ////mCamera.translate(EDGE*1.5f,-EDGE*1.5f,0);
    //
    //mCamera.applyToCanvas(canvas);
    //
    ////canvas.translate(-EDGE*1.5f,-EDGE*1.5f);
    //
    //canvas.drawBitmap(getBitmap(mRectF),mRectF.left,mRectF.top,mPaint);
    //
    ////canvas.drawRect(mRectF, mPaint);
    //
    //canvas.restore();
    //mCamera.restore();

    //
    //canvas.save();
    //canvas.rotate(rotDeg,getWidth()/2,getHeight()/2);
    //canvas.clipRect(mRectF.left,mRectF.top,mRectF.right,getHeight()/2);
    //canvas.rotate(-rotDeg,getWidth()/2,getHeight()/2);
    //canvas.drawBitmap(getBitmap(mRectF),mRectF.left,mRectF.top,mPaint);
    //canvas.restore();
    //
    //canvas.save();
    //canvas.rotate(rotDeg,getWidth()/2,getHeight()/2);
    //canvas.clipRect(mRectF.left,getHeight()/2,mRectF.right,mRectF.bottom);
    //canvas.rotate(-rotDeg,getWidth()/2,getHeight()/2);
    //canvas.drawBitmap(getBitmap(mRectF),mRectF.left,mRectF.top,mPaint);
    //canvas.restore();

    //// 绘制上半部分
    //canvas.save();
    //canvas.translate(getWidth()/2,getHeight()/2);
    //canvas.rotate(-rotDeg,0,0);
    //canvas.clipRect(-getWidth()/2,-getHeight()/2, getWidth()/2,0);
    //canvas.rotate(rotDeg,0,0);
    //canvas.translate(-getWidth()/2,-getHeight()/2);
    //canvas.drawBitmap(getBitmap(mRectF), mRectF.left,mRectF.top,mPaint);
    //canvas.restore();
    //
    //// 绘制下半部分
    //canvas.save();
    //canvas.translate(getWidth()/2,getHeight()/2);
    //canvas.rotate(-rotDeg,0,0);
    //canvas.clipRect(-getWidth()/2,0, getWidth()/2,getHeight()/2);
    //mCamera.applyToCanvas(canvas);
    //canvas.rotate(rotDeg,0,0);
    //canvas.translate(-getWidth()/2,-getHeight()/2);
    //canvas.drawBitmap(getBitmap(mRectF), mRectF.left,mRectF.top, mPaint);
    //canvas.restore();

    mCamera.restore();
    //canvas.save();
    //
    //mCamera.save();
    //
    //
    //canvas.rotate(-rotDeg,getWidth()/2,getHeight()/2);
    //canvas.translate(getWidth()/2,getHeight()/2);
    //
    //mCamera.applyToCanvas(canvas);
    //
    //
    //canvas.translate(-getWidth()/2,-getHeight()/2);
    //canvas.rotate(rotDeg,getWidth()/2,getHeight()/2);
    //
    //mCamera.restore();
    //canvas.clipRect(mRectF.left,getHeight()/2,mRectF.right,mRectF.bottom);
    //canvas.drawBitmap(getBitmap(mRectF),mRectF.left,mRectF.top,mPaint);
    //canvas.restore();

  }

  public Bitmap getAvatar() {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
    options.inJustDecodeBounds = false;
    options.inDensity = options.outWidth;
    options.inTargetDensity = 600;
    return BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
  }

  private Bitmap getBitmap(RectF rectF) {
    Bitmap bitmap =
        Bitmap.createBitmap((int) (rectF.right - rectF.left), (int) (rectF.bottom - rectF.top),
            Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(bitmap);
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inDensity = options.outWidth;
    options.inTargetDensity = (int) (rectF.right - rectF.left);
    options.inSampleSize = 2;
    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.w, options), 0, -170,
        null);

    return bitmap;
  }
}
