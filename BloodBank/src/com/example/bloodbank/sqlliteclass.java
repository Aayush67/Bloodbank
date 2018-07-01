package com.example.bloodbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;

public class sqlliteclass extends SQLiteOpenHelper{

	public sqlliteclass(Context context) {
		super(context,"Blood_Bank", null, 7);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase _sql) {
		// TODO Auto-generated method stub
		_sql.execSQL("create table Registration(Username string,Password string," +
				"Email string,Phone string primary key,Locality string,City string,State string," +
				"Gender string,BloodGroup string)");
		_sql.execSQL("create table Donor(Username string,Password string," +
				"Email string,Phone string primary key,Locality string,City string,State string," +
				"Gender string,BloodGroup string)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase _sql, int arg1, int arg2) {
		// TODO Auto-generated method stub
		_sql.execSQL("drop table Registration");
		_sql.execSQL("drop table Donor");
		onCreate(_sql);
		
	}

}
