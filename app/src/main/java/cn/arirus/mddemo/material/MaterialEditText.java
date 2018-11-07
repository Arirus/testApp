package cn.arirus.mddemo.material;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
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
import android.util.Log;
import cn.arirus.mddemo.R;
import cn.arirus.mddemo.draw.Utils;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

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

  float hintLine;
  float hintTop;
  float hintBottom;
  float hintEdgeMin;
  float hintEdgeMax;

  Path mPath = new Path();
  float pathLength;
  float[] mpos;

  PathMeasure mPathMeasure = new PathMeasure();

  float hintLeft;

  ObjectAnimator mObjectAnimatorTop;
  ObjectAnimator mObjectAnimatorBottom;

  ObjectAnimator mObjectAnimatorTextSieze;
  ObjectAnimator mObjectAnimatorAlpha;

  AnimatorSet mSet;
  AnimatorSet mSetReverse;

  @ShownST
  int showing = ShownST.ToHide;

  void init(Context context) {
    HINT_COLOR = ContextCompat.getColor(context, R.color.colorPrimary);
    mPaint.setTextSize(hintSize);
    mPaint.setColor(HINT_COLOR);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.getTextBounds(getHint().toString(), 0, getHint().length(), hintRect);
    pathLength = hintRect.right - hintRect.left;
    hintLeft = -hintRect.left + getTotalPaddingLeft();
    hintTop = mPaint.getFontMetrics(null);
    hintBottom = mPaint.getFontMetrics(null);
    mpos = new float[getHint().length() * 2];
    hintEdgeMin = hintTop;
    mPaint.setTextSize(getTextSize());
    hintEdgeMax = hintTop + mPaint.getFontMetrics(null) + Utils.dp2px(4);
    setPadding(getTotalPaddingLeft(), getTotalPaddingTop() + (int) hintTop, getTotalPaddingRight(),
        getTotalPaddingBottom());

    mSet = new AnimatorSet();
    mSetReverse = new AnimatorSet();

    PropertyValuesHolder valuesHolderTransTop =
        PropertyValuesHolder.ofFloat("HintTop", hintEdgeMin, hintEdgeMax);
    PropertyValuesHolder valuesHolderTransBottom =
        PropertyValuesHolder.ofFloat("HintBottom", hintEdgeMin, hintEdgeMax);

    mObjectAnimatorTop = ObjectAnimator.ofPropertyValuesHolder(this, valuesHolderTransTop);

    mObjectAnimatorBottom = ObjectAnimator.ofPropertyValuesHolder(this, valuesHolderTransBottom);

    mObjectAnimatorTop.setDuration(100);
    mObjectAnimatorBottom.setDuration(100);

    mObjectAnimatorTextSieze = ObjectAnimator.ofFloat(this, "HintSize", getTextSize());
    mObjectAnimatorAlpha = ObjectAnimator.ofInt(this, "HintAlpha", 0);
    mObjectAnimatorTextSieze.setDuration(200);
    mObjectAnimatorAlpha.setDuration(200);

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

  //@TargetApi(26)
  private void onShowChange(@ShownST int showing) {
    this.showing = showing;
    if (ShownST.ToHide == showing) {
      mSet.cancel();
      mObjectAnimatorTop.removeAllListeners();
      mSetReverse.playSequentially(mObjectAnimatorTop, mObjectAnimatorBottom);
      mObjectAnimatorTextSieze.start();
      mSetReverse.start();
      mObjectAnimatorAlpha.start();
    } else if (ShownST.ToShown == showing) {
      mSetReverse.cancel();
      //26一下这么写
      mObjectAnimatorTop.addListener(new Animator.AnimatorListener() {
        @Override public void onAnimationStart(Animator animation) {
        }

        @Override public void onAnimationEnd(Animator animation) {
          mObjectAnimatorBottom.reverse();

        }

        @Override public void onAnimationCancel(Animator animation) {

        }

        @Override public void onAnimationRepeat(Animator animation) {

        }
      });
      mObjectAnimatorTop.reverse();
      //26以上这么写
      //mSet.playSequentially(mObjectAnimatorBottom, mObjectAnimatorTop);
      //mSet.reverse();
      mObjectAnimatorTextSieze.reverse();
      mObjectAnimatorAlpha.reverse();
    }
  }

  public float getHintBottom() {
    return hintBottom;
  }

  @TargetApi(21)
  public void setHintBottom(float hintBottom) {
    Log.i(ARIRUS, "setHintTop: " + hintTop + " " + hintBottom);

    this.hintBottom = hintBottom;
    mPath.reset();
    if (hintTop <= hintBottom) {
      mPath.addArc(hintLeft - pathLength-Utils.dp2px(2), hintTop, hintLeft + pathLength+Utils.dp2px(2),
          hintBottom + Utils.dp2px(1) + (hintBottom - hintTop), 270, 90);
    } else {
      mPath.addArc(hintLeft ,  hintBottom,
          hintLeft + 2*pathLength+2*Utils.dp2px(2), hintTop*2 + Utils.dp2px(1)-hintBottom, -180, 90);
    }

    invalidate();
  }

  public float getHintTop() {
    return hintTop;
  }

  @TargetApi(21)
  public void setHintTop(float hintTop) {
    Log.i(ARIRUS, "setHintTop: " + hintTop + " " + hintBottom);
    this.hintTop = hintTop;
    mPath.reset();

    if (hintTop <= hintBottom) {
      mPath.addArc(hintLeft - pathLength-Utils.dp2px(2), hintTop, hintLeft + pathLength+Utils.dp2px(2),
          hintBottom + Utils.dp2px(1) + (hintBottom - hintTop), 270, 90);
    } else {
      mPath.addArc(hintLeft ,  hintBottom,
          hintLeft + 2*pathLength+2*Utils.dp2px(2), hintTop*2 + Utils.dp2px(1)-hintBottom, -180, 90);
    }

    //mPath.addArc(hintLeft - pathLength, hintTop, hintLeft + pathLength, hintBottom+Utils.dp2px(1), 270, 90);

    invalidate();
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
    mPaint.setTextSize(hintSize);
    mPaint.getTextBounds(getHint().toString(), 0, getHint().length(), hintRect);
    pathLength = hintRect.right - hintRect.left + Utils.dp2px(1);
    hintLeft = -hintRect.left + getTotalPaddingLeft();
    invalidate();
  }

  public float getHintLine() {
    return hintLine;
  }

  public void setHintLine(float hintLine) {
    this.hintLine = hintLine;
  }

  @Override
  @TargetApi(21)
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //for (int i = 0; i < getHint().length(); i++) {
    //  mPaint.getTextBounds(getHint().toString(),0,i,hintRect);
    //  canvas.drawText(getHint(), i, i+1, hintLeft+hintRect.right, hintTop, mPaint);
    //}

    ////canvas.drawRect(hintLeft - pathLength, hintTop, hintLeft + pathLength, hintTop+Utils.dp2px(1), mPaint);
    //Log.i(ARIRUS, "onDraw: "
    //    + (hintLeft - pathLength)
    //    + " "
    //    + hintTop
    //    + " "
    //    + ( hintLeft + pathLength)
    //    + "  "
    //    + ( hintBottom));

    //mPath.reset();
    //mPath.addArc(hintLeft - pathLength, hintTop, hintLeft + pathLength, hintTop+2, 270, 90);

    mPathMeasure.setPath(mPath, false);
    //Log.i(ARIRUS, "onDraw: " + hintLeft + " " + hintTop);
    //canvas.drawPath(mPath, mPaint);
    for (int i = 0; i < getHint().length(); i++) {
      mPaint.getTextBounds(getHint().toString(), 0, i, hintRect);
      Log.i(ARIRUS, "onDraw: " + hintRect.left);
      Log.i(ARIRUS, "onDraw: " + mPathMeasure.getPosTan(
          mPathMeasure.getLength() * (hintLeft + hintRect.right) / pathLength, mpos, null));
      //  Log.i(ARIRUS, "onDraw: "+mpos[0]+" "+mpos[1]);
        canvas.drawText(getHint(), i, i+1, mpos[0], mpos[1], mPaint);
    }
    //Log.i(ARIRUS, "onDraw: " + mpos[0] + " " + mpos[1]);
    //canvas.drawText(getHint(), 0, getHint().length(), hintLeft+hintRect.right, hintTop, mPaint);
    //canvas.drawTextOnPath(getHint().toString(), mPath, 0, 0, mPaint);
  }
}
