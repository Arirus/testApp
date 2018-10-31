package cn.arirus.mddemo;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import static android.app.Notification.DEFAULT_ALL;
import static android.app.Notification.PRIORITY_MAX;
import static cn.arirus.mddemo.MainActivity.ARIRUS;

public class Main3Activity extends AppCompatActivity {

  Button mButton1;
  Button mButton2;

  Widget mWidget;

  View mMainView;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main3);

    mWidget = findViewById(R.id.w);
    mButton1 = findViewById(R.id.btn1);
    mButton2 = findViewById(R.id.btn2);
    mMainView = findViewById(R.id.main_view);
    mButton1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //ObjectAnimator animator =
        //    ObjectAnimator.ofInt(mMainView, "scrollX", -50).setDuration(1000);
        //animator.start();
        Notification builder = new Notification.Builder(Main3Activity.this)
            .setContentTitle("DDDDDDD")
            .setContentText("FFFFFFF")
            .setPriority(Notification.PRIORITY_HIGH)
            .setDefaults(DEFAULT_ALL)
            //.setTicker("CCCCCCC")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(10029,builder);
      }
    });

    mButton2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ObjectAnimator animator =
            ObjectAnimator.ofInt(mMainView, "scrollY", -50).setDuration(1000);
        animator.start();
      }
    });


    LauncherApps apps = (LauncherApps) getSystemService(LAUNCHER_APPS_SERVICE);
    UserManager userManager = (UserManager) getSystemService(USER_SERVICE);
    for (LauncherActivityInfo han : apps.getActivityList(null,userManager.getUserProfiles().get(0))) {
      Log.i(ARIRUS, "onCreate: "+han.getComponentName());
    }


  }
}
