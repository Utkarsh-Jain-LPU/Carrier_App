package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");

        MyDatabase db = new MyDatabase(this);
        ArrayList<Example> list = db.ShowData();

        TextView name,number,email,aadhar,address;
        name = findViewById(R.id.profile_name);
        number = findViewById(R.id.profile_number);
        email = findViewById(R.id.profile_email);
        aadhar = findViewById(R.id.profile_aadhar);
        address = findViewById(R.id.profile_address);

        name.setText(list.get(0).getName());
        number.setText(list.get(0).getNumber());
        email.setText(list.get(0).getEmail());
        aadhar.setText(list.get(0).getAadhar());
        address.setText(list.get(0).getAddress());
    }
}
