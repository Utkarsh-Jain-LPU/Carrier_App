package com.utkarsh.carrierapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;

class BitmapUtility {

    static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }

    static Bitmap getImage(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}
