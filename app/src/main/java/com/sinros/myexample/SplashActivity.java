package com.sinros.myexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        final String val = shared.getString("uid", "");


        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);

                    if (val.length() == 0) {

                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();


                    } else {

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        thread.start();

    }
}
