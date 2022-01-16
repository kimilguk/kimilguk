package com.global.kimilguk;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class PushActivity extends AppCompatActivity {
    //멤버변수선언
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        //객체 생성
        Button btnPush = findViewById(R.id.btnPush);
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//객체생성 후 형변환
                if(Build.VERSION.SDK_INT >= 26) {
                    //안드로이드 8.0 오레오버전 이상일때
                    vibrator.vibrate(VibrationEffect.createOneShot(1000,10));//진동크기 amp 지정
                }else{
                    vibrator.vibrate(1000);
                }
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);//링톤 리소스 접근 위치 지정
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);//소리를 울리기 위해 Ringtone 객체 생성
                ringtone.play();
                showNotification();//상단 알림 메세지 메소드 구현
            }
        });
        //은행입금알림 객체 생성
        Button btnBank = findViewById(R.id.btnBank);
        btnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification2();
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int id = extras.getInt("notificationId");
            String extraData = extras.getString("extraString");
            Toast.makeText(this, id + extraData, Toast.LENGTH_SHORT).show();
        }
    }

    private void showNotification2() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;//빌더방식 클래스변수선언
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //오레오버전 이상일때
            manager.createNotificationChannel(new NotificationChannel(
                    "2", "채널2", NotificationManager.IMPORTANCE_DEFAULT
            ));
            builder = new NotificationCompat.Builder(this, "2");//객체생성
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        Intent intent = new Intent(this, PushActivity.class);
        intent.putExtra("notificationId", 2); //전달할 값
        intent.putExtra("extraString", "입금내용 확인 하였습니다."); //전달할 값
        //펜딩(보류)인텐트는 메시지를 클릭해서 응답확인 하는 용도로 사용
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 109, intent,PendingIntent.FLAG_UPDATE_CURRENT);//펜딩인텐트 객체생성
        builder.setAutoCancel(true);//알림메시지를 클릭하면 사라짐
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("은행 입금 알림");
        builder.setTicker("은행알림");
        builder.setContentText("10000 원 입금 되었습니다.");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        Notification notification = builder.build();//빌더패턴(방식)이란? 객체를 손쉽게 사용하기위한 목적으로 객체를 조립식으로 만들 수 있게 클래스를 구성하고, build()메소드로 조립한다.
        manager.notify(2, notification);//화면출력
    }

    private void showNotification() {
        //객체생성
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;//빌더방식 클래스변수선언
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //오레오버전 이상일때
            manager.createNotificationChannel(new NotificationChannel(
                    "1", "채널1", NotificationManager.IMPORTANCE_DEFAULT
            ));
            builder = new NotificationCompat.Builder(this, "1");//객체생성
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("인증코드 알림");
        builder.setContentText("123456789");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        Notification notification = builder.build();//빌더패턴(방식)이란? 객체를 손쉽게 사용하기위한 목적으로 객체를 조립식으로 만들 수 있게 클래스를 구성하고, build()메소드로 조립한다.
        manager.notify(1, notification);//화면출력
    }
}