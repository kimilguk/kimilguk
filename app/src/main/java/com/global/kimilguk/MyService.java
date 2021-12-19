package com.global.kimilguk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("My서비스","onCrate() 호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null) {
            return Service.START_STICKY;//서비스를 재시작하라는 값
        }else{
            for(int i=1;i<5;i++){
                try {
                    Thread.sleep(1000);
                    Log.d("My서비스",i+"초 후에 " + intent.getStringExtra("serviceName")+" 시작됨");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //인텐트로 액티비티를 띄울 때 사용하는 태그 중 태스크를 신규로 시작하는 플래그는 ACTIVITY_NEW_TASK 이다
            Intent showIntent = new Intent(getApplicationContext(), DialogActivity.class);
            showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //다이얼로그액티비티에 전송한 값이 뜬다
            showIntent.putExtra("startCount", intent.getStringExtra("startCount"));
            startActivity(showIntent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}