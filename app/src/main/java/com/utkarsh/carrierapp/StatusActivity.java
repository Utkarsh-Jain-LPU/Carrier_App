package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity {

    private String screen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        DataDatabase db = new DataDatabase(this);

        if (getIntent().hasExtra("ABC")) {
            screen = getIntent().getStringExtra("ABC");
        }

        ArrayList<Qwerty> list = db.ShowData();

        if (screen.equals("sender")) {
            ListView listView = findViewById(R.id.list_view_sender);
            ListAdapter adapter = new ListAdapter(this, list);
            listView.setAdapter(adapter);
        }
        else {
            ListView listView = findViewById(R.id.list_view_sender);
            RecieverAdapter adapter = new RecieverAdapter(this, list);
            listView.setAdapter(adapter);
        }
    }
}
