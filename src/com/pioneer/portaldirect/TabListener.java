package com.pioneer.portaldirect;

import com.pioneer.sqlite.helper.DatabaseHelper;

import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
 
public class TabListener implements ActionBar.TabListener {
 
    Fragment fragment;
 
    public TabListener(Fragment fragment) {
        this.fragment = fragment;
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    	int tabSelected = tab.getPosition();
    	GlobalVariables gv = GlobalVariables.getInstance();
    	Context context = null;
    	DatabaseHelper db = DatabaseHelper.getInstance(context);
    	
    	if(tabSelected > 0){
    		if(gv.getQuoteID().equals("")){
    			gv.setQuoteDoesNotExist(true);
    		}
    	}
    	    	
    	ft.replace(R.id.fragment_container, fragment);
    	
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
 
    }
}
