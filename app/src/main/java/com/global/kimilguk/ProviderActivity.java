package com.global.kimilguk;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class ProviderActivity extends AppCompatActivity {
    //멤버변수 선언
    ImageView imgSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        //객체 생성
        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        imgSelect = findViewById(R.id.imgSelect);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);//콘텐츠 불러오기 인텐트액션추가
                intent.setType("image/*");//콘텐츠타입을 image 로 제한하면, 앨범 앱을 띄운다.
                startActivityForResult(intent, 101);//선택한 이미지를 가져오기위해 requestCode 추가
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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