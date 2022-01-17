package com.global.kimilguk;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {
    //멤버변수
    BluetoothAdapter bluetoothAdapter;
    TextView txtNetwork;
    WiFiReceiver wifiReceiver;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        //객체 생성
        Button btnNetwork = findViewById(R.id.btnNetwork);
        txtNetwork = findViewById(R.id.txtNetwork);
        wifiReceiver = new WiFiReceiver();
        btnNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //블루투스 연결코딩 시작
                bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
                if(bluetoothAdapter == null) {
                    //장치가 블루투스를 지원하지 않는 경우.
                    Toast.makeText(getApplicationContext(), "Bluetooth 사용불가", Toast.LENGTH_LONG).show();
                }
                else {
                    // 장치가 블루투스를 지원하는 경우.
                    Toast.makeText(getApplicationContext(), "Bluetooth 사용가능", Toast.LENGTH_LONG).show();
                    bluetoothAdapter.enable();
                    if(!bluetoothAdapter.isEnabled()){
                        //인텐트 액션으로 블루투스 활성화 확인 창 띄우기
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, 102);
                    }else{
                        //이건 이미 페어링되어 있는 기기 목록을 가져 오는것이다.
                        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                        if (pairedDevices.size() > 0) {
                            // 루프를 돌면서 리스트뷰에 출력할 array에 계속 추가한다.
                            for (BluetoothDevice device : pairedDevices) {//향상된 for문 사용
                                Toast.makeText(getApplicationContext(), "연결된 장치: "+ device.getName() + "\n" + device.getAddress(), Toast.LENGTH_LONG).show();
                                //기기이름과 맥어드레스 주소를 출력한다.
                            }
                        }
                    }
                }
                //WiFi 연결코딩 시작
                ConnectivityManager manager = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);//시스템의 커넥션 매니저 객체 생성
                NetworkInfo info = manager.getActiveNetworkInfo();//네트워크 객체 생성
                if(info != null) {
                    if(info.getType() == ConnectivityManager.TYPE_WIFI) {
                        txtNetwork.setText("WiFi로 연결됨\n");
                        IntentFilter filter = new IntentFilter();
                        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);//와이파이상태체크
                        //filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);//네트워크상태체크
                        registerReceiver(wifiReceiver, filter);//자바코드에서 리시버 등록하기
                    }else{
                        txtNetwork.setText("기타로 연결됨\n");
                    }
                    txtNetwork.append("와이파이 연결여부: " + info.isConnected() + "\n");//연결여부 확인
                }else{
                    txtNetwork.setText("데이터통신 불가\n");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 102) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this, "Bluetooth 활성화", Toast.LENGTH_LONG).show();
            }
        }
    }
    //와이파이가 연결되었는지 감시하는 서비스
    private class WiFiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {//와이파이상태체크
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);//인텐트 부가데이터
                if(WifiManager.WIFI_STATE_ENABLED == state) {
                    txtNetwork.append("와이파이상태 접속중\n");
                }else{
                    txtNetwork.append("와이파이상태 비접속중\n");
                }
            }
            /*
            else{
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                WifiManager manager = (WifiManager) getApplicationContext().getSystemService(context.WIFI_SERVICE);
                txtNetwork.append("기타접속: " + manager.getConnectionInfo().getSSID() + "\n");
            }
            */
        }
    }
}