package com.untar.eaglevision;

import java.util.ArrayList;

import org.mixare.MixView;

import com.untar.eaglevision.CategoryFragment.OnCategorySelectedListener;

import Data.mgr.asyncLoader;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.google.android.gms.maps.SupportMapFragment;
public class MainActivity extends FragmentActivity implements OnCategorySelectedListener, OnPOISelectedListener, TabListener {

	CategoryFragment categories;
	ArrayList<Fragment> _POIFragments;
	ActionBar _actionBar;
	public static Context appContext;
	/** Called when the activity is first created. */
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
	    
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_activity);
		_POIFragments = new ArrayList<Fragment>();
		_POIFragments.add(new POIListFragment(/*Cursor cursor, */this));
		_POIFragments.add(new SupportMapFragment(/*Cursor cursor, OnPOISelectedListener this*/));
		_POIFragments.add(new MixView(/*Cursor cursor, OnPOISelectedListener this*/));
		appContext=getApplicationContext();
		 new asyncLoader().execute();
		_actionBar = getActionBar();
		_actionBar.addTab(_actionBar.newTab().setIcon(R.drawable.ic_tab_list).setTabListener(this));
		_actionBar.addTab(_actionBar.newTab().setIcon(R.drawable.ic_tab_map).setTabListener(this));
		_actionBar.addTab(_actionBar.newTab().setIcon(R.drawable.ic_tab_camera).setTabListener(this));
	    
	    categories = new CategoryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.startActivity_container, categories).commit();
    
	}

	@Override
	public void onCategorySelected(int position) {
		_actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        _actionBar.setDisplayHomeAsUpEnabled(true);
        _actionBar.setIcon(getResources().obtainTypedArray(R.array.CategoryIcons).getDrawable(position));
        _actionBar.setTitle(getResources().obtainTypedArray(R.array.CategoryTitles).getString(position));
        _actionBar.setSelectedNavigationItem(0);
		getSupportFragmentManager().beginTransaction().replace(R.id.startActivity_container, _POIFragments.get(0)).commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            this.getActionBar().setDisplayHomeAsUpEnabled(false);
	            _actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	            _actionBar.setIcon(R.drawable.ic_launcher);
	            _actionBar.setTitle(R.string.app_name);
	    		getSupportFragmentManager().beginTransaction().replace(R.id.startActivity_container, categories).commit();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onPOISelected(int position) {
//		((SupportMapFragment) _POIFragments.get(1)).setPoi(position);
//		_actionBar.setSelectedNavigationItem(1);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		if (_POIFragments.size() == 3)
		getSupportFragmentManager().beginTransaction().replace(R.id.startActivity_container, _POIFragments.get(tab.getPosition())).commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}
	
}
