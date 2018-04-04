package com.bloodbank;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends Activity {
    ImageView splash_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_splash_screen);
        splash_icon = (ImageView) findViewById(R.id.logo_img);
        splash_icon.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this,
                android.R.anim.slide_in_left|android.R.anim.fade_in));
        splash_icon.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.zoomout));


        int SPLASH_TIME_OUT = 4000;
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences vendorname = SplashScreenActivity.this.
                        getSharedPreferences("OUTLET",SplashScreenActivity.MODE_PRIVATE);
                ;

                if (vendorname.getString("username","").equals("signout") || vendorname.getString("username","").equals("")){
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }

                // close this activity
            }
        }, SPLASH_TIME_OUT);
    }
}
