package com.pioneer.portaldirect;

import com.pioneer.sqlite.helper.DatabaseHelper;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;

public class QuoteTabActivity extends Activity {
		
	// Declare Tab Variable
	ActionBar.Tab Tab1, Tab2, Tab3, Tab4, Tab5;
	Fragment fragmentTab1 = new QuoteApplicantActivity();
	Fragment fragmentTab2 = new QuoteDriverActivity();
	Fragment fragmentTab3 = new QuoteVehicleListActivity();
	Fragment fragmentTab4 = new QuoteCoverageActivity();
	Fragment fragmentTab5 = new QuoteReviewActivity();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quote_tab);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		GlobalVariables gv = GlobalVariables.getInstance();
				 
		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(true);
		
		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(true);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
		String[] tabIcons = db.getTabIconDataForQuote(gv.getQuoteID());
		
		Tab1 = actionBar.newTab().setText("Applicant");
		Tab2 = actionBar.newTab().setText("Driver List");
		Tab3 = actionBar.newTab().setText("Vehicle List");
		Tab4 = actionBar.newTab().setText("Coverages");
		Tab5 = actionBar.newTab().setText("Review");
		
		if(tabIcons[0].equals("0")){Tab1.setIcon(R.drawable.question_white);}else{Tab1.setIcon(R.drawable.tick_white);}
		if(tabIcons[1].equals("0")){Tab2.setIcon(R.drawable.question_white);}else{Tab2.setIcon(R.drawable.tick_white);}
		if(tabIcons[2].equals("0")){Tab3.setIcon(R.drawable.question_white);}else{Tab3.setIcon(R.drawable.tick_white);}
		if(tabIcons[3].equals("0")){Tab4.setIcon(R.drawable.question_white);}else{Tab4.setIcon(R.drawable.tick_white);}
		if(tabIcons[0].equals("1") && tabIcons[1].equals("1") && tabIcons[2].equals("1") && tabIcons[3].equals("1"))
			{Tab5.setIcon(R.drawable.tick_white);}
		else
			{Tab5.setIcon(R.drawable.question_white);}

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));
		Tab4.setTabListener(new TabListener(fragmentTab4));
		Tab5.setTabListener(new TabListener(fragmentTab5));
		
		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
		actionBar.addTab(Tab4);
		actionBar.addTab(Tab5);
		
		switch(gv.getTabID()){
		case 1:
			actionBar.selectTab(Tab1);
		break;
		case 2:
			actionBar.selectTab(Tab2);
		break;
		case 3:
			actionBar.selectTab(Tab3);
		break;
		case 4:
			actionBar.selectTab(Tab4);
		break;
		case 5:
			actionBar.selectTab(Tab5);
		break;
		}
		
		gv.setTabID(0);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // go to previous screen when app icon in action bar is clicked
	            Intent intent = new Intent(this, LoginActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
}
