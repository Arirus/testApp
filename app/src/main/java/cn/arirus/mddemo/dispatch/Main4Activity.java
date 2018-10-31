package cn.arirus.mddemo.dispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import cn.arirus.mddemo.R;

import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class Main4Activity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main4);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    Log.i(ARIRUS, "dispatchTouchEvent: ACTIVITY");
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.i(ARIRUS, "onTouchEvent: ACTIVITY");
    return super.onTouchEvent(event);
  }
}
