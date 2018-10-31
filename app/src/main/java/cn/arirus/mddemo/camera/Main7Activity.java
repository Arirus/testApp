package cn.arirus.mddemo.camera;

import android.media.AudioRecord;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.arirus.mddemo.R;

public class Main7Activity extends AppCompatActivity {



  Button mButton1 ;
  Button mButton2 ;
  Button mButton3 ;
  Button mButton4 ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main7);
    mButton1 = findViewById(R.id.btn1);
    mButton2 = findViewById(R.id.btn2);
    mButton3 = findViewById(R.id.btn3);
    mButton4 = findViewById(R.id.btn4);

    mButton1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });


    mButton1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

  }
}
