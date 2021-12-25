package com.global.kimilguk;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SpinnerViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //클래스 멤버 변수 선언
    String[] items = {"직접입력","global.com","kimilguk.com","abc.net"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_view);
        //객체 생성
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);//참조: ArrayAdapter(출력할 위치, 출력아이템디자인, 출력할아이템배열값)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//스피너 디자인 선택
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);//스피너와 어댑터 연결 즉, 화면에 출력
        spinner.setOnItemSelectedListener(this);//익명클래스를 사용하지 않고, 이벤트구현
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position > 0) {
            Toast.makeText(this, "선택한 도메인은 " + items[position], Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}