package com.global.kimilguk;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //객체 생성
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Button btnFragment1 = findViewById(R.id.btnFragment1);
        Button btnFragment2 = findViewById(R.id.btnFragment2);
        //초기 객체 지우기(아래)
        FrameLayout frameLayout = findViewById(R.id.fragmentContainer);
        frameLayout.removeAllViews();
        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment1).commit();
            }
        });
        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //화면에 메뉴 출력=inflate
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //메뉴 아이템 중에서 검색을 위해 정의한 아이템의 액션 뷰객체 생성
        View view = menu.findItem(R.id.menu_search).getActionView();
        EditText editText = view.findViewById(R.id.txtEditSearch);//검색어입력 객체생성
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override //입력상자 리스너객체의 수정이벤트 메소드
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // 키보드 내리기
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                Toast.makeText(getApplicationContext(),"입력된 검색어는: " + editText.getText(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedId = item.getItemId();
        switch(selectedId) {
            /*case R.id.menu_search:
                Toast.makeText(this, "검색메뉴가 선택 되었습니다.", Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.menu_settings:
                Toast.makeText(this, "설정메뉴가 선택 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_hidden:
                Toast.makeText(this, "숨김메뉴가 선택 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}