package cn.arirus.mddemo.draw.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import cn.arirus.mddemo.R;
import cn.arirus.mddemo.draw.Utils;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class BreakLineTextView extends View {
  Paint mPaint = new Paint();
  TextPaint mTextPaint = new TextPaint();
  RectF mRectF = new RectF();
  static final float RECT_EDGE = Utils.dp2px(50);
  float RoundRadius = Utils.dp2px(30);
  float txtLeftEdage = Utils.dp2px(10);
  float txtTopEdage = 0;
  float lineHeigh = 0;
  private static final String TXT_CONTENT = "AAAAAA"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "the font's recommended interline spacing the font's recommended interline spacing"
      + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
      + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa";

  {
    mTextPaint.setTextSize(Utils.sp2px(18));
    mTextPaint.setAntiAlias(true);
    mTextPaint.setColor(getResources().getColor(R.color.colorAccent));

    lineHeigh = mTextPaint.getFontMetrics(null);
  }

  public BreakLineTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (h>w)
    mRectF.set(getWidth() / 2 - RECT_EDGE, (getHeight() - getWidth()) / 2 + RECT_EDGE,
        getWidth() / 2 + RECT_EDGE, (getHeight() + getWidth()) / 2 - RECT_EDGE);
    else
      mRectF.set((getWidth() - getHeight()) / 2 + RECT_EDGE, getHeight()/2 - RECT_EDGE,
          (getHeight() + getWidth()) / 2 - RECT_EDGE, getHeight()/2 + RECT_EDGE);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    //StaticLayout staticLayout =
    //    StaticLayout.Builder.obtain(TXT_CONTENT, 0, TXT_CONTENT.length(), mTextPaint, getWidth())
    //        .build();

    canvas.drawRoundRect(mRectF, RoundRadius, RoundRadius, mPaint);

    //canvas.drawText(TXT_CONTENT,0,0,mTextPaint);

    //canvas.drawRect(mRectF,mPaint);
    //
    int curIndex = 0;
    int lastIndex = 0;
    txtTopEdage = mTextPaint.getFontMetrics().top;
    float curLinHeigh = 0;
    float curLinBaseLine = -txtTopEdage;
    float maxLinWidth = getWidth() - txtLeftEdage * 2;
    boolean isLeft = false;
    boolean isWholeLine = true;
    float curHeigh = mTextPaint.getFontMetrics().descent - mTextPaint.getFontMetrics().ascent;
    while (curIndex < TXT_CONTENT.length()) {
      if ((curLinHeigh > mRectF.top && curLinHeigh < mRectF.bottom) || (curLinBaseLine > mRectF.top
          && curLinBaseLine < mRectF.bottom)) {
        maxLinWidth = mRectF.left - txtLeftEdage;
        isWholeLine = false;
        isLeft = !isLeft;
      } else {
        maxLinWidth = getWidth() - txtLeftEdage * 2;
        isWholeLine = true;
      }

      lastIndex +=
          (mTextPaint.breakText(TXT_CONTENT, curIndex, TXT_CONTENT.length(), true, maxLinWidth,
              null));
      Log.i(ARIRUS, "onDraw: " + curLinHeigh);
      if (isLeft || isWholeLine) {
        canvas.drawText(TXT_CONTENT, curIndex, lastIndex, txtLeftEdage, curLinBaseLine, mTextPaint);
      } else {
        canvas.drawText(TXT_CONTENT, curIndex, lastIndex, mRectF.right, curLinBaseLine, mTextPaint);
      }
      if (isWholeLine || !isLeft) {
        curLinHeigh += lineHeigh;
        curLinBaseLine += lineHeigh;
      }
      curIndex = lastIndex;
    }
  }
}
