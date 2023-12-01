package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logonClick(View v) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
    public void regonClick(View v){
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}