package cn.arirus.mddemo.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import cn.arirus.mddemo.R;
import java.util.ArrayList;
import java.util.List;

public class Main6Activity extends AppCompatActivity {

  CuestomViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main6);
    mViewPager = findViewById(R.id.vp);

    mViewPager.setAdapter(new Adapter(getSupportFragmentManager()));
  }



  private static class Adapter extends FragmentStatePagerAdapter{

    List<ContentFragment> mContentFragments;

    public Adapter(FragmentManager fm) {
      super(fm);
      mContentFragments = new ArrayList<>();
      mContentFragments.add(ContentFragment.getInstance());
      mContentFragments.add(ContentFragment.getInstance());
      mContentFragments.add(ContentFragment.getInstance());
    }

    @Override
    public Fragment getItem(int position) {
      return mContentFragments.get(position);
    }

    @Override
    public int getCount() {
      return mContentFragments.size();
    }
  }
}
