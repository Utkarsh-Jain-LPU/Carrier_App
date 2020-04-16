package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText num,pass;
    private SharedPreferences preferences;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (preferences.getString("Login","").equals("Yes")) {
            Intent intent = new Intent(MainActivity.this,FirstActivity.class);
            startActivity(intent);
            finish();
        }

        num = findViewById(R.id.login_num);
        pass = findViewById(R.id.login_pass);
    }

    public void func(View view) {

        String number = num.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (number.isEmpty()) {
            num.setError("Number is required");
            num.requestFocus();
        }
        else if (number.length() != 10) {
            num.setError("Number must be Valid");
            num.requestFocus();
        }
        else if (password.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
        }
        else {
            preferences.edit().putString("Login","Yes").apply();
            Toast.makeText(this,"Login Successfully...",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
