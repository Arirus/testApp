package cn.arirus.mddemo.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import cn.arirus.mddemo.R;

public class IpcActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ipc);
  }


  public void onClick(View view){
    Intent intent = new Intent(this,RemoteService.class);
    bindService(intent, new ServiceConnection() {
      @Override public void onServiceConnected(ComponentName name, IBinder service) {
        try {
          CustomBinder.getContract(service).request();
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }

      @Override public void onServiceDisconnected(ComponentName name) {

      }
    },BIND_AUTO_CREATE);
  }
}
