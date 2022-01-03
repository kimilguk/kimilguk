package com.global.kimilguk;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpActivity extends AppCompatActivity {
    //멤버변수 선언
    EditText txtHttpUrl;
    Button btnRequest;
    TextView txtResponse;
    Handler handler = new Handler();//스레드에서 UI 업데이트용
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        //객체 생성
        txtHttpUrl = findViewById(R.id.txtHttpUrl);
        btnRequest = findViewById(R.id.btnRequest);
        txtResponse = findViewById(R.id.txtResponse);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(txtHttpUrl.getText().toString());//외부 메소드 생성
                    }
                }).start();
            }
        });
    }

    private void request(String toString) {
        StringBuilder stringBuilder = new StringBuilder();//데이터 수신 객체로 사용
        try {
            URL url = new URL(toString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();//객체 생성
            if(urlConnection != null) {
                urlConnection.setConnectTimeout(1000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                int resCode = urlConnection.getResponseCode();//응답 코드 저장
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));//입력 데이터를 받기 위한 객체 생성
                String line = null;
                while(true) {
                    line = reader.readLine();
                    if(line == null) {//입력데이터가 없으면 반복문 종료
                        break;
                    }
                    stringBuilder.append(line + "\n");
                }
                reader.close();//객제 삭제
                urlConnection.disconnect();//객체 삭제
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtResponse.setText(stringBuilder.toString());
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}