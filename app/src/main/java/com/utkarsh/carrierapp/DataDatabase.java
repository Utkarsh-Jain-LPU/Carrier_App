package com.utkarsh.carrierapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataDatabase extends SQLiteOpenHelper {

    private static int dbversion = 1;
    private static String dbname = "My Product";

    DataDatabase(Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE data(name text,spec text,pick text,del text,num text,fare text,status text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void InsertData(String nm, String spec, String pick, String del, String num, String fare) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",nm);
        contentValues.put("spec",spec);
        contentValues.put("pick",pick);
        contentValues.put("del",del);
        contentValues.put("num",num);
        contentValues.put("fare",fare);
        contentValues.put("status","0");
        database.insert("data",null,contentValues);
    }

    ArrayList<Qwerty> ShowData() {

        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * from data";
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<Qwerty> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String spec = cursor.getString(1);
            String pick = cursor.getString(2);
            String del = cursor.getString(3);
            String num = cursor.getString(4);
            String fare = cursor.getString(5);
            String status = cursor.getString(6);
            list.add(new Qwerty(name,spec,pick,del,num,fare,status));
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
