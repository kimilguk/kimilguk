package com.global.kimilguk;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    boolean isPageOpen = false;//초기오픈상태값
    Animation animationLeft;//열릴 때 애니
    Animation animationRight;//닫힐 때 애니
    LinearLayout bluePage;//애니적용 페이지
    Button btnAnimation;//애니실행버튼이벤트
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        //객체 생성
        bluePage = findViewById(R.id.bluePage);
        btnAnimation = findViewById(R.id.btnAnimation);
        //에니메이션 만들기
        animationLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        animationRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        //애니메이션 리스너 클래스 신규생성 후 객체 생성(아래)
        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        animationLeft.setAnimationListener(animationListener);//애니메이션에 설정셋팅
        animationRight.setAnimationListener(animationListener);
        //버튼에 콜백메소드 onClick액션 적용
        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen) {//기본값은 false
                    bluePage.startAnimation(animationRight);//블루페이지 애니메이션 시작
                }else{
                    bluePage.setVisibility(View.VISIBLE);//블루페이지 보임
                    bluePage.startAnimation(animationLeft);//블루페이지 애니메이션 시작
                }
            }
        });
        //web뷰 객체 생성
        WebView webView = findViewById(R.id.webview);
        EditText txtGoUrl = findViewById(R.id.txtGoUrl);
        Button btnGoUrl = findViewById(R.id.btnGoUrl);
        WebSettings webSettings = webView.getSettings();//웹뷰설정 수정용 객체생성
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());//웹뷰 클라이언트 객체 생성
        btnGoUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//버튼 클릭시 사이트 이동
                webView.loadUrl(txtGoUrl.getText().toString());
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override//애니메이션 종료시 구현코딩
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen) {
                bluePage.setVisibility(View.INVISIBLE);
                btnAnimation.setText("블루페이지 열림");
                isPageOpen = false;
            }else{
                btnAnimation.setText("노란페이지 열림");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}