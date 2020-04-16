package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class RiderActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Rider");

        DataDatabase db = new DataDatabase(this);

        ArrayList<Qwerty> list = db.ShowData();

        ListView listView = findViewById(R.id.list_view_rider);
        RiderAdapter adapter = new RiderAdapter(this,list);
        listView.setAdapter(adapter);
    }
}
