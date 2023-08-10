package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class ResultDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Result.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="my_result";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="bill_name";
    private static final String COLUMN_AMOUNT="bill_amount";
    private static final String COLUMN_NUM_PEOPLE="num_people";
    private static final String COLUMN_FRIEND_NAME="friend_name_involed";

    ResultDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AMOUNT + " INTEGER, " +
                COLUMN_NUM_PEOPLE + " INTEGER, " +
                COLUMN_FRIEND_NAME + " TEXT" +
                ")";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addbill(String bill_name, int bill_amount, int num_people, String friend_name_involved) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,bill_name);
        cv.put(COLUMN_AMOUNT,bill_amount);
        cv.put(COLUMN_NUM_PEOPLE,num_people);
        cv.put(COLUMN_FRIEND_NAME,friend_name_involved);
        long result=db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context,"FAILED",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"ADDED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if (db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }



    void deleteAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}
