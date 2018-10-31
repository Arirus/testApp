package cn.arirus.mddemo.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.arirus.mddemo.R;

/**
 * Created by Arirus on 2018/9/21.
 */

public class ContentFragment extends Fragment {

  public static ContentFragment getInstance() {
    return new ContentFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.viewpager_content1,container,false);

    return rootView;
  }
}
