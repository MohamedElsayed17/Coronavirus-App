package com.example.coronavirus.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.coronavirus.R;
import com.example.coronavirus.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);

        if(getActionBar()!=null)
            getActionBar().hide();
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },4000);


    }
}