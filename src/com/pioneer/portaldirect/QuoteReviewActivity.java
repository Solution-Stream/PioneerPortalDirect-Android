package com.pioneer.portaldirect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.QuoteVehicle;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class QuoteReviewActivity extends Fragment implements OnClickListener{

	private ArrayList<Integer> vehicleIDList;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 
		View rootView = inflater.inflate(R.layout.activity_quote_review, container, false);
	     		
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ListView list;
		final ListView listview = (ListView) rootView.findViewById(R.id.listViewQuoteVehicles);
		final ArrayList<String> quoteVehicleIDList = new ArrayList<String>();
		
		
		DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
		final GlobalVariables gv = GlobalVariables.getInstance();
		List<QuoteVehicle> listQuoteVehicles = db.getAllQuoteVehicles(gv.getQuoteID());
		
		int i = 0;
		final String[] vehicleList = new String[listQuoteVehicles.size()];
		final String[] statusList = new String[listQuoteVehicles.size()];
		final String[] selectedDriverList = new String[listQuoteVehicles.size()];
		final Integer[] imageid = new Integer[listQuoteVehicles.size()];
		vehicleIDList = new ArrayList<Integer>();
		for(QuoteVehicle q : listQuoteVehicles){
			vehicleList[i] = q.year + " " + q.make + " " + q.model;			
			vehicleIDList.add(q.id);
			selectedDriverList[i] = q.assignedDriverName;
			imageid[i] = R.drawable.cabrioletred;
			quoteVehicleIDList.add(String.valueOf(q.quoteID));
			i++;
		}
		
			
		ListRowAdapter adapter = new ListRowAdapter(this.getActivity(), vehicleList, imageid, selectedDriverList);
		list = (ListView) rootView.findViewById(R.id.listViewQuoteVehicles);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(getActivity().getApplicationContext(), "You Clicked at " + vehicleList[+ position], Toast.LENGTH_SHORT).show();
				
			}
		});


		
		return rootView;
		}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // go to previous screen when app icon in action bar is clicked
	            Intent intent = new Intent(getActivity(), LoginActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getActivity().getMenuInflater().inflate(R.menu.quote_list, menu);
		return true;
	}
	
	private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }
	}
			
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSubmitQuote:
				GlobalVariables gv = GlobalVariables.getInstance();
		    	gv.setVehicleID(-1);
		    	Intent i = new Intent(getActivity(), QuoteVehicleActivity.class); startActivity(i);
			break;
		}
	}

}