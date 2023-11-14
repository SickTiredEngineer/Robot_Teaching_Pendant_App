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

        sqLiteDatabase.execSQL("create table Members (time integer primary key autoincrement, CommandName text, SCN integer, SC01 integer, SC01T integer, SC02 integer, SC02T integer, SC03 integer, SC03T integer);");
        /*
        create table Members (time integer primary key autoincrement, CommandName text, SCN integer, SC01 integer, SC01T integer, SC02 integer, SC02T integer, SC03 integer, SC03T integer, SC04 integer, SC04T integer, SC05 integer, SC05T integer, SC06 integer, SC06T integer, SC07 integer, SC07T integer, SC08 integer, SC08T integer, SC09 integer, SC09T integer
        //순서 확인              time
        //명령어 식별자           CommandName
        //화위 명령어수           SCN
        //화위 명령어             SC1
        //하위 명령어 순서         SC1T
        //화위 명령어2
        //하위 명령어 순서2
        //화위 명령어3
        //하위 명령어 순서3
        //화위 명령어4
        //하위 명령어 순서4
        //화위 명령어5
        //하위 명령어 순서5
        //화위 명령어6
        //하위 명령어 순서6
        //화위 명령어7
        //하위 명령어 순서7
        //화위 명령어8
        //하위 명령어 순서8
        //화위 명령어9
        //하위 명령어 순서9
        */

        sqLiteDatabase.execSQL("INSERT INTO Members VALUES (1,'move', 3, 'fich', 3, 'yaw', 1, 'angel', 2);");
        sqLiteDatabase.execSQL("INSERT INTO Members VALUES (2,'pinpoint', 2, 'yaw', 2, 'angel', 1, 'time', 3);");
        sqLiteDatabase.execSQL("INSERT INTO Members VALUES (3,'move_itpl', 4, 'yaw', 2, 'angel', 1, 'fich', 3);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<String> getMemberNames (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CommandName FROM Members",null);
        ArrayList<String> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        cursor.close();
        return result;
    }

}