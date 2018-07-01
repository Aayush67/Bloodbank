package com.example.bloodbank;

import java.util.ArrayList;
import java.util.Arrays;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Searchdonor extends Fragment
{
	Spinner _bloodgrpid;
	ArrayList<String>bloodgrp;
	String bloodgroup;
	sqlliteclass _sqlliteclass;
	SQLiteDatabase _database;
	ListView grouplist;
	ArrayList<String>bloodlist;
	ArrayAdapter <String> _bloodadpt;
	AlertDialog.Builder builder;
	String phoneno,name;
	String splt[];
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, false);
		return inflater.inflate(R.layout.activity_searchdonor,container,false);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Activity act=getActivity();
		_bloodgrpid=(Spinner)act.findViewById(R.id.Bloodgroup);
		bloodgrp=new ArrayList<String>(Arrays.asList("Select","A+","A-","B+","B-","AB+","AB-","O+","O-"));
		ArrayAdapter<String>addbloodgrp=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bloodgrp);
		grouplist=(ListView)act.findViewById(R.id.listView1);
		//ArrayAdapter<String>addbloodgrp=new ArrayAdapter<String>(getActivity().getBaseContext().this,android.R.layout.simple_spinner_dropdown_item, bloodgrp);
	   bloodlist=new ArrayList<String>();
		_bloodgrpid.setAdapter(addbloodgrp);
	    _sqlliteclass=new sqlliteclass(getActivity().getApplicationContext());
	    
	    _bloodgrpid.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> list, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				bloodgroup=list.getItemAtPosition(position).toString().trim();
				//Toast.makeText(getActivity(), bloodgroup, Toast.LENGTH_SHORT).show();
				_database=_sqlliteclass.getReadableDatabase();
				String val;
				Cursor c=_database.rawQuery("select * from Donor where BloodGroup='"+bloodgroup+"'",null);
				bloodlist.removeAll(bloodlist);
				 _bloodadpt=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,bloodlist);
			      grouplist.setAdapter(_bloodadpt);
				int rowcnt=c.getCount();
				int a=1;
				int flag=0;
				if(rowcnt>0)
				{	
					while(!c.isLast())
					{
						val="";
						c.moveToNext();//to read first row
						
						//Toast.makeText(getActivity(), val, Toast.LENGTH_SHORT).show();
						if(!Login.phone.equals(c.getString(3)))
						{	
							val=a+"."+"			"+c.getString(0).trim()+"			"+c.getString(3).trim()+"		"+"Contact";
							bloodlist.add(val);
							a++;
							flag=1;
						}	
					
					}
				
				 _bloodadpt=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,bloodlist);
			        grouplist.setAdapter(_bloodadpt);
				}
				else
				{
					if(!bloodgroup.equals("Select"))
						Toast.makeText(getActivity(), bloodgroup+" not available", Toast.LENGTH_SHORT).show();
				}
				if(flag==0 && !bloodgroup.equals("Select"))
					Toast.makeText(getActivity(), bloodgroup+" not available", Toast.LENGTH_SHORT).show();
					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    grouplist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> list1, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
					String value=list1.getItemAtPosition(pos).toString();
					splt=value.split("\\s+");//For multiple spaces
					//Toast.makeText(getActivity(),splt[2],Toast.LENGTH_SHORT).show();
					phoneno=splt[2];
					name=splt[1];
					builder=new AlertDialog.Builder(getActivity());
					builder.setMessage(name+"\n"+phoneno).setTitle("Contact").setIcon(R.drawable.phone);
					
					
					builder.setPositiveButton("Call",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							//arg0.finish();
						
							Intent phonecallIntent=new Intent(Intent.ACTION_CALL);
							phonecallIntent.setData(Uri.parse("tel:"+phoneno));
							//Uri is used to show the path of address
							//tel: is predefined key cannot be substituted
							startActivity(phonecallIntent);
						}
					} );
			
					
					builder.setNegativeButton("Message", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							//dialog.cancel();
							_database=_sqlliteclass.getReadableDatabase();
							Cursor result=_database.rawQuery("select * from Donor where Phone='"+phoneno+"'",null);
							result.moveToFirst();
							String address="\n"+result.getString(4)+", "+result.getString(5)+"		"+"\n"+result.getString(6)+"			";
							SmsManager smsManager = SmsManager.getDefault();
					       //Replace number 87746 by phoneno
							smsManager.sendTextMessage("87746", null,"Urgent "+bloodgroup+" Blood required"+"\n"+"ADDRESS		"+address, null, null);    
							Toast.makeText(getActivity(), "Message Send to "+phoneno, Toast.LENGTH_SHORT).show();
							
						}
					});
					
					builder.setNeutralButton("Cancel",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							//Toast.makeText(getActivity(), "Cancel is clicked", Toast.LENGTH_LONG).show();
						}
					});
					
					
					AlertDialog alert=builder.create();
					alert.setTitle("Contact Details");
					alert.show();
				
			}
		});
	    
		super.onStart();
	}

}
