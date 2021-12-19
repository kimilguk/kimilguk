package com.global.kimilguk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {
    //클래스 멤버변수
    EditText txtStartCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        txtStartCount = findViewById(R.id.txtStartCount);
        Button btnServiceStart = findViewById(R.id.btnServiceStart);
        btnServiceStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트 저장 객체 만들고, 부가데이터 넣기
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("serviceName","Myservice");
                intent.putExtra("startCount", txtStartCount.getText().toString());
                startService(intent);//서비스 시작하기 메소드
            }
        });
    }
}