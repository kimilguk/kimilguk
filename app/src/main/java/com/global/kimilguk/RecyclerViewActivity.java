package com.global.kimilguk;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //객체 생성
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //리사이클러 뷰 객체를 리니어레이아웃 배치로 설정
        //recyclerView.setLayoutManager(linearLayoutManager);
        //여러 컬럼으로 보여주기
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyAdapter myAdapter = new MyAdapter();//어댑터 객체 생성
        //전화번호부에 데이터 입력
        myAdapter.addItem(new MyTelBook("홍길동", "000-0000-0000"));
        myAdapter.addItem(new MyTelBook("성춘향", "111-1111-1111"));
        myAdapter.addItem(new MyTelBook("각시탈", "222-2222-2222"));
        recyclerView.setAdapter(myAdapter);//리사이클러뷰와 어댑터 연결
    }
}