package cn.arirus.mddemo.ipc;

import android.os.IInterface;

public interface ICallBack extends IInterface {
  void onResponse(String content);
}
