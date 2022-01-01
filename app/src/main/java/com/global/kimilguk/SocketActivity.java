package com.global.kimilguk;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketActivity extends AppCompatActivity {
    //클래스 멤버변수 선언
    EditText txtSendMessage;
    Button btnSendMessage;
    TextView txtReceiveClient;
    TextView txtReceiveServer;
    Button btnStartServer;
    Handler handler = new Handler();//변수선언하면서 객체생성
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        //객체생성
        txtSendMessage = findViewById(R.id.txtSendMessage);
        txtReceiveClient = findViewById(R.id.txtReceiveClient);
        txtReceiveServer = findViewById(R.id.txtReceiveServer);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnStartServer = findViewById(R.id.btnStartServer);
        //버튼 이벤트 구현
        btnStartServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //스레드 사용
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();//서버시작 외부메소드 사용
                    }
                }).start();
            }
        });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사용자가 입력한 전송 메시지 변수에 저장
                final String sendData = txtSendMessage.getText().toString();
                //스레드 사용
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage(sendData);//메세지전송 외부메소드 사용
                    }
                }).start();
            }
        });
    }

    private void sendMessage(String sendData) {
        int portNumber = 5001;
        try {
            Socket socket = new Socket("localhost", portNumber);//localhist 는 IP상수 127.0.0.1이다.,소켓 객체생성
            Log.d("sendMessage 메소드", "소켓 연결함.");
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());//클라이언트에서 서버로 보내는 스트림 출력저장 객체생성
            outputStream.writeObject(sendData+ " from 클라이언트");//서버로 데이터 전송시작
            Log.d("sendMessage 메소드", "서버로 데이터 전송함.");
            outputStream.flush();//출력 스트림 객체삭제
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());//서버에서 클라이언트로 받은 스트림 입력저장 객체생성
            Object obj = inputStream.readObject();//입력 값 객체화
            handler.post(new Runnable() {
                @Override
                public void run() {
                        txtReceiveClient.append("서버에서 받은 데이터 :" + obj + "\n");
                }
            });
            socket.close();//소켓 객체삭제
        } catch (IOException | ClassNotFoundException e) {
            Log.d("sendMessage 메소드", "소켓 연결안됨.");
        }
    }

    private void startServer() {
        int portNumber = 5001;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);//서버객체생성
            Log.d("startServer 메소드", "서버 시작됨.");
            while (true) {
                Socket socket = serverSocket.accept();//소켓에 연결된 클라이언트를 관리하는 대기상태의 객체생성
                Log.d("startServer 메소드", "소켓 연결됨.");
                InetAddress inetAddress = socket.getLocalAddress();//클라이언트 IP주소객체생성
                int clientPort = socket.getPort();//소켓에 연결된 클라이언트 포트생성
                 ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());//클라이언트에서 서버로 받은 스트림 입력저장 객체생성
                Object obj = inputStream.readObject();//입력값 객체화

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());//서버에서 클라이언트로 보내는 스트림 출력저장 객체생성
                outputStream.writeObject(obj + " from 서버");//클라이언트로 데이터 전송시작
                Log.d("startServer 메소드", "클라이언트로 데이터 전송함.");
                outputStream.flush();//출력 스트림 객체삭제
                handler.post(new Runnable() {//UI 핸들링 시작
                    @Override
                    public void run() {
                        txtReceiveServer.append("클라이언트에서 IP : 포트 :" + inetAddress + ":" + clientPort + "\n");
                        txtReceiveServer.append("클라이언트에서 받은 데이터 :" + obj + "\n");
                    }
                });
                socket.close();//소켓 객체삭제
            }
        } catch (IOException | ClassNotFoundException e) {
            Log.d("startServer 메소드", "startServer 메소드 에러.");
        }
    }
}