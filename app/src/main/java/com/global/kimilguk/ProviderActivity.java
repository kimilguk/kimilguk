package com.global.kimilguk;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ProviderActivity extends AppCompatActivity {
    //멤버변수 선언
    ImageView imgSelect;
    File outFile;//저장소 저장된 파일변수
    Uri fileUri;//저장소의 파일 경로
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        //주,Android M마시멜로 6버전일때는 수동으로 카메라 권한을 줘야 한다. 누가 7버전 부터는 AutoPermissions 작동되기 때문에 필요없음.
        ActivityCompat.requestPermissions(ProviderActivity.this,new String[]{Manifest.permission.CAMERA},0);
        //객체 생성
        Button btnUserCamera = findViewById(R.id.btnUserCamera);
        //신규 카메라 앱 띄우기
        btnUserCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CameraActivity.class);
                startActivityForResult(intent, 103);
            }
        });
        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        imgSelect = findViewById(R.id.imgSelect);
        //앨범 앱 띄우기
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);//콘텐츠 불러오기 인텐트액션추가
                intent.setType("image/*");//콘텐츠타입을 image 로 제한하면, 앨범 앱을 띄운다.
                startActivityForResult(intent, 101);//선택한 이미지를 가져오기위해 requestCode 추가
            }
        });
        Button btnCameraImage = findViewById(R.id.btnCameraImage);
        //내장 카메라 앱 띄우기
        btnCameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //저장될 폴더 및 파일정보 구하는 코딩
                String filename = "capture.jpg";
                outFile = new File(getFilesDir(), filename);//저장될 파일경로
                Log.d("프로바이더", "파일프로바이더 경로는" + getFilesDir());
                if(outFile.exists()) {
                    outFile.delete();//저장시 중복파일이 있으면 삭제 처리
                }
                try {
                    outFile.createNewFile();//빈 이미지 생성
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT >= 24) {//누가7.0버전이상
                    fileUri = FileProvider.getUriForFile(getApplicationContext(), "com.global.kimilguk.fileprovider", outFile);
                }else{//구형버전 에서 에러나서 제외
                    //fileUri = Uri.fromFile(outFile);
                }
                //인텐트로 카메라 앱 띄우기
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);//부가data로 파일정보전송
                startActivityForResult(intent, 102);//카메라 앱 띄우기
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 102 && resultCode == RESULT_OK) {
            Log.d("경로", "URI: " + data.getData());
            ContentResolver resolver = getContentResolver();//리졸버 객체생성
            try {
                //ContentResolver 객체의 openInputStream 메소드로 파일 읽어 들이기
                InputStream inputStream;
                if(data.getData() != null) {
                    inputStream = resolver.openInputStream(data.getData());//카메라 앱에서 선택한 파일명
                }else{
                    inputStream = resolver.openInputStream(fileUri);//파일프로바이더로 생성된 값
                }
                BitmapFactory.Options options = new BitmapFactory.Options();//저장할 이미지옵션
                options.inSampleSize = 8;//이미지 크기를 1/2의8승으로 줄인다.
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,options);//비트맵객체생성
                imgSelect.setImageBitmap(bitmap);//이미지 뷰에 비트맵 출력
                inputStream.close();//객체 제거
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == 101) {
            if(resultCode == RESULT_OK) {
                Uri fileUri = data.getData();//앨범에서 선택한 파일명
                Log.d("경로", "URI: " + String.valueOf(fileUri));
                ContentResolver resolver = getContentResolver();//리졸버 객체생성
                try {
                    //ContentResolver 객체의 openInputStream 메소드로 파일 읽어 들이기
                    InputStream inputStream = resolver.openInputStream(fileUri);
                    BitmapFactory.Options options = new BitmapFactory.Options();//저장할 이미지옵션
                    options.inSampleSize = 8;//이미지 크기를 1/2의8승으로 줄인다.
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,options);//비트맵객체생성
                    imgSelect.setImageBitmap(bitmap);//이미지 뷰에 비트맵 출력
                    inputStream.close();//객체 제거
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}