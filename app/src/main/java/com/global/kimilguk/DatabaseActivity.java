package com.global.kimilguk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    SQLiteDatabase database;
    DatabaseHelper databaseHelper;//헬퍼클래스 변수선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        //객체생성
        /*
        database = openOrCreateDatabase("customer.db", MODE_PRIVATE, null);//db 객체생성
        database.execSQL("create table if not exists customer (" +
                "_id integer PRIMARY KEY autoincrement, " +
                "name text, " +
                "age integer, " +
                "mobile text" +
                ")");//테이블 객체생성
        */
        //객체생성시 버전을 올리면, 기존 DB자료가 삭제되는 onUpgrade 메소드가 자동 콜백 된다.
        databaseHelper = new DatabaseHelper(this,"customer.db",null, 2);
        database = databaseHelper.getWritableDatabase();//customer.db 객체 사용처리
        //레코드 추가
        database.execSQL("insert into customer " +
                "(name, age, mobile) " +
                "values " +
                "('홍길동', 20, '000-0000-0000')" +
                "");
        database.execSQL("insert into customer " +
                "(name, age, mobile) " +
                "values " +
                "('성춘향', 25, '111-1111-1111')" +
                "");
        //데이터 조회 버튼 이벤트 처리
        Button btnSelect = findViewById(R.id.btnSelect);
        TextView txtSelect = findViewById(R.id.txtSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = database.rawQuery("select _id, name, age, mobile from customer",null);//커서 객체에 select 쿼리 결과 값 저장
                for(int i=0;i<cursor.getCount();i++){
                    cursor.moveToNext();//다음 레코드로 넘어가기
                    int _id = cursor.getInt(0);
                    String name = cursor.getString(0);
                    int age = cursor.getInt(2);
                    String mobile = cursor.getString(3);
                    txtSelect.append("데이터 번호 " + _id +",이름 "+ name +",나이 "+ age + ",휴대폰 "+mobile + "\n");
                }
                cursor.close();//객체 삭제하기
            }
        });
    }
}