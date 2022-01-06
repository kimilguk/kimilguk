package com.global.kimilguk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists customer (" +
                "_id integer PRIMARY KEY autoincrement, " +
                "name text, " +
                "age integer, " +
                "mobile text" +
                ")");//테이블 객체생성
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS customer");//업그레이드시 기존 테이블삭제
            onCreate(db);//버전 업그레이드시 신규 테이블 생성
        }
    }
}
