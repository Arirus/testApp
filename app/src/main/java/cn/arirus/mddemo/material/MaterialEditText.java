package cn.arirus.mddemo.material;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import cn.arirus.mddemo.R;
import cn.arirus.mddemo.draw.Utils;

public class MaterialEditText extends AppCompatEditText {

  @interface ShownST {
    int ToShown = 0;
    int Showing = 1;
    int ToHide = 2;
  }

  Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Rect hintRect = new Rect();
  float hintSize = Utils.sp2px(14);
  static int HINT_COLOR;
  float hintTop;
  float hintTopMin;
  float hintTopMax;
  float[] hintTops;

  float hintLeft;

  ObjectAnimator mObjectAnimator;

  @ShownST
  int showing = ShownST.ToHide;

  void init(Context context) {
    HINT_COLOR = ContextCompat.getColor(context, R.color.colorPrimary);
    mPaint.setTextSize(hintSize);
    mPaint.setColor(HINT_COLOR);
    mPaint.getTextBounds(getHint().toString(), 0, getHint().length(), hintRect);
    hintLeft = -hintRect.left + getTotalPaddingLeft() + Utils.dp2px(2);
    hintTop = mPaint.getFontMetrics(null);
    hintTops = new float[getHint().length()];
    hintTopMin = hintTop;
    mPaint.setTextSize(getTextSize());
    hintTopMax = hintTop+mPaint.getFontMetrics(null) + Utils.dp2px(4);
    setPadding(getTotalPaddingLeft(), getTotalPaddingTop() + (int) hintTop, getTotalPaddingRight(),
        getTotalPaddingBottom());

    PropertyValuesHolder valuesHolderTransY = PropertyValuesHolder.ofFloat("HintTop",
        hintTopMax);
    PropertyValuesHolder valuesHolderAlpha = PropertyValuesHolder.ofInt("HintAlpha", 0);
    PropertyValuesHolder valuesHolderSize =
        PropertyValuesHolder.ofFloat("HintSize", Utils.dp2px(14), getTextSize());
    mObjectAnimator =
        ObjectAnimator.ofPropertyValuesHolder(this, valuesHolderTransY, valuesHolderAlpha,
            valuesHolderSize);
    mObjectAnimator.setDuration(200);
    onShowChange(ShownST.ToHide);
  }

  public MaterialEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
    addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (showing == ShownST.Showing && s.length() == 0) {
          onShowChange(ShownST.ToHide);
        } else if (showing == ShownST.ToHide && s.length() > 0) {
          onShowChange(ShownST.ToShown);
        } else if (showing == ShownST.ToShown) {
          if (s.length() > 0) {
            onShowChange(ShownST.Showing);
          } else {
            onShowChange(ShownST.ToHide);
          }
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
  }

  private void onShowChange(@ShownST int showing) {
    this.showing = showing;
    if (ShownST.ToHide == showing) {
      mObjectAnimator.start();
    } else if (ShownST.ToShown == showing) {
      mObjectAnimator.reverse();
    }
  }

  public float getHintTop() {
    return hintTop;
  }

  public void setHintTop(float hintTop) {
    this.hintTop = hintTop;
    invalidate();
  }

  public float getHintLeft() {
    return hintLeft;
  }

  public void setHintLeft(float hintLeft) {
    this.hintLeft = hintLeft;
  }

  public int getHintAlpha() {
    return mPaint.getAlpha();
  }

  public void setHintAlpha(int hintAlpha) {
    mPaint.setAlpha(hintAlpha);
    invalidate();
  }

  public float getHintSize() {
    return hintSize;
  }

  public void setHintSize(float hintSize) {
    this.hintSize = hintSize;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPaint.setTextSize(hintSize);
    //for (int i = 0; i < getHint().length(); i++) {
    //  mPaint.getTextBounds(getHint().toString(),0,i,hintRect);
      canvas.drawText(getHint(), 0, getHint().length(), hintLeft, hintTop, mPaint);
    //}


  }
}
