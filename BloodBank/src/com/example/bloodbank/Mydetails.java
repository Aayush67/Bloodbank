package com.example.bloodbank;



import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mydetails extends Fragment
{
	public static String bloodgroup1;
	sqlliteclass _sqlliteclass;
	SQLiteDatabase _database;
	TextView nameid,genderid,emailid,bloodid,phoneid,localityid,cityid,stateid,pwdid;
	FragmentTransaction ft;
	Button btn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, false);
		return inflater.inflate(R.layout.activity_mydetails,container,false);
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Activity act=getActivity();
		
		nameid=(TextView)act.findViewById(R.id.Name);
		genderid=(TextView)act.findViewById(R.id.Gender);
		emailid=(TextView)act.findViewById(R.id.Email);
		bloodid=(TextView)act.findViewById(R.id.Blood);
		phoneid=(TextView)act.findViewById(R.id.Phone);
		localityid=(TextView)act.findViewById(R.id.Locality);
		cityid=(TextView)act.findViewById(R.id.City);
		stateid=(TextView)act.findViewById(R.id.State);
		pwdid=(TextView)act.findViewById(R.id.Password);
		btn=(Button)act.findViewById(R.id.Edit);
		_sqlliteclass=new sqlliteclass(act.getApplicationContext());
		_database=_sqlliteclass.getReadableDatabase();
		String phone=Login.phone;
		Cursor c=_database.rawQuery("select * from Registration where Phone='"+phone+"'",null);
		c.moveToFirst();
		nameid.setText(c.getString(0));
		genderid.setText(c.getString(7));
		emailid.setText(c.getString(2));
		bloodid.setText(c.getString(8));
		
		bloodgroup1=c.getString(8).trim();
		
		phoneid.setText(c.getString(3));
		localityid.setText(c.getString(4));
		cityid.setText(c.getString(5));
		stateid.setText(c.getString(6));
		pwdid.setText(c.getString(1));
		
		
		//Bloodbank bloodbank=new Bloodbank();
		//ft.replace(R.id.frame,bloodbank);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Update edit1=new Update();
				ft=getFragmentManager().beginTransaction();
				ft.replace(R.id.frame, edit1);
				ft.commit();
				
			}
		});
	
		
		
		
		super.onStart();
	}

	
}
