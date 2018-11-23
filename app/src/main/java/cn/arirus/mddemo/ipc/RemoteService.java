package cn.arirus.mddemo.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RemoteService extends Service {

  OkHttpClient mClient;
  Request mRequest;

  public RemoteService() {
    mClient = new OkHttpClient.Builder().build();
    mRequest = new Request.Builder().url("http://httpbin.org/get").build();
  }

  @Override public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
    return new CustomBinder(this);
  }

  public void request(){
    Log.i("RemoteService", "Arirus request: ");
    Log.i("RemoteService", "Arirus request: ");
    Log.i("RemoteService", "Arirus request: ");
    Log.i("RemoteService", "Arirus request: ");
    mClient.newCall(mRequest).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        e.printStackTrace();
      }

      @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        assert response.body() != null;
        Log.i("RemoteService", "Arirus onResponse: "+response.body().string());
      }
    });
  }


}
