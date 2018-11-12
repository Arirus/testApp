package utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import cn.arirus.mddemo.R;

public class BitmapUtils {

  public static Bitmap getAvator(Context context,float width){
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_rengwuxian,options);
    options.inTargetDensity = (int)width;
    options.inDensity = options.outWidth;
    options.inJustDecodeBounds = false;

    return BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar_rengwuxian,options);
  }

}
