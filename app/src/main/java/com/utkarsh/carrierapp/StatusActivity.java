package com.utkarsh.carrierapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        DataDatabase db = new DataDatabase(this);

        ArrayList<Qwerty> list = db.ShowData();

        ListView listView = findViewById(R.id.list_view_sender);
        ListAdapter adapter = new ListAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
