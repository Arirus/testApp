package cn.arirus.mddemo.draw.text;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.arirus.mddemo.R;
import cn.arirus.mddemo.draw.Utils;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class CenterLeftTextView extends View {
  Paint mPaint = new Paint();
  Paint mTxtPaint = new Paint();
  Paint mTxtPaint1 = new Paint();
  Path mPath = new Path();
  Rect mRect = new Rect();
  Paint.FontMetrics mFontMetrics = null;
  static final float EDGE = Utils.dp2px(20);
  static final String TXT_CONTENT = "alplplp";
  StringBuilder mStringBuilder = new StringBuilder();

  public CenterLeftTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    Observable.intervalRange(0,6,0,1,TimeUnit.SECONDS)
        .subscribe(aLong ->{
          mStringBuilder.append(TXT_CONTENT.charAt(aLong.intValue()));
          postInvalidate();
        } );

  }

  {
    mPaint.setAntiAlias(true);
    mPaint.setStrokeWidth(Utils.dp2px(20));
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));

    mTxtPaint.setAntiAlias(true);
    mTxtPaint.setColor(getResources().getColor(R.color.colorAccent));
    mTxtPaint.setTextSize(Utils.sp2px(60));
    mTxtPaint.setTextAlign(Paint.Align.CENTER);
    mTxtPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"segoesc.ttf"));
    mTxtPaint1.setAntiAlias(true);
    mTxtPaint1.setColor(getResources().getColor(R.color.colorAccent));
    mTxtPaint1.setTextSize(Utils.sp2px(10));
    mTxtPaint1.setTextAlign(Paint.Align.LEFT);
    mTxtPaint1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"segoesc.ttf"));

  }

  @Override
  @TargetApi(21)
  protected void onDraw(Canvas canvas) {
    canvas.save();
    mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2 - EDGE, mPaint);
    mPaint.setColor(getResources().getColor(R.color.colorAccent));
    canvas.drawArc((getWidth() - getHeight()) * 0.5f + EDGE, EDGE, (getHeight() + getWidth()) * 0.5f - EDGE,
        getHeight() - EDGE,-90,120,false,mPaint);
    canvas.drawLine(getWidth()/2,EDGE,getWidth()/2,getHeight()-EDGE,mTxtPaint);
    canvas.drawLine((getWidth()-getHeight())/2+EDGE,getHeight()/2,(getHeight()+getWidth())/2-EDGE,getHeight()/2,mTxtPaint);

    mTxtPaint.getTextBounds(mStringBuilder.toString(),0,mStringBuilder.toString().length(),mRect);
    canvas.drawText(mStringBuilder.toString(),getWidth()/2-mRect.left,getHeight()/2-(mRect.bottom+mRect.top)*0.5f,mTxtPaint);
    canvas.restore();



    //canvas.save();
    //canvas.translate(0,EDGE*2);
    //mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
    //canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - EDGE, mPaint);
    //mPaint.setColor(getResources().getColor(R.color.colorAccent));
    //canvas.drawArc(EDGE, (getHeight() - getWidth()) * 0.5f + EDGE, getWidth() - EDGE,
    //    (getHeight() + getWidth()) * 0.5f - EDGE,-90,120,false,mPaint);
    //canvas.drawLine(EDGE,getHeight()/2,getWidth()-EDGE,getHeight()/2,mTxtPaint1);
    //canvas.drawLine(getWidth()/2,(getHeight()-getWidth())/2+EDGE,getWidth()/2,(getHeight()+getWidth())/2-EDGE,mTxtPaint1);
    //
    //mFontMetrics = mTxtPaint1.getFontMetrics();
    //canvas.drawText(mStringBuilder.toString(),getWidth()/2,getHeight()/2-(mFontMetrics.descent+mFontMetrics.ascent)*0.5f,mTxtPaint1);
    //canvas.restore();
  }
}
