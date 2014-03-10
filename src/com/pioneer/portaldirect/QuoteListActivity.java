package com.pioneer.portaldirect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pioneer.portaldirect.LoginActivity.GetJSONData;
import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.Quotes;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuoteListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quote_list);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		final ListView listview = (ListView) findViewById(R.id.listViewQuotes);
		final ArrayList<String> list = new ArrayList<String>();
		final ArrayList<String> quoteIDList = new ArrayList<String>();
		
		DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
		final GlobalVariables gv = GlobalVariables.getInstance();
		List<Quotes> listQuotes = db.getAllQuotes();
		
		for(Quotes q : listQuotes){
			list.add(q.quoteName);
			quoteIDList.add(String.valueOf(q.quoteID));
		}
		
		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Toast.makeText(getApplicationContext(),((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            	String quoteID = quoteIDList.get(position);
            	gv.setQuoteID(quoteID);
            	Intent i = new Intent(QuoteListActivity.this, QuoteTabActivity.class); startActivity(i);
            }
        });
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quote_list, menu);
		return true;
	}
	
	public void AddNewQuote_Click(View view){   
		GlobalVariables gv = GlobalVariables.getInstance();
		gv.setQuoteID("");
		Intent i = new Intent(QuoteListActivity.this, QuoteTabActivity.class); startActivity(i);
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

}