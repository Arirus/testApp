package cn.arirus.mddemo.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.arirus.mddemo.R;

import static android.graphics.Canvas.ALL_SAVE_FLAG;
import static android.graphics.Shader.TileMode.REPEAT;

public class Avatvar extends View {
  private Paint mPaint;
  private RectF mRectF;
  private static final float EDGE = Utils.dp2px(50);
  private static final float WIDTH = Utils.dp2px(5);

  {
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.FILL);
    mRectF = new RectF();
  }

  public Avatvar(Context context, @Nullable AttributeSet attrs) {
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
    super.onDraw(canvas);

    mPaint.setShader(new RadialGradient(getWidth()/2,getHeight()/2,getWidth()/2,new int[]{
        getResources().getColor(R.color.colorAccent),
        getResources().getColor(R.color.colorPrimaryDark),
        getResources().getColor(R.color.colorPrimary)
    },null,REPEAT));

    canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,mPaint);

    int layer = canvas.saveLayer(null,null,ALL_SAVE_FLAG);

    canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2-WIDTH,mPaint);
    Bitmap bitmap = getBitmap();
    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(bitmap,EDGE,(getHeight()*0.5f-getWidth()*0.5f),mPaint);


    canvas.restoreToCount(layer);
  }

  private Bitmap getBitmap(){
    Bitmap bitmap = Bitmap.createBitmap((int)(getWidth()-2*EDGE),(int)(getWidth()-2*EDGE),Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);

    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inSampleSize =2;

    Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.w,options);

    canvas.drawBitmap(tmp,0,0,mPaint);

    return bitmap;
  }

}
