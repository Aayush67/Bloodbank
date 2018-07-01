package com.example.bloodbank;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Bloodbank extends Fragment
{
	sqlliteclass _sqlliteclass;
	SQLiteDatabase _database;
	TextView abpid,abnid,apid,anid,bpid,bnid,opid,onid;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, false);
		return inflater.inflate(R.layout.activity_bloodbank,container,false);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Activity act=getActivity();
		_sqlliteclass=new sqlliteclass(act.getApplicationContext());
		_database=_sqlliteclass.getReadableDatabase();
		
		abpid=(TextView)act.findViewById(R.id.ABP);
		abnid=(TextView)act.findViewById(R.id.ABN);
		apid=(TextView)act.findViewById(R.id.AP);
		anid=(TextView)act.findViewById(R.id.AN);
		bpid=(TextView)act.findViewById(R.id.BP);
		bnid=(TextView)act.findViewById(R.id.BN);
		opid=(TextView)act.findViewById(R.id.OP);
		onid=(TextView)act.findViewById(R.id.ON);
		
		Cursor c=_database.rawQuery("select * from Donor", null);
		int abp=0,abn=0,ap=0,an=0,bp=0,bn=0,op=0,on=0;
		while(!c.isLast())
		{
				c.moveToNext();//to read first row
			if(!c.getString(3).equals(Login.phone))	
			{	
				if(c.getString(8).equals("AB+"))
					abp++;
				else if(c.getString(8).equals("AB-"))
					abn++;
				else if(c.getString(8).equals("A+"))
					ap++;
				else if(c.getString(8).equals("A-"))
					an++;
				else if(c.getString(8).equals("B+"))
					bp++;
				else if(c.getString(8).equals("B-"))
					bn++;
				else if(c.getString(8).equals("O+"))
					op++;
				else if(c.getString(8).equals("O-"))
					on++;
			}
		}
		abpid.setText(abp+"");
		abnid.setText(abn+"");
		apid.setText(ap+"");
		anid.setText(an+"");
		bpid.setText(bp+"");
		bnid.setText(bn+"");
		opid.setText(op+"");
		onid.setText(on+"");
		
		
		super.onStart();
	}

}
