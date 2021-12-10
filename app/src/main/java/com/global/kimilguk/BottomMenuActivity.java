package com.global.kimilguk;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomMenuActivity extends AppCompatActivity {
    //클래스 멤버 변수 선언
    Fragment1 fragment1;
    Fragment2 fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //switch~case 문 사용
                switch (item.getItemId()) {
                    case R.id.menu_search:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.navContainer, fragment2).commit();

                        return true;
                    case R.id.menu_settings:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.navContainer, fragment1).commit();

                        return true;
                }
                return false;
            }
        });
    }
}