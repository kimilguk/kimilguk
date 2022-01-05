package com.global.kimilguk;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        //객체생성
        database = openOrCreateDatabase("customer", MODE_PRIVATE, null);//db 객체생성
        database.execSQL("create table if not exists customer (" +
                "_id integer PRIMARY KEY autoincrement, " +
                "name text, " +
                "age integer, " +
                "mobile text" +
                ")");//테이블 객체생성
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

    }
}