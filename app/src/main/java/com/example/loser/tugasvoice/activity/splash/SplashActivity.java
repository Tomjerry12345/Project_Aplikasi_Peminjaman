package com.example.loser.tugasvoice.activity.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.example.loser.tugasvoice.R;
import com.example.loser.tugasvoice.activity.autentikasi.login.LoginAdminActivity;

public class SplashActivity extends AppCompatActivity {
    private int waktu_loading=1000;

    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(SplashActivity.this, LoginAdminActivity.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }
}