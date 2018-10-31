package cn.arirus.mddemo.anim;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import cn.arirus.mddemo.R;
import cn.arirus.mddemo.draw.Utils;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class AnimActivity extends AppCompatActivity {

  AnimCircleView mAnimCircleView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_anim);
    mAnimCircleView = findViewById(R.id.anim_circle_view);

    //ObjectAnimator objectAnimator =
    //    ObjectAnimator.ofObject(mAnimCircleView, "DstPoint", new PointFEvalutor(),
    //        new PointF(Utils.dp2px(300), Utils.dp2px(300)));
    //objectAnimator.setDuration(5000).setStartDelay(3000);
    //objectAnimator.start();

    PropertyValuesHolder valuesHolder =
        PropertyValuesHolder.ofObject("DstPoint", new PointFEvalutor(),
            new PointF(Utils.dp2px(600), Utils.dp2px(300)));
    PropertyValuesHolder valuesHolder1 =
        PropertyValuesHolder.ofKeyframe("alpha",
            Keyframe.ofFloat(0,1f),
            Keyframe.ofFloat(0.8f,0.9f),
            Keyframe.ofFloat(1f,0.1f)
        );
        //PropertyValuesHolder.ofFloat("alpha",0.1f);




    ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mAnimCircleView,valuesHolder,valuesHolder1);
    objectAnimator.setDuration(3000).setStartDelay(3000);
    objectAnimator.setInterpolator(new DecelerateInterpolator());

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.playSequentially(objectAnimator,objectAnimator);
  }

  private static class PointFEvalutor implements TypeEvaluator<PointF> {
    PointF mPointF = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
      Log.i(ARIRUS, "evaluate: "+fraction);
      mPointF.x = startValue.x + (endValue.x - startValue.x) * fraction;
      mPointF.y = startValue.y + (endValue.y - startValue.y) * fraction;
      Log.i(ARIRUS, "evaluate: "+mPointF.x+" "+mPointF.y);
      return mPointF;
    }
  }
}
