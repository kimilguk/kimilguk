package com.global.kimilguk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;

public class ReceiverActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    ShakeDetector shakeDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        ShakeOptions options = new ShakeOptions()
                .background(true)
                .interval(1000)
                .shakeCount(2)
                .sensibility(2.0f);
        /*
        this.shakeDetector = new ShakeDetector(options).start(this, new ShakeCallback() {
            @Override
            public void onShake() {
                Log.d("My리시버", "onShake");
            }
        });*/
        Button btnReceiverStart = findViewById(R.id.btnReceiverStart);
        Button btnReceiverStop = findViewById(R.id.btnReceiverStop);
        btnReceiverStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 shakeDetector = new ShakeDetector(options).start(getApplicationContext());
            }
        });
        btnReceiverStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shakeDetector.stopShakeDetector(getBaseContext());
            }
        });
    }
    @Override
    protected void onDestroy() {
        shakeDetector.destroy(getBaseContext());
        super.onDestroy();
    }
}