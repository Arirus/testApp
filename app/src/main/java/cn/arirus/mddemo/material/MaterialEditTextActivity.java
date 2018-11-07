package cn.arirus.mddemo.material;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import cn.arirus.mddemo.Main2Activity;
import cn.arirus.mddemo.R;

public class MaterialEditTextActivity extends Activity {

  MaterialEditText mMaterialEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_material_edit_text);
  }

}
