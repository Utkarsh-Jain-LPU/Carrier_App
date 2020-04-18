package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class recieverActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Receiver");
    }

    public void func(View view) {
        Intent intent = new Intent(recieverActivity.this,StatusActivity.class);
        intent.putExtra("ABC","reciever");
        startActivity(intent);
    }
}
