package com.global.kimilguk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThreadActivity extends AppCompatActivity {
    TextView txtThreadValue;//클래스 멤버변수 선언
    BackgroundThread thread;//이너 클래스 생성
    MainHandler handler;//핸들러 변수 선언
    Handler handler2 = new Handler();//Runnable 클래스용 핸들러
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        //객체 생성
        //handler = new MainHandler();//익명 클래스라서 객체생성할 필요 없음.
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
                    //txtThreadValue.setText("value 값: "+value);//핸들러에서 처리
                    /* Runnable 객체를 사용하기 때문에 Message 객체 필요없음.(아래)
                    Message message = handler.obtainMessage();//핸들러에서 사용할 메세지 객체생성
                    Bundle bundle = new Bundle();//번들 객체생성
                    bundle.putInt("value",value);
                    message.setData(bundle);//메세지 큐에 번들데이터 저장
                    handler.sendMessage(message);//핸들러로 메세지 전송
                    */
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            txtThreadValue.setText("value 값: "+value);
                        }
                    });
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                }
            }
        }
    }

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            txtThreadValue.setText("value 값: "+value);
        }
    }
}