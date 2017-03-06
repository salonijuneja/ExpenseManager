package com.example.android.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Currency;


public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="ledger.db";
    public static final String TABLE_NAME="expense_table";
    public static final String COL_1="Id";
    public static final String COL_2="Day";
    public static final String COL_3="Week";
    public static final String COL_4="Month";
    public static final String COL_5="Year";
    public static final String COL_6="Category";
    public static final String COL_7="Income";
    public static final String COL_8="Expense";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(Id INTEGER PRIMARY KEY AUTOINCREMENT ,Day INTEGER , Week INTEGER , Month INTEGER,Year INTEGER, Category STRING,Income DOUBLE, Expense DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME );
        onCreate(db);
    }
    public boolean insertData(int day,int week,int month,int year,String category,double income,double expense)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,day);
        cv.put(COL_3,week);
        cv.put(COL_4,month);
        cv.put(COL_5,year);
        cv.put(COL_6,category);
        cv.put(COL_7,income);
        cv.put(COL_8, expense);
        long x=db.insert(TABLE_NAME,null,cv);
        if(x==-1)
            return false;
        else
            return true;
            }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from "+TABLE_NAME,null);
        return c;

    }
    public  Cursor getRequiredData(String type,int value)
    {
        String add="";
        if(type.equals("month"))
            add=COL_4+" = "+value;
        else if(type.equals("year"))
            add=COL_5+" = "+value;
        else if(type.equals("day"))
            add=COL_2+" = "+value;
        else if(type.equals("week"))
            add=COL_3+" = "+value;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c1=db.rawQuery("select * from "+TABLE_NAME+" where "+add,null);
        return c1;
    }
}
