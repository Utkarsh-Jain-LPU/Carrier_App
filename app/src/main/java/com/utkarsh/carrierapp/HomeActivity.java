package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private MyDatabase db;
    private EditText nm,num,em,add,aad;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new MyDatabase(this);

        Objects.requireNonNull(getSupportActionBar()).hide();

        nm = findViewById(R.id.home_name);
        num = findViewById(R.id.home_num);
        em = findViewById(R.id.home_email);
        aad = findViewById(R.id.home_aadhar);
        add = findViewById(R.id.home_address);
    }

    public void func(View view) {

        String name = nm.getText().toString().trim();
        String number = num.getText().toString().trim();
        String email = em.getText().toString().trim();
        String aadhar = aad.getText().toString().trim();
        String address = add.getText().toString().trim();

        if (name.isEmpty()) {
            nm.setError("Name is required");
            nm.requestFocus();
        }
        else if (number.isEmpty()) {
            num.setError("Number is required");
            num.requestFocus();
        }
        else if (number.length() != 10) {
            num.setError("Number must be Valid");
            num.requestFocus();
        }
        else if (email.isEmpty()) {
            em.setError("Email is required");
            em.requestFocus();
        }
        else if (aadhar.isEmpty()) {
            aad.setError("Aadhar is required");
            aad.requestFocus();
        }
        else if (address.isEmpty()) {
            aad.setError("Address is required");
            aad.requestFocus();
        }
        else {
            Toast.makeText(this,"Submitted Successfully...",Toast.LENGTH_SHORT).show();
            db.InsertData(name,number,email,aadhar,address);
            Intent intent = new Intent(HomeActivity.this,FirstActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
