package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String dbname = "Bankdb";
    private static final int version= 1;

    public DatabaseHelper(Context context){
        super(context, dbname , null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       //table creation
       String sql = "CREATE TABLE USER_DETAILS (_id INTEGER , NAME TEXT, EMAIL VARCHAR PRIMARY KEY,BALANCE INTEGER,PHONENUMBER TEXT,ACCOUNT VARCHAR)";
       db.execSQL(sql);
       String sql_tr = "CREATE TABLE TRANSACT_DETAILS (transact_id INTEGER , FROMNAME TEXT, TONAME TEXT, AMOUNT INTEGER, STATUS TEXT)";
       db.execSQL(sql_tr);
       //to insert data
        insertData("Rohan","rohanmore76@gmail.com",5000,"9255572698","XXXXXXXXXXXX4735",db);
        insertData("Sahil","sahilp33200@gmail.com",15000,"802339459","XXXXXXXXXXXX1344",db);
        insertData("Siddhesh","sidblackmamba@gmail.com",25000,"2225137174 ","XXXXXXXXXXXX3234",db);
        insertData("Stalin","soviet.russia@gmail.com",56000,"2228073554","XXXXXXXXXXXX1284",db);
        insertData("Balaji","balajiyadav56@gmail.com",54000,"9257472698","XXXXXXXXXXXX1534",db);
        insertData("Ganesh","ganesggurao46@gmail.com",30000,"9834572698","XXXXXXXXXXXX1294",db);
        insertData("Anusha","anushag0@gmail.com",70000,"999972698","XXXXXXXXXXXX1034",db);
        insertData("Shruti","shruti318@gmail.com",85000,"925559876","XXXXXXXXXXXX4234",db);
        insertData("Yogesh","yogesh.omnipresent@gmail.com",25000,"9255234568","XXXXXXXXXXXX1534",db);
        insertData("Abhilash","abhilash.coco@gmail.com",45000,"9234567698","XXXXXXXXXXXX8734",db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_DETAILS");
        db.execSQL("DROP TABLE IF EXISTS TRANSACT_DETAILS");
        onCreate(db);
    }
    public Cursor ReadAllData(){
        SQLiteDatabase db =getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USER_DETAILS",null);
        return cursor;
    }
    public Cursor readParticularData(String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "select * from USER_DETAILS where Email = '"+Email + "'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public Cursor ReadSelectedData(String Email){
        SQLiteDatabase db = getWritableDatabase();
        String select ="select * from USER_DETAILS where email <> '"+Email + "'";
        Cursor c = db.rawQuery(select,null);
        return c;
    }
    public void UpdateAmount(String Email,String amount){
        SQLiteDatabase db =this.getWritableDatabase();
        String select="UPDATE USER_DETAILS set BALANCE = '"+ amount +"' where Email = '"+Email+"'";
        db.execSQL(select);
    }

    private void insertData(String name, String email, int balance,String phoneNumber,String account,SQLiteDatabase database){
        ContentValues values= new ContentValues();
        values.put("NAME",name);
        values.put("EMAIL",email);
        values.put("PHONENUMBER",phoneNumber);
        values.put("ACCOUNT",account);
        values.put("BALANCE",balance);
        database.insert("USER_DETAILS",null,values);
    }
    public void insertTransferData(String from_name, String to_name, int amount, String Status){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("FROMNAME",from_name);
        values.put("TONAME",to_name);
        values.put("AMOUNT",amount);
        values.put("STATUS",Status);
        database.insert("TRANSACT_DETAILS",null,values);
    }


    public Cursor Read_Transfer_amount_Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "select * from TRANSACT_DETAILS";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }
}
