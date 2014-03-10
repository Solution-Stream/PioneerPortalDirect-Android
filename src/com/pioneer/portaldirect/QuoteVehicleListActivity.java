package com.pioneer.portaldirect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pioneer.portaldirect.LoginActivity.GetJSONData;
import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.QuoteVehicle;
import com.pioneer.sqlite.model.Quotes;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuoteVehicleListActivity extends Fragment implements OnClickListener{

	private ArrayList<Integer> vehicleIDList;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 
		View rootView = inflater.inflate(R.layout.activity_quotevehicle_list, container, false);
	     		
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Button buttonAddVehicle = (Button) rootView.findViewById(R.id.btnAddVehicle);
		buttonAddVehicle.setOnClickListener(this);
		
		final ListView listview = (ListView) rootView.findViewById(R.id.listViewQuoteVehicles);
		final ArrayList<String> list = new ArrayList<String>();
		final ArrayList<String> quoteVehicleIDList = new ArrayList<String>();
		
		DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
		final GlobalVariables gv = GlobalVariables.getInstance();
		List<QuoteVehicle> listQuoteVehicles = db.getAllQuoteVehicles(gv.getQuoteID());
		
		vehicleIDList = new ArrayList<Integer>();
		for(QuoteVehicle q : listQuoteVehicles){
			list.add(q.year + " " + q.make + " " + q.model);
			vehicleIDList.add(q.id);
			quoteVehicleIDList.add(String.valueOf(q.quoteID));
		}
		
		final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Toast.makeText(getApplicationContext(),((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            	int vehID = vehicleIDList.get(position);
            	gv.setVehicleID(vehID);
            	Intent i = new Intent(getActivity(), QuoteVehicleActivity.class); startActivity(i);
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
			case R.id.btnAddVehicle:
				GlobalVariables gv = GlobalVariables.getInstance();
		    	gv.setVehicleID(-1);
		    	Intent i = new Intent(getActivity(), QuoteVehicleActivity.class); startActivity(i);
			break;
		}
	}

}