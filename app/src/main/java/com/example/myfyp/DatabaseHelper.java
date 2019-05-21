package com.example.myfyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="boons.db";
    public static final String TABLE_NAME="User";
    public static final String USER_ID="User_ID";
    public static final String USER_NAME="User_name";
    public static final String USER_PASS="User_password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE User (User_ID INTEGER PRIMARY  KEY AUTOINCREMENT, User_name TEXT, User_password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User_name",user);
        contentValues.put("User_password",password);
        long res = db.insert("User",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { USER_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USER_NAME + "=?" + " and " + USER_PASS + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public boolean checkUser1(String username){
        String[] columns = { USER_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USER_NAME + "=?" ;
        String[] selectionArgs = { username};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }


}
