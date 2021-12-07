package com.global.kimilguk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class DialogActivity extends AppCompatActivity {
    //클래스 멤버 변수
    Button btnToast;
    Button btnSnackbar;
    Button btnDialogBox;
    ProgressDialog dialog;//원형다이얼로그 클래스변수
    ProgressBar progressBar;//프로그레스바 클래스변수
    Button btnProgressStart;
    int progress_percent = 0;//프로그레스바 진행율

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        //부가데이터 전달하기 코드 아래 3줄
        Intent backIntent = getIntent();
        String startCount = backIntent.getStringExtra("startCount");
        Toast.makeText(this, "onCreate startCount: " + startCount, Toast.LENGTH_SHORT).show();
        Button btnBackActivity = findViewById(R.id.btnBackActivity);
        btnBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),LifeCycleActivity.class);//객체 생성
                intent.putExtra("name","김일국");//인텐트 객체에 name키 변수에 값을 부여
                //플래그사용 코드 아래 1줄
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                setResult(RESULT_OK, intent);//응답 보내기
                startActivity(intent);//플래그사용 효과를 보기위해 추가
                //finish();//현재 액티비티 종료(메모리에서 지움)
            }
        });
        btnToast = findViewById(R.id.btnToast);//실행 객체 만들기
        btnToast.setOnClickListener(new View.OnClickListener() {//익명클래스 사용 구현코드작성
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "기본 토스트", Toast.LENGTH_LONG).show();
                LayoutInflater inflater = getLayoutInflater();//자바코드로 화면에 나타낼 객체생성
                //뷰를 xml이 아닌 자바코드로 만들기
                View layout = inflater.inflate(//객체화 대상,보여줄 위치
                        R.layout.toast_custom,
                        null);
                TextView text = layout.findViewById(R.id.txtToast);
                Toast toast = new Toast(getApplicationContext());//토스트 객체생성
                text.setText("모양 바꾼 토스트");
                toast.setGravity(Gravity.CENTER, 0, -100);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
        btnSnackbar = findViewById(R.id.btnSnackBar);
        btnSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "스낵바 출력 입니다.", Snackbar.LENGTH_LONG).show();
            }
        });
        btnDialogBox = findViewById(R.id.btnDialogBox);//객체 생성
        btnDialogBox.setOnClickListener(new View.OnClickListener() {//익명클래스로 구현코드작성
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });

        //프로그레스 바 코딩 시작
        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);//수치로 프로그레스바를 표시할때
        progressBar.setProgress(progress_percent);
        progressBar.setMax(100);
        btnProgressStart = findViewById(R.id.btnProgressStart);
        btnProgressStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //막대 익명클래스로 코드구현(아래)
                new Thread() {//스레드는 프로그램에서 1개의 작업에 별도의 순서를 갖는 다른 작업을 실행할 때 사용
                    //화면재생, 네트워크 통신 스레드 시작과 종료에 대한 제어는 핸들러를 사용하는데, 과정 후반에 자세히 다룹니다.
                    @Override
                    public void run() {
                        super.run();
                        while (true) {
                            while(!Thread.currentThread().isInterrupted()) {
                                progress_percent = progress_percent + 10;
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                progressBar.setProgress(progress_percent);
                                if (progress_percent >= 100) {
                                    progress_percent = 0;
                                    dialog.dismiss();
                                    currentThread().interrupt();
                                }
                            }
                        }
                    }
                }.start();
                //원형
                dialog = new ProgressDialog(DialogActivity.this);//객체생성
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("데이터를 확인하는 중입니다.");
                dialog.show();
            }
        });
    }

    private void showMessage() {
        //얼러트다이얼로그 클래스의 빌더로 객체 속성값 설정
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//빌더 객체생성
        builder.setTitle("안내");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override//익명클래스내에서 코드구현
            public void onClick(DialogInterface dialog, int which) {
                String message = "예 버튼을 눌렀습니다.";
                Log.d("디버그",message);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("디버그", "아니오 버튼을 눌렀습니다.");
            }
        });
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("디버그", "취소 버튼을 눌렀습니다.");
            }
        });
        //다이얼로그 객체 생성 후 화면에 출력
        AlertDialog dialog = builder.create();//대화상자 객체생성
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart() 콜백", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop() 콜백", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy() 콜백", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause() 콜백", Toast.LENGTH_SHORT).show();
        //포즈시(뒤로가기 버튼클릭) sharedPreferences.xml 파일이름으로 저장,수정
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);//MODE_PRIVATE 은 xml 파일을 현재 앱에서만 사용한다고 명시
        SharedPreferences.Editor editor = sharedPreferences.edit();//수정가능 객체로 변경
        editor.putString("progress_percent", String.valueOf(progress_percent));//변수값지정
        editor.commit();//실제 저장 명령어
        if(dialog != null) { dialog.dismiss(); }//SPINNER 회전 진행률 회전 시 발생된 에러 처리
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume() 콜백", Toast.LENGTH_SHORT).show();
        //실행시 sharedPreferences 에서 기존 진행 값을 불러옴
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);//sharedPreferences.xml 객체 생성
        if ((sharedPreferences != null) && (sharedPreferences.contains("progress_percent"))) {
            progress_percent = Integer.parseInt(sharedPreferences.getString("progress_percent", ""));//Key 이름 progress_percent 에 해당하는 값을 불러와서 숫자 형으로 변환
        }
        Toast.makeText(this, "현재까지 진행된 진행률은: " + progress_percent, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart() 콜백", Toast.LENGTH_SHORT).show();
    }
}