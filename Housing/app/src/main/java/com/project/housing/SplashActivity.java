package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.StatusBarManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setStatusBar();
        setAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, 2000);
    }

    private void setStatusBar(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    private void setAnimation(){
        ImageView splashLogoIv = findViewById(R.id.splashLogoIv);
        Animation fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        splashLogoIv.setAnimation(fadeInAnim);
    }
}