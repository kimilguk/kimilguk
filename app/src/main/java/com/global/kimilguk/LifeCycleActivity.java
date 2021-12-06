package com.global.kimilguk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleActivity extends AppCompatActivity {
    //클래스 멤버 변수
    static final int REQUEST_CODE_DIALOG = 101;
    static int startCount = 0;
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
                Intent intent = new Intent(getBaseContext(), DialogActivity.class);
                //플래그사용 코드 아래 2줄
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //부가데이터 전달하기 코드 아래 2줄
                intent.putExtra("startCount", String.valueOf(startCount));
                startCount = startCount + 1;
                startActivityForResult(intent, REQUEST_CODE_DIALOG);
            }
        });
        //인텐트사용 객체 생성
        EditText txtIntentData = findViewById(R.id.txtIntentData);
        Button btnContracts = findViewById(R.id.btnContacts);
        btnContracts.setOnClickListener(new View.OnClickListener() {//전화번호부 띄우기
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(txtIntentData.getText().toString()));
                startActivity(intent);
                finish();//현재 액티비티 삭제(화면 종료) 이후 화면에서 뒤로 가기 버튼에서 보이지 않게 됨.
                //주) 위 finish() 추가 후 다음에 실행시 현재 액티비티의 onCreate가 실행되지 않습니다. 스마트폰 전원을 재시작 하면, 정상실행됨.
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