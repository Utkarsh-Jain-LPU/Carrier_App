package com.utkarsh.carrierapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    private static int dbversion = 1;
    private static String dbname = "My Database";

    MyDatabase(Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE info(name text,number text,email text,aadhar text,address text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void InsertData(String nm, String no, String em, String aad, String add) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",nm);
        contentValues.put("number",no);
        contentValues.put("email",em);
        contentValues.put("aadhar",aad);
        contentValues.put("address",add);
        database.insert("info",null,contentValues);
    }

    ArrayList<Example> ShowData() {

        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * from info";
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<Example> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            String email = cursor.getString(2);
            String aadhar = cursor.getString(3);
            String address = cursor.getString(4);
            list.add(new Example(name,number,email,aadhar,address));
        }
        return list;
    }

    /*void DeleteData(String nm) {

        SQLiteDatabase database = getWritableDatabase();
        String where = "name = ?";
        String[] ss = {nm};
        database.delete("info",where,ss);
    }*/

    /*void UpdateData(String name,String number,String email) {

        SQLiteDatabase database = getWritableDatabase();
        String where = "name = ?";
        String[] ss = {name};
        ContentValues contentValues = new ContentValues();
        contentValues.put("num",number);
        contentValues.put("email",email);
        database.update("info",contentValues,where,ss);
    }*/

    /*ArrayList<Example> SearchData(String data) {

        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * from info where name like '%"+data+"%' or num like '%"+data+"%' or email like '%"+data+"%'";
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<Example> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            String email = cursor.getString(2);
            list.add(new Example(name,number,email));
        }
        return list;
    }*/
}
