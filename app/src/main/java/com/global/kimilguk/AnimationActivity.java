package com.global.kimilguk;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    boolean isPageOpen = false;//초기오픈상태값
    Animation animationLeft;//열릴 때 애니
    Animation animationRight;//닫힐 때 애니
    LinearLayout yellowPage;//애니적용 페이지
    Button btnAnimation;//애니실행버튼이벤트
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        yellowPage = findViewById(R.id.yellowiPage);
        //에니메이션 만들기
        animationLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        animationRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);
    }
}