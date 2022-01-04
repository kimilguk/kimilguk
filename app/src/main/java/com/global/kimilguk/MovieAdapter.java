package com.global.kimilguk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHoler> {
    ArrayList<MovieVO> items = new ArrayList<MovieVO>();//영화정보 배열 객체 생성
    @NonNull
    @Override
    public MovieAdapter.MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());//출력객체 생성
        View view = inflater.inflate(R.layout.movie_card, parent, false);
        return new MyViewHoler(view);//카드뷰 출력 실행
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHoler holder, int position) {
        MovieVO item = items.get(position);//1개 영화아이템 저장
        holder.setItem(item);//홀더에 item 데이터 출력
    }

    @Override
    public int getItemCount() {
        return items.size();//리사이클러 뷰의 목록 개수지정
    }

    public void addItem(MovieVO item) {
        items.add(item);//1개 영화정보 저장
    }

    public void setItems(ArrayList<MovieVO> items) {
        this.items = items;//다수 영화정보 저장
    }

    public MovieVO getItem(int postion) {
        return items.get(postion);//선택한 1개 영화정보 출력
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        //멤버변수
        TextView txtMovieTitle;
        TextView txtMovieCount;
        public MyViewHoler(@NonNull View itemView) {
            super(itemView);
            //객체생성
            txtMovieTitle = itemView.findViewById(R.id.txtMovieTitle);
            txtMovieCount = itemView.findViewById(R.id.txtMovieCount);
        }
        public void setItem(MovieVO item) {
            txtMovieTitle.setText(item.movieNm);
            txtMovieCount.setText(item.audiCnt + " 명");
        }
    }
}
