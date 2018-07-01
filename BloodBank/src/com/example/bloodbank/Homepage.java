package com.example.bloodbank;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;

public class Homepage extends Activity {
	sqlliteclass _sqliteclass;
	SQLiteDatabase _database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		ActionBar acnbar=getActionBar();
		String name=Login.useraccount;
		acnbar.setTitle("Welcome " +name);
		
		
		//Tab View
		ActionBar _actionbar=getActionBar();
		//ActionBar is abstract class and Tab is Static class
		ActionBar.Tab t1=_actionbar.newTab();
		//t1.setIcon(android.R.drawable.ic_menu_search);
		t1.setIcon(R.drawable.search);
		t1.setText(" Search"+"\n"+" Donor");
		
		//Listener for Tab1
		t1.setTabListener(new TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
				Searchdonor search=new Searchdonor();
				ft.replace(R.id.frame,search);
				
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		ActionBar.Tab t2=_actionbar.newTab();
		//t2.setIcon(android.R.drawable.btn_dialog);
		t2.setIcon(R.drawable.blood);
		t2.setText(" Blood Bank");
		
		
		t2.setTabListener(new TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
				//Fragment2 f2=new Fragment2();
				//ft.replace(R.id.frm1, f2);
				Bloodbank bloodbank=new Bloodbank();
				ft.replace(R.id.frame,bloodbank);
				
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ActionBar.Tab t3=_actionbar.newTab();
		t3.setIcon(R.drawable.profile);
		t3.setText(" My Details");
		
		t3.setTabListener(new TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				//Fragment3 f3=new Fragment3();
				//ft.replace(R.id.frm1, f3);
				Mydetails details=new Mydetails();
				ft.replace(R.id.frame,details);
				
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		_actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		_actionbar.addTab(t1);
		_actionbar.addTab(t2);
		_actionbar.addTab(t3);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.homepage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if(item.getItemId()==R.id.action_logout)
		{
			Intent I=new Intent(Homepage.this,Login.class);
			startActivity(I);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	
}
