package cn.arirus.mddemo.draw;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import cn.arirus.mddemo.R;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class XfermodeView extends View {

  private Paint mPaint = new Paint();
  private RectF mRectF1 = new RectF();
  private RectF mRectF2 = new RectF();
  private Path mPath = new Path();
  private int index = 0;
  private PorterDuff.Mode[] array = new PorterDuff.Mode[] {
      PorterDuff.Mode.DST, PorterDuff.Mode.SRC, PorterDuff.Mode.DST_IN, PorterDuff.Mode.SRC_IN
  };

  public XfermodeView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    //Observable.interval(5, TimeUnit.SECONDS).subscribe(aLong -> {
    //  aLong += 1;
    //  index = (int) (aLong % (array.length));
    //  Log.i(ARIRUS, "CustomView: " + aLong + " " + index);
    //  postInvalidate();
    //});
  }

  @SuppressLint("DrawAllocation")
  @Override
  @TargetApi(21)
  protected void onDraw(Canvas canvas) {
    canvas.drawColor(Color.WHITE);
    //int layer = canvas.saveLayer(getLeft(), getTop(), getRight(), getBottom(), null);
    mPaint.setXfermode(null);
    canvas.save();
    canvas.rotate(90,getWidth()/2,getHeight()/2);
    canvas.drawBitmap(getRtBm(), getWidth() / 4, getHeight() / 4, mPaint);
    canvas.restore();

    //mPaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
    //canvas.drawRect(getWidth() / 2 - Utils.dp2px(100), getHeight() / 2 - Utils.dp2px(100),
    //    getWidth() / 2 + Utils.dp2px(100), getHeight() / 2 + Utils.dp2px(100), mPaint);

    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));

    canvas.drawBitmap(getOvBm(), getWidth() / 4, getHeight() / 4, mPaint);
    mPaint.setXfermode(null);

    //canvas.restoreToCount(layer);
  }

  //dst
  private Bitmap getOvBm() {
    Paint paint = new Paint();
    paint.setColor(getResources().getColor(R.color.colorAccent));

    mRectF1.left = getWidth() / 4 - Utils.dp2px(100);
    mRectF1.top = getHeight() / 4 - Utils.dp2px(100);
    mRectF1.right = getWidth() / 4 + Utils.dp2px(100);
    mRectF1.bottom = getHeight() / 4 + Utils.dp2px(100);
    Bitmap bitmap = Bitmap.createBitmap(getWidth() / 2, getHeight() / 2, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(getResources().getColor(android.R.color.holo_orange_dark));
    //canvas.drawOval(mRectF1, paint);
    //BitmapFactory.Options options = new BitmapFactory.Options();
    //options.outWidth = getWidth()/2;
    //options.outHeight = getHeight()/2;
    //
    //options.inTargetDensity = getWidth()/2;
    //return BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher1,options);
    return bitmap;
  }

  //src
  private Bitmap getRtBm() {
    Paint paint = new Paint();
    paint.setColor(getResources().getColor(R.color.colorPrimary));
    mRectF2.left = Utils.dp2px(50);
    mRectF2.top = Utils.dp2px(50);
    mRectF2.right = getWidth() / 2 - Utils.dp2px(50);
    mRectF2.bottom = getHeight() / 2 - Utils.dp2px(50);
    Bitmap bitmap = Bitmap.createBitmap(getWidth() / 2, getHeight() / 2, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(getResources().getColor(android.R.color.holo_blue_bright));
    //canvas.drawRect(mRectF2, paint);
    return bitmap;
  }
}
