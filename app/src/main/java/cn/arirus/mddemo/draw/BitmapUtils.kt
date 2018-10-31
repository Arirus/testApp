package cn.arirus.mddemo.draw

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import cn.arirus.mddemo.R

public fun createSRC(width: Int, height: Int,context: Context, mPaint:Paint): Bitmap {
  val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
  val canvas = Canvas(bitmap)
  mPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
//        canvas.drawRect(width / 3f, height / 3f, width * 19 / 20f, height * 19 / 20f, mPaint)
  canvas.drawRect(0f, 0f, width.toFloat() , height.toFloat(), mPaint)
  return bitmap
}

/** 目标图像 */
public fun createDST(width: Int, height: Int,context: Context, mPaint:Paint): Bitmap {
  val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
  val canvas = Canvas(bitmap)
  mPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)
//        canvas.drawCircle(width / 3f, height / 3f, width / 3f, mPaint)
  canvas.drawRect(0f, 0f, width.toFloat() , height.toFloat(), mPaint)
  return bitmap
}