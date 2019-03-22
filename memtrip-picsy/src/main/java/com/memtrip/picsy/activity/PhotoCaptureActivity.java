package com.memtrip.picsy.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;

import com.memtrip.picsy.R;
import com.memtrip.picsy.camera.CameraHolder;
import com.memtrip.picsy.camera.CameraProvider;
import com.memtrip.picsy.view.ControlView;
import com.memtrip.picsy.view.PreviewView;

public class PhotoCaptureActivity extends Activity implements CameraHolder.OnPhotoCaptured {
    private PreviewView uiPreviewView;
    private ControlView uiControlView;

    private CameraHolder mCameraHolder;

    public static final int RESULT_CODE = 0x1;
    public static final String URI = "URI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_photo_capture);
        try {
            System.gc();
        }catch (Exception e){
            System.out.println("Error in GC Call er : " + e);
        }
      //  getActionBar().hide();
        uiPreviewView = (PreviewView)findViewById(R.id.start_camera);
        uiControlView = (ControlView)findViewById(R.id.start_control);

        mCameraHolder = new CameraHolder(this, new CameraProvider(), uiPreviewView,getWindowManager().getDefaultDisplay());
        mCameraHolder.setOnPhotoCaptured(this);
        mCameraHolder.start(Camera.CameraInfo.CAMERA_FACING_BACK);
        uiControlView.setCameraHolder(mCameraHolder);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraHolder.stop();
        try {
            System.gc();
        }catch (Exception e){
            System.out.println("Error in GC Call 652 : " + e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraHolder.start();
    }

    @Override
    public void onPhotoCaptured(String uri) {
        Intent intent = new Intent();
        intent.putExtra(URI,uri);
        setResult(RESULT_CODE,intent);
        try {
            System.gc();
        }catch (Exception e){
            System.out.println("Error in GC Call 956 : " + e);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        mCameraHolder.stop();

        try {
            System.gc();
        }catch (Exception e){
            System.out.println("Error in GC Call : " + e);
        }
        finish();
        super.onBackPressed();

    }

    @Override
    public void onPhotoCapturePressed() {
        uiControlView.setDisabled();
    }
}