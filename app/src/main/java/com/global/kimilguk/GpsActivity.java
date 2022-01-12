package com.global.kimilguk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class GpsActivity extends AppCompatActivity {
    //멤버변수
    TextView txtGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        //객체 생성
        txtGps = findViewById(R.id.txtGps);
        Button btnGps = findViewById(R.id.btnGps);
        //위치관리자 객체 생성
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GpsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            ActivityCompat.requestPermissions(GpsActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//마지막에 접근한 GPS경로 불러오기
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            txtGps.setText("최근 위치-> 위도: " + latitude + "\n경도 : " + longitude);
        }
        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSListener gpsListener = new GPSListener();//리스너 객체 생성
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GpsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    ActivityCompat.requestPermissions(GpsActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                }
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, gpsListener);//GPS리스너 객체에 현재 GPS 위치정보 불러오기, 위치갱생에 필요한 시간1초, 위치갱신에 필요한 이동거리0미터
            }
        });
    }

    private class GPSListener implements LocationListener {
        @Override //위치가 확인 되었을때 콜백으로 자동으로 호출된다.
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            txtGps.setText("현재 위치-> 위도: " + latitude + "\n경도 : " + longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //구형 기기에서는 필수
        }
    }
}