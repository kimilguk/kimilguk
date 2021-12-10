package com.global.kimilguk;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class TopMenuActivity extends AppCompatActivity {
    //클래스 멤버 변수 선언
    Fragment1 fragment1;
    Fragment2 fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//액션바에 툴바 객체를 올려 놓는다
        ActionBar actionBar = getSupportActionBar();//객체 생성
        actionBar.setTitle("앱바 액티비티");//액션바에 현재 액티비티 이름 출력
        toolbar.setTitleTextColor(Color.WHITE);//Color 객체의 final 변수 상수값 사용
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("연락처"));
        tabLayout.addTab(tabLayout.newTab().setText("구글지도"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//탭메뉴가 가로로 가득차게
        //탭 메뉴 리스너로 선택 이벤트 기능 구현
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selected = tab.getPosition();
                Fragment fragment = null;//객체 변수 생성
                if(selected == 0) {
                    fragment = fragment1;
                }else if(selected == 1) {
                    fragment = fragment2;
                }
                //프래그먼트 매니저로 커밋해서 화면에 출력
                getSupportFragmentManager().beginTransaction().replace(R.id.tabContainer, fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int selected = tab.getPosition();
                Fragment fragment = null;//객체 변수 생성
                if(selected == 0) {
                    fragment = fragment1;
                }else if(selected == 1) {
                    fragment = fragment2;
                }
                //프래그먼트 매니저로 커밋해서 화면에 출력
                getSupportFragmentManager().beginTransaction().replace(R.id.tabContainer, fragment).commit();
            }
        });
    }
}