package com.example.greenpoint;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;

import kotlinx.coroutines.Delay;

public class Splash_screen_1 extends AppCompatActivity {

    TextView text_title_app;
    LottieAnimationView animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen1);

        text_title_app = findViewById(R.id.text_title_app);
        animation = findViewById(R.id.animation);

        text_title_app.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        animation.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash_screen_1.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 5000);


    }

}