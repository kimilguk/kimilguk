package com.global.kimilguk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        //뷰페이저 객체 생성
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),getLifecycle());//객체생성
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        myPagerAdapter.addItem(fragment1);
        myPagerAdapter.addItem(fragment2);
        viewPager2.setOffscreenPageLimit(myPagerAdapter.getItemCount());//프래그먼트 2개를 사용예정
        viewPager2.setAdapter(myPagerAdapter);
        //버튼 액션 추가
        Button btnPage1 = findViewById(R.id.btnPage1);
        Button btnPage2 = findViewById(R.id.btnPage2);
        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(0);
            }
        });
        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(1);
            }
        });
    }
}

class MyPagerAdapter extends FragmentStateAdapter {
    //클래스 멤버 변수생성
    ArrayList<Fragment> items = new ArrayList<Fragment>();//변수 생성과 동시에 객체 생성

    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    //프래그먼트 item 입력 메소드 생성
    public void addItem(Fragment item) {
        items.add(item);
    }
    //프래그먼트 item 출력 오버라이드 메소드 추가
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return items.get(position);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

}