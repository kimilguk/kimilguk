package com.global.kimilguk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //멤버 변수 선언
    ArrayList<MyTelBook> myTelBooks = new ArrayList<MyTelBook>();
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());//출력 객체 생성
        View cardView = inflater.inflate(R.layout.card_view, parent, false);//카드 뷰 출력
        return new MyViewHolder(cardView);//인플레이터로 카드뷰를 화면에 출력한다
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        //카드 뷰 1  개 position 에 대한 값을 holder에 바인딩 시킬 때 사용
        MyTelBook myTelBook = myTelBooks.get(position);
        holder.setItem(myTelBook);
    }

    @Override
    public int getItemCount() {
        return myTelBooks.size(); //출력 할 카드 뷰 갯수 지정
    }
    public void addItem(MyTelBook myTelBook) {
        myTelBooks.add(myTelBook);//신규전화번호추가
    }
    public MyTelBook getItem(int position) {
        return myTelBooks.get(position);//선택한전화번호읽어오기
    }
    public void setItem(int position, MyTelBook myTelBook) {
        myTelBooks.set(position, myTelBook);//선택한위치에전화번호저장
    }
    public void setItems(ArrayList<MyTelBook> myTelBooks) {
        this.myTelBooks = myTelBooks;//전화번호목록을전체저장
    }
    //카드 뷰 디자인 객체로 사용할 클래스 생성
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewTel;
        TextView txtTelName;
        TextView txtTelDetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewTel = itemView.findViewById(R.id.imgViewTel);
            txtTelName = itemView.findViewById(R.id.txtTelName);
            txtTelDetail = itemView.findViewById(R.id.txtTelDetail);
        }
        public void setItem(MyTelBook myTelBook) {
            txtTelName.setText(myTelBook.getTelName());
            txtTelDetail.setText(myTelBook.getTelDetail());
        }
    }
}
