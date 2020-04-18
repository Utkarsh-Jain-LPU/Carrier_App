package com.utkarsh.carrierapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Objects;

public class AddDataActivity extends AppCompatActivity {

    private byte[] blob;
    private DataDatabase db;
    private ImageView imageView;
    private Bitmap bitmap;
    private EditText nm, spec, num, fare;
    private AutoCompleteTextView del,pick;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        db = new DataDatabase(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Add Data");

        nm = findViewById(R.id.data_name);
        spec = findViewById(R.id.data_spec);
        num = findViewById(R.id.data_number);
        fare = findViewById(R.id.data_fare);
        imageView = findViewById(R.id.add_image);

        checkPermission();

        pick = findViewById(R.id.data_pick);
        pick.setAdapter(new PlaceAutoSuggestAdapter(AddDataActivity.this,android.R.layout.simple_list_item_1));

        del = findViewById(R.id.data_deliver);
        del.setAdapter(new PlaceAutoSuggestAdapter(AddDataActivity.this,android.R.layout.simple_list_item_1));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,131);
            }
        });
    }

    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},131);
        }
    }

    public void func(View view) {

        String name = nm.getText().toString().trim();
        String specification = spec.getText().toString().trim();
        String pickup = pick.getText().toString().trim();
        String delivery = del.getText().toString().trim();
        String number = num.getText().toString().trim();
        String fares = fare.getText().toString().trim();

        if (name.isEmpty()) {
            nm.setError("Name is required");
            nm.requestFocus();
        }
        else if (specification.isEmpty()) {
            spec.setError("Specification is required");
            spec.requestFocus();
        }
        else if (pickup.isEmpty()) {
            pick.setError("Pick-Up Location is required");
            pick.requestFocus();
        }
        else if (delivery.isEmpty()) {
            del.setError("Delivery Location is required");
            del.requestFocus();
        }
        else if (number.isEmpty()) {
            num.setError("Number is required");
            num.requestFocus();
        }
        else if (number.length() != 10) {
            num.setError("Number must be Valid");
            num.requestFocus();
        }
        else if (fares.isEmpty()) {
            fare.setError("Fare is required");
            fare.requestFocus();
        }
        else if (bitmap == null) {
            Toast.makeText(AddDataActivity.this,"Image is required...",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Item Added Successfully...", (Toast.LENGTH_SHORT)).show();
            db.InsertData(name,specification,pickup,delivery,number,fares,blob);
            onBackPressed();
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==131 && resultCode==RESULT_OK) {
            assert data != null;
            bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            imageView.setImageBitmap(bitmap);
            assert bitmap != null;
            blob = BitmapUtility.getBytes(bitmap);
        }
    }
}
