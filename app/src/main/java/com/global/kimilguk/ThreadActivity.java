package com.global.kimilguk;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadActivity extends AppCompatActivity {
    TextView txtThreadValue;//클래스 멤버변수 선언
    BackgroundThread thread;//이너 클래스 생성
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        //객체 생성
        txtThreadValue = findViewById(R.id.txtThreadValue);
        Button btnThreadStart = findViewById(R.id.btnThreadStart);
        btnThreadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new BackgroundThread();
                thread.start();
            }
        });
        Button btnThreadStop = findViewById(R.id.btnThreadStop);
        btnThreadStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });
    }

    private class BackgroundThread extends Thread {
        int value = 0;//출력할 초기값
        @Override
        public void run() {
            super.run();
            for(int i=0;i<100;i++) {
                try {
                    Thread.sleep(1000);
                    value++;//동일 코딩: value = value + 1; value += 1;
                    Log.d("스레드","value 값: "+value);
                    //스레드 클래스에서 메인스레드의 UI내용 변경 시 에러 발생됨
                    txtThreadValue.setText("value 값: "+value);
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                }
            }
        }
    }
}