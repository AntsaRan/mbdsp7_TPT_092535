package com.example.bet.views.fragment.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.example.bet.R;

public class First_screen extends AppCompatActivity {

    public static int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                Intent loginIntent = new Intent(First_screen.this, Login.class);

                startActivity(loginIntent);
                //  Genre.Initialise();
                finish();
            }
        },SPLASH_TIME);
    }
}