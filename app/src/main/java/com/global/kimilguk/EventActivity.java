package com.global.kimilguk;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    TextView txtViewSaveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);//화면에 xml 뷰 불러옴
        Toast.makeText(getApplicationContext(), "onCreate 호출.", Toast.LENGTH_LONG).show();
        //클래스 객체 생성
        txtViewSaveState = findViewById(R.id.txtViewSaveState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(), "시스템의 뒤로가기 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}