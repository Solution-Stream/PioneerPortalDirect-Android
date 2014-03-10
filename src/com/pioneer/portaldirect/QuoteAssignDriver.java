package com.pioneer.portaldirect;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class QuoteAssignDriver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quote_assign_driver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quote_assign_driver, menu);
		return true;
	}

}
