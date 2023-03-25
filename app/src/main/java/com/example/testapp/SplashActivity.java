package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private LottieAnimationView animation;
    final int ANIM_DURATION = 16000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
      animation= findViewById(R.id.lottie);

      animation.animate().translationX(2000).setDuration(2000).setStartDelay(1000);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
          }
      },ANIM_DURATION);

    }
}
