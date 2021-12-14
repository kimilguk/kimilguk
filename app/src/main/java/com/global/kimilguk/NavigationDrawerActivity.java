package com.global.kimilguk;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity {
    //클래스 멤버 변수선언
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Fragment1 fragment1;
    Fragment2 fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        // 툴바 홈-뒤로가기 버튼 활성화
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 햄버거 버튼 이미지 불러오기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        // 툴바에 적힐 제목
        actionBar.setTitle("앱바 액티비티");//액션바에 현재 액티비티 이름 출력
        toolbar.setTitleTextColor(Color.WHITE);//Color 객체의 final 변수 상수값 사용
        // 객체 생성
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_settings) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, fragment2).commit();
                    drawerLayout.closeDrawer(navigationView);//drawer객체를 닫고, 시작위치로
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(navigationView)) {//drawer객체가 open 상태라면
            drawerLayout.closeDrawer(navigationView);//drawer객체를 닫고, 시작위치로
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home://시스템에 예약되어 있는 상수값
                //navigationView 전역변수객체가 openDrawer 메소드로 화면에 나타남
                drawerLayout.openDrawer(navigationView);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}