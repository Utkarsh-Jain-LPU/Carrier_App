package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void func(View view) {
        switch (view.getId()) {
            case R.id.profile:
                Intent intent = new Intent(FirstActivity.this,ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.sender:
                Intent intent1 = new Intent(FirstActivity.this,SenderActivity.class);
                startActivity(intent1);
                break;
            case R.id.rider:
                Intent intent2 = new Intent(FirstActivity.this,RiderActivity.class);
                startActivity(intent2);
                break;
            case R.id.receiver:
                Intent intent3 = new Intent(FirstActivity.this, recieverActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
