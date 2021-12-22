package com.global.kimilguk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

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
        /*holder.imgViewTel.setImageResource(android.R.drawable.presence_away);
        holder.txtTelName.setText("홍길동");
        holder.txtTelDetail.setText("111-1111-1111");*/
    }

    @Override
    public int getItemCount() {
        return 5; //출력 할 카드 뷰 갯수 지정
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
    }
}
