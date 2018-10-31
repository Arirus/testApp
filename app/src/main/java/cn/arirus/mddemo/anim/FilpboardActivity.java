package cn.arirus.mddemo.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import cn.arirus.mddemo.R;

public class FilpboardActivity extends AppCompatActivity {

  FlipBoardView mFlipBoardView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_filpboard);
    mFlipBoardView = findViewById(R.id.flip_board_view);

    ObjectAnimator rightAnim = ObjectAnimator.ofFloat(mFlipBoardView, "RightDeg", 30);
    rightAnim.setDuration(1000);
    ObjectAnimator rotAnim = ObjectAnimator.ofFloat(mFlipBoardView, "RotDeg", 270);
    rotAnim.setInterpolator(new DecelerateInterpolator());
    rotAnim.setDuration(1000);
    ObjectAnimator leftAnim = ObjectAnimator.ofFloat(mFlipBoardView, "LeftDeg", -30);
    leftAnim.setDuration(1000);

    //rotAnim.start();

    AnimatorSet set = new AnimatorSet();
    set.setStartDelay(3000);
    set.playSequentially(rightAnim,rotAnim,leftAnim);
    set.start();

  }
}
