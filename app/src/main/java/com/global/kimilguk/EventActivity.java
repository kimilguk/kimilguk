package com.global.kimilguk;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    TextView txtViewSaveState;
    EditText txtEditSaveState;
    Button btnSaveState;
    View view1;
    GestureDetector gestureDetector;//제스처디텍트 클래스형 멤버변수
    View view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);//화면에 xml 뷰 불러옴
        Toast.makeText(getApplicationContext(), "onCreate 호출.", Toast.LENGTH_LONG).show();
        //클래스 객체 생성
        txtViewSaveState = findViewById(R.id.txtViewSaveState);
        txtEditSaveState = findViewById(R.id.txtEditSaveState);
        btnSaveState = findViewById(R.id.btnSaveState);
        view1 = findViewById(R.id.view1);
        btnSaveState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewSaveState.setText(txtEditSaveState.getText().toString());
            }
        });
        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float cursorX = event.getX();
                float cursorY = event.getY();
                if(action == MotionEvent.ACTION_DOWN) {
                    txtViewSaveState.append("손가락 눌림: " + cursorX + "," + cursorY);
                }
                if(action == MotionEvent.ACTION_MOVE) {
                    txtViewSaveState.append("손가락 움직임: " + cursorX + "," + cursorY);
                }
                if(action == MotionEvent.ACTION_UP) {
                    txtViewSaveState.append("손가락 땜: " + cursorX + "," + cursorY);
                }
                return true;
            }
        });
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                txtViewSaveState.append("onDown() 호출됨");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                txtViewSaveState.append("onShowPress() 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                txtViewSaveState.append("onSingleTapUp() 호출됨");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                txtViewSaveState.append("onScroll() 호출됨");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                txtViewSaveState.append("onLongPress() 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                txtViewSaveState.append("onFling() 호출됨");
                return false;
            }
        });
        view2 = findViewById(R.id.view2);
        //view2 객체에 터치이벤트 리스너추가(아래)
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
        if (savedInstanceState != null) {
            String name = savedInstanceState.getString("name");
            txtViewSaveState.setText(name);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState 호출.", Toast.LENGTH_LONG).show();
        outState.putString("name", txtViewSaveState.getText().toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(), "시스템의 뒤로가기 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();//부모메소드 초기화
        Toast.makeText(getApplicationContext(), "onDestroy 호출.", Toast.LENGTH_LONG).show();

    }

}