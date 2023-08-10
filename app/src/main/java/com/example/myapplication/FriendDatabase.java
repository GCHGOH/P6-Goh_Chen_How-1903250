package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class FriendDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Friend.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="my_friend";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="friend_name";
    private static final String COLUMN_AGE="friend_age";
    private static final String COLUMN_GENDER="friend_gender";
    private static final String COLUMN_CONTACT="friend_contact";
    private static final String COLUMN_EMAIL="friend_email";

    FriendDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_CONTACT + " TEXT, " +
                COLUMN_EMAIL + " TEXT" +
                ")";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addfriend(String name, int age, String gender,String contact, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_AGE,age);
        cv.put(COLUMN_GENDER,gender);
        cv.put(COLUMN_CONTACT,contact);
        cv.put(COLUMN_EMAIL,email);
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
    void updateData(String row_id, String name, String age, String gender,String contact, String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_AGE,age);
        cv.put(COLUMN_GENDER,gender);
        cv.put(COLUMN_CONTACT,contact);
        cv.put(COLUMN_EMAIL,email);

        long result= db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if (result== -1){
            Toast.makeText(context,"FAILED!",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context,"SUCCESFULLY UPDATED!",Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if (result== -1){
            Toast.makeText(context,"FAILED!",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context,"SUCCESFULLY DELETED!",Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}
