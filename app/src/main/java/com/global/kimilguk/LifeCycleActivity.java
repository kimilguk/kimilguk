package com.global.kimilguk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleActivity extends AppCompatActivity {
    //클래스 멤버 변수
    static final int REQUEST_CODE_DIALOG = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"버튼을 눌렀습니다.", Toast.LENGTH_LONG).show();
            }
        });
        Button btnDialogActivity = findViewById(R.id.btnDialogActivity);
        btnDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
                startActivityForResult(intent, REQUEST_CODE_DIALOG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_DIALOG) {
            Toast.makeText(getApplicationContext(),"101번으로 요청한 응답입니다.", Toast.LENGTH_SHORT).show();
            if(resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "응답으로 전달된 값은 " + data.getExtras().getString("name"), Toast.LENGTH_LONG).show();
            }
        }
    }
}