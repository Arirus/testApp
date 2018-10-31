package cn.arirus.mddemo.scrolling;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import cn.arirus.mddemo.R;

public class Main5Activity extends AppCompatActivity {

  SwipeRefreshLayout mSwipeLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main5);
    //mSwipeLayout = findViewById(R.id.refresher);
    //
    //
    //mSwipeLayout.setColorSchemeColors(Color.BLUE,
    //    Color.GREEN,
    //    Color.YELLOW,
    //    Color.RED);
    //
    //
    //// 设置手指在屏幕下拉多少距离会触发下拉刷新
    //mSwipeLayout.setDistanceToTriggerSync(300);
    //// 设定下拉圆圈的背景
    //mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
    //// 设置圆圈的大小
    //mSwipeLayout.setSize(SwipeRefreshLayout.LARGE);
    //
    //mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    //  @Override
    //  public void onRefresh() {
    //    new Thread(new Runnable() {
    //      @Override
    //      public void run() {
    //        try{
    //          Thread.sleep(2000);
    //        }catch (Exception e){}
    //        finally {
    //          mSwipeLayout.setRefreshing(false);
    //        }
    //      }
    //    }).start();
    //  }
    //});
  }
}
