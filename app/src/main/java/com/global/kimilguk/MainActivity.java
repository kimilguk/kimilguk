package com.global.kimilguk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

    public void onClickBtnNaver(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    public void onClickBtnCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
    //실제 단말기 스마트폰에서 전화걸기 앱을 호출하시기 바랍니다.
    public void onClickBtnTel(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:01081756075"));
        startActivity(intent);
    }
}