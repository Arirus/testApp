package cn.arirus.mddemo.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import cn.arirus.mddemo.R;

public class IpcActivity extends AppCompatActivity implements IBinder.DeathRecipient {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ipc);
  }


  IBinder mIBinder;
  ResponseCallBack mResponseCallBack = new ResponseCallBack() {
    @Override public void onResponse(String content) {
      Log.i("IpcActivity", "Arirus onResponse: 1"+" "+Thread.currentThread().getName());
    }
  };

  ResponseCallBack mResponseCallBack1 = new ResponseCallBack() {
    @Override public void onResponse(String content) {
      Log.i("IpcActivity", "Arirus onResponse: 2"+" "+Thread.currentThread().getName());
    }
  };

  public void onClick(View view){
    Intent intent = new Intent(this,RemoteService.class);
    Log.i("IpcActivity", "Arirus onClick: "+Thread.currentThread().getName());
    bindService(intent, new ServiceConnection() {
      @Override public void onServiceConnected(ComponentName name, IBinder service) {
        try {
          Log.i("IpcActivity", "Arirus onServiceConnected: ");
          mIBinder = CustomBinder.getContract(service).asBinder();
          CustomBinder.getContract(service).request(
              new RequestParm("http://httpbin.org/post", "NMSL"), mResponseCallBack);

          //SystemClock.sleep(5000);
          //CustomBinder.getContract(service).request(
          //    new RequestParm("http://httpbin.org/post", "NMSL"), mResponseCallBack1);
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }

      @Override public void onServiceDisconnected(ComponentName name) {
        Log.i("IpcActivity", "Arirus onServiceDisconnected: ");
        mIBinder = null;
      }
    },Service.BIND_AUTO_CREATE);
  }

  @Override public void binderDied() {

  }
}
