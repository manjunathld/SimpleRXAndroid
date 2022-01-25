package com.example.simplerxandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Button buttonSubscribe;
    private TextView textMessage;
    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSubscribe = findViewById(R.id.bv_subscribe);
        textMessage = findViewById(R.id.tv_message);
        observable = Observable.just("Data is Observed");
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("onSubscribe", ""+d);

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("onNext", ""+s);
                String message = s;
                textMessage.setText("onNext: "+message);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("onError", ""+e);
            }

            @Override
            public void onComplete() {
                Log.d("onComplete", "complete");
            }
        };

        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscribeNow();
            }
        });

    }

    public void subscribeNow() {
        observable.subscribe(observer);
    }
}