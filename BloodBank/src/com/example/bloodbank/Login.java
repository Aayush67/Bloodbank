package com.example.bloodbank;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	
	public static String useraccount;
	Button _login;
	EditText _phone,_password;
	public static String phone;
	String pwd;
	sqlliteclass _sqliteclass;
	SQLiteDatabase _database;
	Cursor c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		_phone=(EditText)findViewById(R.id.Phone_no);
		_password=(EditText)findViewById(R.id.Password);
		_sqliteclass=new sqlliteclass(getApplicationContext());
		_login=(Button)findViewById(R.id.Login);
		_phone.setText("");
		_password.setText("");
		_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
					phone=_phone.getText().toString().trim();
					pwd=_password.getText().toString().trim();
					if(phone.equals("") || pwd.equals(""))
						Toast.makeText(getBaseContext(), "Field Is Empty",Toast.LENGTH_SHORT).show();
					else
					{
						_database=_sqliteclass.getReadableDatabase();
						c=_database.rawQuery("select * from Registration where Phone='"+phone+"'",null);
						int rowcnt=c.getCount();
						if(rowcnt==0)
							Toast.makeText(getBaseContext(), "First register Your Number",Toast.LENGTH_SHORT).show();
						else
						{
							c=_database.rawQuery("select * from Registration where Phone='"+phone+"' and Password='"+pwd+"'",null);
							if(c.getCount()==1)
							{
								c.moveToFirst();
								useraccount=c.getString(0);
								Toast.makeText(getBaseContext(),"Welcome to Blood Bank "+ useraccount, Toast.LENGTH_SHORT).show();
								Intent I=new Intent(Login.this,Homepage.class);
								startActivity(I);
								finish();
							}
							else
								Toast.makeText(getBaseContext(),"Invalid Password", Toast.LENGTH_SHORT).show();
								
						}
							
					}
			
			}
		});
	}
	
	public void registration(View view)
	{
		Intent I=new Intent(Login.this,Registration.class);
		startActivity(I);
	}
	public void Forgot(View view)
	{
		Intent I=new Intent(Login.this,Phonefourdigit.class);
		startActivity(I);
	}

/*	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}*/
	


}
