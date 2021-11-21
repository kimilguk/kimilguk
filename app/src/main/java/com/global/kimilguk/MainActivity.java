package com.global.kimilguk;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtnMethod(View view) {
        Toast.makeText(getApplicationContext(), "메서드 테스트 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
    }
}