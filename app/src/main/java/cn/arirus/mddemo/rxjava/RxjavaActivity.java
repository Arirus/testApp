package cn.arirus.mddemo.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import cn.arirus.mddemo.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.single.SingleCreate;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class RxjavaActivity extends AppCompatActivity {

  Disposable mDisposable;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rxjava);

    Executor executor = new Executor() {
      @Override public void execute(@NonNull Runnable command) {
        command.run();
      }
    };
    Executors.newCachedThreadPool()

    Observable.range(1,5).subscribeOn(Schedulers.newThread()).subscribe(new Observer<Integer>() {
      @Override public void onSubscribe(Disposable d) {
        mDisposable = d;
        Log.i("RxjavaActivity", "Arirus onSubscribe: ");
      }

      @Override public void onNext(Integer integer) {
        Log.i("RxjavaActivity", "Arirus onNext: "+integer);
        if (integer == 3) mDisposable.dispose();
      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onComplete() {

      }
    });
  }
}
