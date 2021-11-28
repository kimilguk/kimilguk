package com.global.kimilguk;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    TextView txtViewSaveState;
    EditText txtEditSaveState;
    Button btnSaveState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);//화면에 xml 뷰 불러옴
        Toast.makeText(getApplicationContext(), "onCreate 호출.", Toast.LENGTH_LONG).show();
        //클래스 객체 생성
        txtViewSaveState = findViewById(R.id.txtViewSaveState);
        txtEditSaveState = findViewById(R.id.txtEditSaveState);
        btnSaveState = findViewById(R.id.btnSaveState);
        btnSaveState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewSaveState.setText(txtEditSaveState.getText().toString());
            }
        });
        if (savedInstanceState != null) {
            String name = savedInstanceState.getString("name");
            txtViewSaveState.setText(name);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", txtViewSaveState.getText().toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(), "시스템의 뒤로가기 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();//부모메소드 초기화
        Toast.makeText(getApplicationContext(), "onDestroy 호출.", Toast.LENGTH_LONG).show();

    }

}