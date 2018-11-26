package cn.arirus.mddemo.decomp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.arirus.mddemo.R
import cn.arirus.mddemo.ipc.RemoteService

class DecompActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_decomp)

    Log.i("DecompActivity", "Arirus onCreate: 2222222222")
    Log.i("DecompActivity", "Arirus onCreate: 3333333333")

  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    startService(Intent(this, RemoteService::class.java))
  }
}
