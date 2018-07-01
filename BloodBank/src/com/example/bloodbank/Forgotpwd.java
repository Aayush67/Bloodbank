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
import android.widget.TextView;
import android.widget.Toast;

public class Forgotpwd extends Activity {
Button pwd;
EditText phoneid,pwdid,digitid;
String Phone,password,digit,cdigit;
sqlliteclass _sqliteclass;
SQLiteDatabase _database;
TextView tv;
Cursor c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpwd);
		pwd=(Button)findViewById(R.id.Login);
		phoneid=(EditText)findViewById(R.id.Phone_no);
		phoneid.setText(Phonefourdigit.phone);

		pwdid=(EditText)findViewById(R.id.Password);
		digitid=(EditText)findViewById(R.id.fourdigit);
		tv=(TextView)findViewById(R.id.textView2);
		tv.setText("<-- Back to Login");
		_sqliteclass=new sqlliteclass(getApplicationContext());
		pwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Phone=phoneid.getText().toString().trim();
				//phoneid.setText(Phonefourdigit.phone);
				Phone=phoneid.getText().toString().trim();
				password=pwdid.getText().toString().trim();
				digit=digitid.getText().toString().trim();
				cdigit=Phonefourdigit.cdigit;
				if(Phonefourdigit.phone.equals(Phone))
				{
						if(Phone.equals("") || password.equals("") || digit.equals("") )
							Toast.makeText(getBaseContext(),"Field is Empty", Toast.LENGTH_SHORT).show();
						else
						{
							if(password.length()<6)
								Toast.makeText(getApplicationContext(),"Enter a Strong 6 digit Alphanumeric Password ",Toast.LENGTH_SHORT).show();
							else
							{
								if(digit.equals(cdigit))
								{	
									_database=_sqliteclass.getReadableDatabase();
									c=_database.rawQuery("select * from Registration where Phone='"+Phone+"'",null);
									int rowcnt=c.getCount();
									if(rowcnt==0)
										Toast.makeText(getBaseContext(), "First Register Your Number",Toast.LENGTH_SHORT).show();
									else
									{
										
										_database=_sqliteclass.getWritableDatabase();
										_database.execSQL("update Registration set Password='"+password+"' where Phone='"+Phone+"'");
										_database.execSQL("update Donor set Password='"+password+"' where Phone='"+Phone+"'");
										Toast.makeText(getBaseContext(), "Password Reset",Toast.LENGTH_SHORT).show();
										
									}
								}
								else
									Toast.makeText(getBaseContext(), "Code doesn't Match",Toast.LENGTH_SHORT).show();
							}
						}
				}
				else
					Toast.makeText(getBaseContext(), "Password Reset allowed only for Registered"+"\n"+"  Phone No to which the Code was sent",Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	public void movetologin(View view)
	{
		Intent I=new Intent(Forgotpwd.this,Login.class);
		startActivity(I);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.forgotpwd, menu);
		return true;
	}

}
