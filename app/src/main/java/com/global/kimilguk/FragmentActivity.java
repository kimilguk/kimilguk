package com.global.kimilguk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //객체 생성
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Button btnFragment1 = findViewById(R.id.btnFragment1);
        Button btnFragment2 = findViewById(R.id.btnFragment2);
        //초기 객체 지우기(아래)
        FrameLayout frameLayout = findViewById(R.id.fragmentContainer);
        frameLayout.removeAllViews();
        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment1).commit();
            }
        });
        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();
            }
        });
    }
}