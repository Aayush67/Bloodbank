package com.example.bloodbank;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration extends Activity {

	Spinner _bloodgrpid;
	CheckBox donor;
	ArrayList<String>bloodgrp;
	RadioGroup _radiogrp;
	Button register;
	RadioButton rbtn;
	sqlliteclass _sqlliteclass;
	Cursor c;
	SQLiteDatabase _database;
	String bloodgrp1,gender,Username,password,Email,Phone,Locality,city,state;
	EditText Usernameid,passwordid,Emailid,Phoneid,Localityid,cityid,stateid;
	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		_bloodgrpid=(Spinner)findViewById(R.id.Bloodgroup);
		bloodgrp=new ArrayList<String>(Arrays.asList("Select","A+","A-","B+","B-","AB+","AB-","O+","O-"));
		//ArrayAdapter<String> addbloodgrp=new ArrayAdapter<String>(Registration.this,android.R.layout.simple_spinner_dropdown_item,_bloodgrp);
		ArrayAdapter<String>addbloodgrp=new ArrayAdapter<String>(Registration.this,android.R.layout.simple_spinner_dropdown_item, bloodgrp);
	    _bloodgrpid.setAdapter(addbloodgrp);
	   
	    _radiogrp=(RadioGroup)findViewById(R.id.Gender);
	    Usernameid=(EditText)findViewById(R.id.username);
	    passwordid=(EditText)findViewById(R.id.password);
	    Emailid=(EditText)findViewById(R.id.Email);
	    Phoneid=(EditText)findViewById(R.id.Phone);
	    Localityid=(EditText)findViewById(R.id.locality);
	    cityid=(EditText)findViewById(R.id.City);
	    stateid=(EditText)findViewById(R.id.State);
	    register=(Button)findViewById(R.id.Register);
	    donor=(CheckBox)findViewById(R.id.checkBox1);
	    _sqlliteclass=new sqlliteclass(getApplicationContext());
	    
	    
	    register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				int selectid=_radiogrp.getCheckedRadioButtonId();
				rbtn=(RadioButton)findViewById(selectid);
			    gender=rbtn.getText().toString().trim();
			    Username=Usernameid.getText().toString().trim();
			    password=passwordid.getText().toString().trim();
			    Email=Emailid.getText().toString().trim();
			    Phone=Phoneid.getText().toString().trim();
			    Locality=Localityid.getText().toString().trim();
			    state=stateid.getText().toString().trim();
			    city=cityid.getText().toString().trim();
			    //Toast.makeText(getApplicationContext(),gender, Toast.l).show();
			   // Toast.makeText(getBaseContext(), gender+bloodgrp1, Toast.LENGTH_SHORT).show();
			   // finish();
			   if(Locality.equals("") || Username.equals("") || password.equals("") 
			    		|| Email.equals("")|| bloodgrp1.equals("Select") || Phone.equals("") || state.equals("") || city.equals(""))
			    {
			    	Toast.makeText(getApplicationContext(),"Some Field Is Empty",Toast.LENGTH_SHORT).show();
			    }
			    else
			    {
				    if (Email.matches(emailPattern) )
				    { 	
				    	int flag=0;
				    	for(int i=0;i<Phone.length();i++)
				    	{
				    		if(Phone.charAt(i)<'0' || Phone.charAt(i)>'9')
				    			flag=7;
				    	}
				    	if(Phone.length()==10 && flag==0)
				    	{
				    		if(password.length()<6)
				    			Toast.makeText(getApplicationContext(),"Enter a Strong 6 digit Alphanumeric Password ",Toast.LENGTH_SHORT).show();
				    		else
				    		{
				    			_database=_sqlliteclass.getReadableDatabase();
				    			c=_database.rawQuery("select * from Registration where Phone='"+Phone+"'", null);
				    			int rowcnt=c.getCount();
				    			if(rowcnt==0)
				    			{
				    				_database=_sqlliteclass.getWritableDatabase();
				    				_database.execSQL("insert into Registration (Username,Password,Email,Phone,Locality," +
				    						"City,State,Gender,BloodGroup) values ('"+Username+"','"+password+"','"+Email+"','"+Phone+"','"+Locality+"','"+city+"','"+state+"','"+gender+"','"+bloodgrp1+"') ");
				    				
				    				//Toast.makeText(getBaseContext(),"Blood:" +bloodgrp1+"gender"+gender, Toast.LENGTH_LONG).show();
				    				/*_database=_sqlliteclass.getReadableDatabase();
				    				Cursor c=_database.rawQuery("select * from Registration where Phone='"+Phone+"'", null);
				    				c.moveToFirst();
				    				Toast.makeText(getBaseContext(),"7 "+c.getString(7), Toast.LENGTH_LONG).show();
				    				//*/
				    				if(donor.isChecked())
				    				{
				    					_database.execSQL("insert into Donor (Username,Password,Email,Phone,Locality," +
					    						"City,State,Gender,BloodGroup) values ('"+Username+"','"+password+"','"+Email+"','"+Phone+"','"+Locality+"','"+city+"','"+state+"','"+gender+"','"+bloodgrp1+"') ");
				    					Toast.makeText(getBaseContext(),"You are Registered as Donor"+"\n"+" Now Login with your valid details", Toast.LENGTH_SHORT).show();
				    				}
				    				else
				    					Toast.makeText(getBaseContext(),"You are registered "+"\n"+" Now Login with your valid details", Toast.LENGTH_SHORT).show();
				    				
				    				Intent I=new Intent(Registration.this,Login.class);
				    				startActivity(I);
				    		
				    			}
	
				    			else
				    			{
				    				Toast.makeText(getBaseContext(),"Your Phone Number Is already Registered", Toast.LENGTH_SHORT).show();
				    			}
				    		}
				    	}
				    	else
				    		Toast.makeText(getApplicationContext(),"Enter Valid Number",Toast.LENGTH_SHORT).show();
				    }
				    else
				    	Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
			    
				}
				
			}
		});
	    
	    
	    
	    
	    
	    
	    
	    _bloodgrpid.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> bloodgrplist, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				bloodgrp1=bloodgrplist.getItemAtPosition(position).toString().trim();
				//Toast.makeText(getBaseContext(),bloodgrp1, Toast.LENGTH_SHORT).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}


}
