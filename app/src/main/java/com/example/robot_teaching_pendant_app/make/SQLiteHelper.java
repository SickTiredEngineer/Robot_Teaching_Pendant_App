package com.example.robot_teaching_pendant_app.make;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mytestgo.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Members");

        sqLiteDatabase.execSQL("create table Members (mID integer primary key autoincrement, Name text, Age integer);");

        sqLiteDatabase.execSQL("INSERT INTO Members VALUES (1,'Kim',20);");
        sqLiteDatabase.execSQL("INSERT INTO Members VALUES (2,'Lee',30);");
        sqLiteDatabase.execSQL("INSERT INTO Members VALUES (3,'Park',40);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<String> getMemberNames (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Name FROM Members",null);
        ArrayList<String> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        cursor.close();
        return result;
    }

}