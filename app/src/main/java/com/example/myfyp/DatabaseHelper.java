package com.example.myfyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="boons.db";
    public static final String TABLE_NAME="Password";
    public static final String PASS_ID="Pass_ID";
    public static final String PASS_PASS="Pass_real";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Password (Pass_ID INTEGER PRIMARY  KEY AUTOINCREMENT, Pass_real TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Cursor addUser(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Pass_real",password);
        long res = db.insert("Password",null,contentValues);
        db.close();
        SQLiteDatabase dba = getReadableDatabase();
        String[] columns = { PASS_ID};
        String selection =  PASS_PASS + "=?";
        String[] selectionArgs = {password};
        Cursor cursor = dba.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        return  cursor;
    }

    public boolean checkUser(String pass_id, String password){
        String[] columns = { PASS_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = PASS_ID + "=?" + " and " + PASS_PASS + "=?";
        String[] selectionArgs = { pass_id, password };
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
