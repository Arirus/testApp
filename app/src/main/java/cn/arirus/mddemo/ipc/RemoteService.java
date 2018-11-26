package cn.arirus.mddemo.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RemoteService extends Service {

  OkHttpClient mClient;
  Request mRequest;
  RemoteCallbackList<ICallBack> mCallbackList;

  public RemoteService() {
    mClient = new OkHttpClient.Builder().build();
    mRequest = new Request.Builder().url("http://httpbin.org/get").build();
    mCallbackList = new RemoteCallbackList<>();
  }

  @Override public IBinder onBind(Intent intent) {
    return new CustomBinder(this);
  }

  void request(RequestParm requestParm , ICallBack callBack){
    mCallbackList.register(callBack);
    mRequest = mRequest.newBuilder().url(requestParm.url)
        .post(new FormBody.Builder().add("args",requestParm.parms).build())
        .build();
    new Thread(()->{

      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      int N = mCallbackList.beginBroadcast();
      Log.i("RemoteService", "Arirus request: "+N);

      Log.i("RemoteService", "Arirus request: "+callBack);
      Log.i("RemoteService", "Arirus request: "+callBack.asBinder());

      for (int i = 0; i < N; i++) {
        mCallbackList.getBroadcastItem(i).onResponse("DDDDDD");
        //mCallbackList.getRegisteredCallbackItem(i).onResponse("DDDDDD");
      }
      //callBack.onResponse("DDDDDD");
      mCallbackList.finishBroadcast();
    }).start();
    //mClient.newCall(mRequest).enqueue(new Callback() {
    //  @Override public void onFailure(Call call, IOException e) {
    //    callBack.onResponse(e.getMessage());
    //  }
    //
    //  @Override public void onResponse(Call call, Response response) throws IOException {
    //    callBack.onResponse(response.body().string());
    //  }
    //});
  }


}
