package cn.arirus.mddemo.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CustomBinder extends Binder implements IContract{
  private static final java.lang.String DESCRIPTOR = "cn.arirus.versioncomp.ipc.CallBack";

  public static IContract getContract(IBinder binder) throws RemoteException{
    if (binder == null)
      throw new RemoteException("BINDER is null!!");
    if (binder instanceof CustomBinder)
      return (CustomBinder)binder;
    else
      return new Helper(binder);
  }


  private RemoteService mService;

  public CustomBinder(RemoteService service) {
    attachInterface(this,DESCRIPTOR);
    mService = service;
  }

  @Override
  protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags)
      throws RemoteException {
    //RequestParm requestParm = data.readParcelable(RequestParm.class.getClassLoader());
    RequestParm requestParm = data.readParcelable(RequestParm.class.getClassLoader());
    ICallBack callBack = ResponseCallBack.getCallBack(data.readStrongBinder());
    this.request(requestParm,callBack);
    return true;
  }

  @Override public void request(RequestParm requestParm, ICallBack callBack) {
    mService.request(requestParm, callBack);
  }

  @Override public IBinder asBinder() {
    return this;
  }


  public static class Helper implements IContract{

    IBinder mIBinder;

    public Helper(IBinder iBinder) {
      mIBinder = iBinder;
    }

    public java.lang.String getInterfaceDescriptor() {
      return DESCRIPTOR;
    }

    @Override public void request(RequestParm requestParm, ICallBack callBack) {
      try {
        Parcel request = Parcel.obtain();
        request.writeParcelable(requestParm,0);
        request.writeStrongBinder(callBack.asBinder());
        mIBinder.transact(FIRST_CALL_TRANSACTION,request,Parcel.obtain(),0);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    @Override public IBinder asBinder() {
      return mIBinder;
    }
  }

}
