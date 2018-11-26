package cn.arirus.mddemo.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestParm implements Parcelable {

  String url;
  String parms;

  public RequestParm(String url, String parms) {
    this.url = url;
    this.parms = parms;
  }

  protected RequestParm(Parcel in) {
    url = in.readString();
    parms = in.readString();
  }

  public static final Creator<RequestParm> CREATOR = new Creator<RequestParm>() {
    @Override public RequestParm createFromParcel(Parcel in) {
      return new RequestParm(in);
    }

    @Override public RequestParm[] newArray(int size) {
      return new RequestParm[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(url);
    dest.writeString(parms);
  }
}
