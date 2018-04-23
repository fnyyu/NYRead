package com.ny.program.nyread.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.view.WindowManager;

import com.ny.program.nyread.MainActivity;
import com.ny.program.nyread.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ny on 2018/4/23.
 */

public class SplashActivity extends Activity {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startHome();
    }

    private void startHome(){
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(new Intent(SplashActivity.this, MainActivity.class));
               overridePendingTransition(R.anim.splash_enter, R.anim.splash_exit);
               finish();
           }
       }, 2000 ); //2秒后跳转
    }

}
