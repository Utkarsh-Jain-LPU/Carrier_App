package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;

public class SenderActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Sender");
    }

    public void func(View view) {
        switch (view.getId()) {
            case R.id.sender_add:
                Intent intent = new Intent(SenderActivity.this,AddDataActivity.class);
                startActivity(intent);
                break;
            case R.id.sender_status:
                Intent intent1 = new Intent(SenderActivity.this,StatusActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
