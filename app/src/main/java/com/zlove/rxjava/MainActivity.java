package com.zlove.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.create).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                create();
                break;
            default:
                break;
        }
    }

    private void create() {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });

        Observer<Integer> observer = new Observer<Integer>() {

            @Override public void onSubscribe(Disposable d) {
                Log.d("zlove", "--- onSubscribe --- ");
            }

            @Override public void onNext(Integer integer) {
                Log.d("zlove", "--- onNext --- " + integer);
            }

            @Override public void onError(Throwable e) {

            }

            @Override public void onComplete() {
                Log.d("zlove", "--- onComplete --- ");
            }
        };

        observable.subscribe(observer);
    }

}
