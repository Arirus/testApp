package cn.arirus.mddemo.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CustomBinder extends Binder implements IContract{


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
    mService = service;
  }

  @Override
  protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags)
      throws RemoteException {
    this.request();
    return true;
  }

  @Override public void request() {
    mService.request();
  }

  @Override public IBinder asBinder() {
    return this;
  }


  public static class Helper implements IContract{

    IBinder mIBinder;

    public Helper(IBinder iBinder) {
      mIBinder = iBinder;
    }

    @Override public void request() {
      try {
        mIBinder.transact(FIRST_CALL_TRANSACTION,Parcel.obtain(),Parcel.obtain(),0);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    @Override public IBinder asBinder() {
      return mIBinder;
    }
  }

}
