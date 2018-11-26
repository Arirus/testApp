package cn.arirus.mddemo.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ResponseCallBack extends Binder implements ICallBack {

  public static ICallBack getCallBack(IBinder iBinder){
    if (iBinder instanceof ResponseCallBack)
      return (ResponseCallBack)iBinder;
    else
      return new Helper(iBinder);
  }


  @Override public IBinder asBinder() {
    return this;
  }

  @Override
  protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags)
      throws RemoteException {
    this.onResponse(data.readString());
    return true;
  }

  public static class Helper implements ICallBack{

    IBinder mIBinder;

    public Helper(IBinder iBinder) {
      mIBinder = iBinder;
    }


    @Override public void onResponse(String content) {
      Parcel data = Parcel.obtain();
      data.writeString(content);
      try {
        mIBinder.transact(FIRST_CALL_TRANSACTION,data,Parcel.obtain(),0);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    @Override public IBinder asBinder() {
      return mIBinder;
    }
  }

}
