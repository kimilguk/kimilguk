package com.global.kimilguk;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class DialogActivity extends AppCompatActivity {
    //클래스 멤버 변수
    Button btnToast;
    Button btnSnackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        btnToast = findViewById(R.id.btnToast);//실행 객체 만들기
        btnToast.setOnClickListener(new View.OnClickListener() {//익명클래스 사용 구현코드작성
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();//자바코드로 화면에 나타낼 객체생성
                //뷰를 xml이 아닌 자바코드로 만들기
                View layout = inflater.inflate(//객체화 대상,보여줄 위치
                        R.layout.toast_custom,
                        null);
                TextView text = layout.findViewById(R.id.txtToast);
                Toast toast = new Toast(getApplicationContext());//토스트 객체생성
                text.setText("모양 바꾼 토스트");
                toast.setGravity(Gravity.CENTER, 0, -100);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
        btnSnackbar = findViewById(R.id.btnSnackBar);
        btnSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "스낵바 출력 입니다.", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}