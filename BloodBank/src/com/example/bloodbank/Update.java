package com.example.bloodbank;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends  Fragment
{
	Button updateid;
	Button cancelid;
	sqlliteclass _sqlliteclass;
	SQLiteDatabase _database;
	CheckBox donor,ndonor;
	Cursor c;
	int rowcnt;
	EditText nameid,genderid,emailid,bloodid,phoneid,localityid,cityid,stateid,pwdid;
	String bloodgrp1,gender,Username,password,Email,Phone,Locality,city,state;
	FragmentTransaction ft;
	String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, false);
		return inflater.inflate(R.layout.activity_update,container,false);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Activity act=getActivity();
		
		
		nameid=(EditText)act.findViewById(R.id.Name);
		//genderid=(EditText)act.findViewById(R.id.Gender);
		emailid=(EditText)act.findViewById(R.id.Email);
		//bloodid=(EditText)act.findViewById(R.id.Blood);
		//phoneid=(EditText)act.findViewById(R.id.Phone);
		localityid=(EditText)act.findViewById(R.id.Locality);
		cityid=(EditText)act.findViewById(R.id.City);
		stateid=(EditText)act.findViewById(R.id.State);
		pwdid=(EditText)act.findViewById(R.id.Password);
		String phone=Login.phone;
		
		_sqlliteclass=new sqlliteclass(getActivity().getApplicationContext());
		_database=_sqlliteclass.getReadableDatabase();
		c=_database.rawQuery("select * from Registration where Phone='"+phone+"'",null);
		c.moveToFirst();
		
		
		nameid.setText(c.getString(0));
	//	genderid.setText(c.getString(7));
		emailid.setText(c.getString(2));
	//	bloodid.setText(c.getString(8));
		
		//bloodgroup1=c.getString(8).trim();
		
	///	phoneid.setText(c.getString(3));
		localityid.setText(c.getString(4));
		cityid.setText(c.getString(5));
		stateid.setText(c.getString(6));
		pwdid.setText(c.getString(1));
		
		
		//changed
		gender=c.getString(7).trim();
		Phone=c.getString(3).trim();
		bloodgrp1=c.getString(8).trim();

		
		
		
		updateid=(Button)act.findViewById(R.id.Update);
		cancelid=(Button)act.findViewById(R.id.Cancel);
		cancelid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			/*	Edit edit1=new Edit();
				ft=getFragmentManager().beginTransaction();
				ft.replace(R.id.frame2, edit1);
				ft.commit();*/
				Mydetails edit1=new Mydetails();
				ft=getFragmentManager().beginTransaction();
				ft.replace(R.id.frame, edit1);
				ft.commit();
				
			}
		});
	
			
		updateid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Activity act=getActivity();
				//flag=0;
				int flag2=0,flag3=0,flag4=0,flag5=0,flag6=0;
				String flag21="Data Updated";
				String flag31="Data Updated Added to Donor List";
				String flag41="You are Already a Donor";
				String flag51="You are Not in a Donor List";
				String flag61="You are Removed from Donor List";
				
				//Toast.makeText(getActivity().getBaseContext(), "Enter Your details",Toast.LENGTH_LONG).show();
				nameid=(EditText)act.findViewById(R.id.Name);
				//genderid=(EditText)act.findViewById(R.id.Gender);
				emailid=(EditText)act.findViewById(R.id.Email);
				//bloodid=(EditText)act.findViewById(R.id.Blood);
				//phoneid=(EditText)act.findViewById(R.id.Phone);
				localityid=(EditText)act.findViewById(R.id.Locality);
				cityid=(EditText)act.findViewById(R.id.City);
				stateid=(EditText)act.findViewById(R.id.State);
				pwdid=(EditText)act.findViewById(R.id.Password);
				donor=(CheckBox)act.findViewById(R.id.checkBox2);
				
				ndonor=(CheckBox)act.findViewById(R.id.checkBox3);
				
				
			//	_sqlliteclass=new sqlliteclass(getActivity().getApplicationContext());
				
				//Getting data
				  //gender=genderid.getText().toString().trim();
				  Username=nameid.getText().toString().trim();
				  password=pwdid.getText().toString().trim(); 
				  Email=emailid.getText().toString().trim();
				  //Phone=phoneid.getText().toString().trim();
				  Locality=localityid.getText().toString().trim();
				  state=stateid.getText().toString().trim();
				  city=cityid.getText().toString().trim();
				//  bloodgrp1=bloodid.getText().toString().trim();
			
if(!donor.isChecked() || !ndonor.isChecked())
{	
	//if(bloodgrp1.equals(Mydetails.bloodgroup1))
	//{	
			//if(Phone.equals(Login.phone))
			//{	
				if(Locality.equals("") || Username.equals("") || password.equals("") 
			    		|| Email.equals("")|| password.equals("") || Phone.equals("") || gender.equals("")|| state.equals("") || city.equals(""))
			    {
			    	Toast.makeText(act.getApplicationContext(),"Some Field Is Empty",Toast.LENGTH_SHORT).show();
			    }
			    else
			    {
				    if (Email.matches(emailPattern) )
				    { 	
				    	//int flag=0;
				    //	for(int i=0;i<Phone.length();i++)
				    //	{
				    //		if(Phone.charAt(i)<'0' || Phone.charAt(i)>'9')
				    //			flag=7;
				    //	}
				    	//if(Phone.length()==10 && flag==0)
				    	//{
				    		if(password.length()<6)
				    			Toast.makeText(act.getApplicationContext(),"Enter a Strong 6 digit Alphanumeric Password ",Toast.LENGTH_SHORT).show();
				    		else
				    		{
					    		//	_database=_sqlliteclass.getReadableDatabase();
					    		//	c=_database.rawQuery("select * from Registration where Phone='"+Login.phone+"'", null);
					    		//	int rowcnt=c.getCount();
					    		//	if(rowcnt==1)
					    		//	{
				    					flag2=1;
					    				_database=_sqlliteclass.getWritableDatabase();
					    				_database.execSQL("Update Registration set Username='"+Username+"',Password='"+password+"',Email='"+Email+"',Locality='"+Locality+"'," +
					    						"City='"+city+"',State='"+state+"' where Phone='"+Phone+"'");
					    				
					    				_database=_sqlliteclass.getReadableDatabase();
					    				c=_database.rawQuery("select * from Donor where Phone='"+Phone+"'", null);
					    				 rowcnt=c.getCount();
					    				if(rowcnt==1)
					    				{
					    					_database=_sqlliteclass.getWritableDatabase();
					    					_database.execSQL("Update Donor set Username='"+Username+"',Password='"+password+"',Email='"+Email+"',Locality='"+Locality+"'," +
						    						"City='"+city+"',State='"+state+"' where Phone='"+Phone+"'");
					    					
					    				}
					    				//Toast.makeText(act.getBaseContext(),"Data Updated", Toast.LENGTH_LONG).show();
					    			//	val="Data Updated ";
					    				/*if(flag==0)
				    					{
				    						
				    						Mydetails edit1=new Mydetails();
				    						ft=getFragmentManager().beginTransaction();
				    						ft.replace(R.id.frame, edit1);
				    						ft.commit();
				    						flag=1;
				    					}*/
					    				/*_database=_sqlliteclass.getReadableDatabase();
					    				Cursor c=_database.rawQuery("select * from Registration where Phone='"+Phone+"'", null);
					    				c.moveToFirst();
					    				Toast.makeText(getBaseContext(),"7 "+c.getString(7), Toast.LENGTH_LONG).show();
					    				//*/
					    				if(donor.isChecked())
					    				{
					    					_database=_sqlliteclass.getReadableDatabase();
							    			c=_database.rawQuery("select * from Donor where Phone='"+Phone+"'", null);
							    			rowcnt=c.getCount();
							    			if(rowcnt==0)
							    			{	
							    				
							    				_database.execSQL("insert into Donor (Username,Password,Email,Phone,Locality," +
							    						"City,State,Gender,BloodGroup) values ('"+Username+"','"+password+"','"+Email+"','"+Phone+"','"+Locality+"','"+city+"','"+state+"','"+gender+"','"+bloodgrp1+"') ");
							    				
						    					/*_database.execSQL("Update Registration set Username='"+Username+"',Password='"+password+"',Email='"+Email+"',Phone='"+Phone+"',Locality='"+Locality+"'," +
							    						"City='"+city+"',State='"+state+"',Gender='"+gender+"',BloodGroup='"+bloodgrp1+"' where Phone='"+Phone+"'");*/
						    					//Toast.makeText(act.getBaseContext(),"You are added to Donor List", Toast.LENGTH_SHORT).show();
						    				//	val+="Added to Donor List";
						    					flag3=1;
						    				/*	if(flag==0)
						    					{
						    						
						    						Mydetails  edit1=new Mydetails();
						    						ft=getFragmentManager().beginTransaction();
						    						ft.replace(R.id.frame, edit1);
						    						ft.commit();
						    						flag=1;
						    					}*/
						    					//Toast.makeText(act.getBaseContext(),val, Toast.LENGTH_SHORT).show();
							    			}
							    			else//donor.isChecked()
							    			{
							    				//flag1=2;
							    				flag4=1;
							    				//Toast.makeText(getActivity().getBaseContext(),"You are Already a Donor",Toast.LENGTH_SHORT).show();
							    			}
							    			
					    				}
					    				if(ndonor.isChecked())
					    				{
					    					_database=_sqlliteclass.getReadableDatabase();
							    			c=_database.rawQuery("select * from Donor where Phone='"+Phone+"'", null);
							    			rowcnt=c.getCount();
							    			if(rowcnt==0)
							    			{	
							    				flag5=1;
							    			//	Toast.makeText(getActivity().getBaseContext(),"You are Not in a Donor List",Toast.LENGTH_SHORT).show();
							    			}
							    			else
							    			{
							    				_database=_sqlliteclass.getWritableDatabase();
							    				_database.execSQL("delete from Donor where Phone='"+Phone+"'");
							    			//	Toast.makeText(getActivity().getBaseContext(), "You are Removed from Donor List",Toast.LENGTH_SHORT).show();
							    				flag6=1;
							    			}
					    				}
					    				
					    				
					    				
					    				//Intent I=new Intent(Registration.this,Login.class);
					    				//startActivity(I);
					    				//if(flag1==0)
					    				//	Toast.makeText(act.getBaseContext(),val, Toast.LENGTH_SHORT).show();
					    				
					    				if(flag6==1)
					    					Toast.makeText(act.getBaseContext(),flag61, Toast.LENGTH_SHORT).show();
					    				else if(flag5==1)
					    					Toast.makeText(act.getBaseContext(),flag51, Toast.LENGTH_SHORT).show();
					    				else if(flag4==1)
					    					Toast.makeText(act.getBaseContext(),flag41, Toast.LENGTH_SHORT).show();
					    				else if(flag3==1)
					    					Toast.makeText(act.getBaseContext(),flag31, Toast.LENGTH_SHORT).show();
					    				else if(flag2==1)
					    					Toast.makeText(act.getBaseContext(),flag21, Toast.LENGTH_SHORT).show();
					    		//	}
		
					    			//else//rowcnt==1
					    			//{
					    			//	Toast.makeText(act.getBaseContext(),"Your Phone Number Is Not Registered", Toast.LENGTH_SHORT).show();
					    			//}
					    				
					    				
					    			//changing to mydetails activity after updation
					    				Mydetails edit1=new Mydetails();
			    						ft=getFragmentManager().beginTransaction();
			    						ft.replace(R.id.frame, edit1);
			    						ft.commit();
			    						//flag=1;
					    				
					    		}
					  //  	}
				    	//else//2nd if Phone.length()==10 && flag==0
				    		//Toast.makeText(act.getApplicationContext(),"Enter Valid Number",Toast.LENGTH_SHORT).show();
				    }
				    else//1st if Email.matches(emailPattern)
				    	Toast.makeText(act.getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
			    
				}
			//}
		//	else
			//	Toast.makeText(act.getApplicationContext(),"Entered Wrong Number",Toast.LENGTH_SHORT).show();
				
			
		//}
	// else
	// {
	//	Toast.makeText(act.getBaseContext(),"Changing BloodGroup not Allowed",Toast.LENGTH_SHORT).show();
	// }
}
else
{
	Toast.makeText(act.getBaseContext(),"Select a Single Choice",Toast.LENGTH_SHORT).show();
}
			}
	
		});
		
		super.onStart();
	}

}
