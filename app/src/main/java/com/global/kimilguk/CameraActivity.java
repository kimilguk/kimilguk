package com.global.kimilguk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    //멤버변수 선언
    CameraSurfaceView surfaceView;
    MediaScannerConnection mediaScannerConnection = null;
    MediaScannerConnection.MediaScannerConnectionClient mMediaScannerClient = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //객체생성
        FrameLayout surfaceFrame = findViewById(R.id.surfaceFrame);
        surfaceView = new CameraSurfaceView(this);
        surfaceFrame.addView(surfaceView);//서피스 뷰 출력
        Button btnCapture = findViewById(R.id.btnCapture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surfaceView.capture(new Camera.PictureCallback() {//서피스뷰의 capture호출
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);//서피스뷰에서 전달받은 배열값을 아날로그사진 Bitmap 객체로 만들기
                        String outUriStr = MediaStore.Images.Media.insertImage(
                             getContentResolver(),//내용재공자 리졸버를 통해서 앨범에 저장처리
                             bitmap,//저장할 데이터
                             "Captured Image",//이미지 메타 데이터 타이틀
                             "Captured Image using Camera"//이미지 메타 데이터 내용
                        );
                        if(outUriStr == null) {
                            Log.d("저장", "저장 에러");
                        }else{
                            //Uri outUri = Uri.parse(outUriStr);
                            //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));
                            //미디어스캐너 클라이언트 init
                            mMediaScannerClient = new MediaScannerConnection.MediaScannerConnectionClient(){
                                @Override
                                public void onMediaScannerConnected() {
                                    mediaScannerConnection.scanFile( outUriStr, null);
                                    //"미디어 스캔 성공"
                                }
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    //"미디어 스캔 연결 종료 처리 uri=" + uri
                                    mediaScannerConnection.disconnect();
                                }
                            };
                            mediaScannerConnection = new MediaScannerConnection(CameraActivity.this, mMediaScannerClient);
                            mediaScannerConnection.connect();
                            Log.d("저장", "저장한 내용을 앨범 앱에 알려줌");
                        }
                        // 다시 미리보기 화면 보여줌
                        camera.startPreview();
                    }
                });
            }
        });
    }

    private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        //멤버변수 선언
        SurfaceHolder surfaceHolder;
        Camera camera = null;
        public CameraSurfaceView(Context context) {
            super(context);
            surfaceHolder = getHolder();//객체 생성
            surfaceHolder.addCallback(this);//객체 설정
            //카메라가 SurfaceView를 독점하기 위한 타입 설정,버퍼를 사용하지않음
            //surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            camera = Camera.open();//객체 생성
            try {
                camera.setPreviewDisplay(surfaceHolder);//카메라 미리보기에 서피스홀더 바인딩
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();//서피스뷰 크기가 변경시 미리보기 시작
        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            camera.stopPreview();//미리보기 중지
            camera.release();//객체 삭제
            camera=null;
        }
        public boolean capture(Camera.PictureCallback handler) {
            if(camera != null) {//사진찍기 버튼 이벤트 처리 코드
                camera.takePicture(null,null,handler);//사진촬영 시작
                return true;
            }else{
                return false;
            }
        }
    }
}