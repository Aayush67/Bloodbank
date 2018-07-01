package com.example.bloodbank;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Phonefourdigit extends Activity {
EditText phoneid;
public static String phone;
public static String cdigit;
sqlliteclass _sqlliteclass;
SQLiteDatabase _database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phonefourdigit);
	}

	public void message(View view)
	{
		_sqlliteclass=new sqlliteclass(getApplicationContext());
		_database=_sqlliteclass.getReadableDatabase();
		phoneid=(EditText)findViewById(R.id.Phone_no);
		phone=phoneid.getText().toString().trim();
		
		Cursor c=_database.rawQuery("select * from Registration where Phone='"+phone+"'",null);
		int rowcnt=c.getCount();
		if( phone.length()==10)
		{	
			if(rowcnt==1)
			{
				Random rand = new Random();
				int  n = rand.nextInt(9999) + 1000;
				cdigit=n+"";
				/*Intent i=new Intent(android.content.Intent.ACTION_VIEW);
				i.putExtra("address", phone);
				i.putExtra("sms_body", cdigit);
				i.setType("vnd.android-dir/mms-sms");
				startActivity(i);*/
				SmsManager smsManager = SmsManager.getDefault();
		        smsManager.sendTextMessage(phone, null, cdigit, null, null);    
				Toast.makeText(getBaseContext(), "Four digit code sent to "+phone, Toast.LENGTH_SHORT).show();
				Intent I=new Intent(Phonefourdigit.this,Forgotpwd.class);
				startActivity(I);
				finish();
				
			}
			else
				Toast.makeText(getBaseContext(), "Enter Registered Number", Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(getBaseContext(), "Enter Valid Number", Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.phonefourdigit, menu);
		return true;
	}


}
