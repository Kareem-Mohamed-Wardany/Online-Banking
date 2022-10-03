package com.example.onlinebanking;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DatabaseName = "UsersData";
    SQLiteDatabase UsersData;

    public Database(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table UsersData(Id integer primary key," +
                " Name text not null, Password text not null, Phone text not null, Mail text not null, Balance integer not null)");

    }

   @Override
    public void onUpgrade(SQLiteDatabase db, int oldverion, int newverion) {
        db.execSQL("drop table if exists UsersData");
        onCreate(db);
    }

    public void adduser(String Name, String Password, String Phone, String Mail, int Balance) {
        ContentValues row = new ContentValues();
        row.put("Name", Name);
        row.put("Password", Password);
        row.put("Phone", Phone);
        row.put("Mail", Mail);
        row.put("Balance", Balance);
        UsersData = getWritableDatabase();
        UsersData.insert("UsersData", null, row);
        UsersData.close();
    }

    public String getUserBalance(String Name) {
        UsersData =  getReadableDatabase();
        String[] uname = {Name};
        @SuppressLint("Recycle") Cursor cursor = UsersData.rawQuery("Select Balance from UsersData where name like ?", uname);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public String getUserMail(String Name)
    {
        UsersData =  getReadableDatabase();
        String[] uname={Name};
        @SuppressLint("Recycle") Cursor cursor=UsersData.rawQuery("Select Mail from UsersData where name like ?",uname);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public String getUserPassword(String Name)
    {
        UsersData =  getReadableDatabase();
        String[] uname={Name};
        @SuppressLint("Recycle") Cursor cursor=UsersData.rawQuery("Select Password from UsersData where name like ?",uname);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public boolean checkuser(String userName,String userPassword) {
        UsersData =  getReadableDatabase();
        boolean flag;
        try (Cursor cursor = UsersData.rawQuery("Select Name, Password from UsersData where Name=? and Password = ?", new String[]{userName, userPassword})) {
            if (cursor.getCount()>0)
                flag = true;
            else
                flag = false;
        }
        catch (SQLiteException e)
        {
            flag = false;
        }
        return flag;
    }

    public String getUserPhone(String Name) {
        UsersData =  getReadableDatabase();
        String[] uname = {Name};
        @SuppressLint("Recycle") Cursor cursor = UsersData.rawQuery("Select Phone from UsersData where name like ?", uname);
        cursor.moveToFirst();
        return cursor.getString(0);

    }

    public void updateBalance(String Name, float balance)
    {
      UsersData = getWritableDatabase();
      ContentValues row = new ContentValues();
      row.put("balance",balance);
      UsersData.update("UsersData",row, "Name like ?",new String[]{Name});
      UsersData.close();
    }
    public void updateEmail(String Name, String email)
    {
        UsersData = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("Mail",email);
        UsersData.update("UsersData",row,"Name like ?", new String[]{Name});
        UsersData.close();
    }
    public void updatePhone(String Name, String phone)
    {
        UsersData = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("Phone",phone );
        UsersData.update("UsersData", row,"Name like ?", new String[]{Name});
        UsersData.close();
    }
    public void updatePassword(String Name, String password)
    {
        UsersData = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("Password", password);
        UsersData.update("UsersData", row,"Name like ?", new String[]{Name});
        UsersData.close();
    }
}