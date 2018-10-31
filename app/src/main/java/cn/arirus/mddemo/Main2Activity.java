package cn.arirus.mddemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Main2Activity extends AppCompatActivity {


  private RecyclerView recyclerView;
  private LinearLayoutManager linearLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);

    linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    recyclerView = ((RecyclerView) findViewById(R.id.rr));
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new MainActivity.TestAdapter(null));
  }

  public void click(View view) {
    Bundle bundle = new Bundle();
    Intent intent = new Intent(this, MainActivity.class);

    if (view.getId() == R.id.b2) {
      bundle.putInt("index", 1);
      intent.putExtras(bundle);
      startActivity(intent);
    }
  }
}
