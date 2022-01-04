package com.global.kimilguk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HttpActivity extends AppCompatActivity {
    //멤버변수 선언
    EditText txtHttpUrl;
    Button btnRequest;
    //TextView txtResponse;//기존코딩 제거 후 아래라인 추가
    RecyclerView movieRecycler;
    //Handler handler = new Handler();//스레드에서 UI 업데이트용 사용안함.
    static RequestQueue requestQueue;
    MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        //객체 생성
        txtHttpUrl = findViewById(R.id.txtHttpUrl);
        btnRequest = findViewById(R.id.btnRequest);
        //txtResponse = findViewById(R.id.txtResponse);//기존코딩 제거 후 아래라인 추가
        movieRecycler = findViewById(R.id.movieRecycler);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //기존 스레드 코딩 제거 후 아래 request() 메소드 제거
                makeRequest();//신규 외부 메소드 추가
            }
        });
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());//새로운 스레드 생성
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);//레이아웃 설정 객체생성,역순정렬사용안함
        movieRecycler.setLayoutManager(layoutManager);//리사이클러 뷰 설정적용
        movieAdapter = new MovieAdapter();//어댑터 객체 생성
        movieRecycler.setAdapter(movieAdapter);//리사이클러와 어댑터 바인딩 후 출력.
    }

    private void makeRequest() {
        String url = txtHttpUrl.getText().toString();
        StringRequest request = new StringRequest(//웹 요청 객체추가(스레드)
                //볼리 사용시작
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();//Gson사용시작
                        //데이터구조 확인: boxOfficeResult { {boxofficeType, showRange ,dailyBoxOfficeList} }
                        MovieList movieList = gson.fromJson(response, MovieList.class);//Json구조와 같은 자료클래스제작
                        for (int i = 0; i < movieList.boxOfficeResult.dailyBoxOfficeList.size(); i++) {
                            MovieVO movieVO = movieList.boxOfficeResult.dailyBoxOfficeList.get(i);
                            movieAdapter.addItem(movieVO);
                        }
                        movieAdapter.notifyDataSetChanged();//재 접속시 화면UI에 업데이트 적용
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "서버로 부터 응답이 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        request.setShouldCache(false);//요청 객세 실행시 캐시 사용않함.항상새로고침
        requestQueue.add(request);//요청 스레드 시작
    }
    //Json 데이터 구조와 같은 클래스 구조 만들기 시작
    private class MovieList {
        MovieListResult boxOfficeResult;
    }

    private class MovieListResult {
        ArrayList<MovieVO> dailyBoxOfficeList = new ArrayList<MovieVO>();
    }
}