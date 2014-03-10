package com.pioneer.portaldirect;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.DropdownData;
import com.pioneer.sqlite.model.QuoteVehicle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class QuoteVehicleActivity extends Activity implements OnClickListener, OnFocusChangeListener{
	private EditText _vehicleType;
	private EditText _txtYear;
	private EditText _antiLockBrakes;
	private EditText _passiveRestraints;
	private EditText _antiTheftDevice;
	private EditText _vehicleUsage;
	private EditText _workWeek;
	private EditText _make;
	private EditText _vin;
	private EditText _model;
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_quote_vehicle); 
		 
		 DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
		 GlobalVariables gv = GlobalVariables.getInstance();
		 
		//Redirect to Applicant tab if quote does not exist
		 if(gv.getShowQuoteDoesNotExist()){
			 gv.setQuoteDoesNotExist(false);
			 runOnUiThread(new Runnable() {
				 public void run()
				 {
					 //Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
					 AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
					 builder1.setMessage(R.string.quoteApplicantRedirectMessage);
					 builder1.setCancelable(true);
					 builder1.setPositiveButton("Ok",
							 new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int id) {
							 dialog.cancel();
							 GlobalVariables gv = GlobalVariables.getInstance();
							 gv.setTabID(0);
							 Intent intent = new Intent(QuoteVehicleActivity.this, QuoteTabActivity.class); startActivity(intent);
						 }
					 });

					 AlertDialog alert11 = builder1.create();
					 alert11.show();
				 }
			 });        	
		 }

		 Button buttonSave = (Button) findViewById(R.id.btnSaveNext);
		 buttonSave.setOnClickListener(this);
		 
		 Button buttonCheckVIN = (Button) findViewById(R.id.btnCheckVIN);
		 buttonCheckVIN.setOnClickListener(this);
		 
		 Button buttonCancel = (Button) findViewById(R.id.btnCancel);
		 buttonCancel.setOnClickListener(this);
		 
		 EditText vehicleType = (EditText) findViewById(R.id.txtVehicleType);
		 vehicleType.setOnFocusChangeListener(this);
		 _vehicleType = vehicleType;
		 
		 EditText make = (EditText) findViewById(R.id.txtMake);
		 make.setOnFocusChangeListener(this);
		 _make = make;
		 
		 EditText model = (EditText) findViewById(R.id.txtModel);
		 model.setOnFocusChangeListener(this);
		 _model = model;
		 
		 EditText vin = (EditText) findViewById(R.id.txtVIN);
		 vin.setOnFocusChangeListener(this);
		 _vin = vin;

		 EditText txtYear = (EditText) findViewById(R.id.txtYear);
		 txtYear.setOnFocusChangeListener(this);
		 _txtYear = txtYear;
		 
		 EditText antiLockBrakes = (EditText) findViewById(R.id.txtAntiLockBrakes);
		 antiLockBrakes.setOnFocusChangeListener(this);
		 _antiLockBrakes = antiLockBrakes;
		 
		 EditText passiveRestraints = (EditText) findViewById(R.id.txtPassiveRestraint);
		 passiveRestraints.setOnFocusChangeListener(this);
		 _passiveRestraints = passiveRestraints;
		 
		 EditText antiTheftDevice = (EditText) findViewById(R.id.txtAntiTheft);
		 antiTheftDevice.setOnFocusChangeListener(this);
		 _antiTheftDevice = antiTheftDevice;
		 
		 EditText vehicleUsage = (EditText) findViewById(R.id.txtVehicleUsage);
		 vehicleUsage.setOnFocusChangeListener(this);
		 _vehicleUsage = vehicleUsage;
		 
		 EditText workWeek = (EditText) findViewById(R.id.txtWorkWeek);
		 workWeek.setOnFocusChangeListener(this);
		 _workWeek = workWeek;

		 vehicleType.setOnFocusChangeListener(new OnFocusChangeListener() {
			 public void onFocusChange(View arg0, boolean arg1) {
				 if(arg1){
					 SpinnerData sd = new SpinnerData(getApplicationContext());
					 List<DropdownData> resList = sd.GetVehicleTypeList();
					 final CharSequence[] descList = new CharSequence[resList.size()];
					 for(int i = 0; i < resList.size(); i++){
						 descList[i] = resList.get(i).getDescription();
					 }

					 AlertDialog.Builder builder = new AlertDialog.Builder(QuoteVehicleActivity.this);
					 builder.setTitle("Vehicle Type");
					 builder.setItems(descList, new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int which) {
							 _vehicleType.setText(descList[which]);
							 _vin.requestFocus();
						 }
					 });
					 builder.show();
				 }
			 }} );

		 txtYear.setOnFocusChangeListener(new OnFocusChangeListener() {
			 public void onFocusChange(View arg0, boolean arg1) {
				 if(arg1){
					 SpinnerData sd = new SpinnerData(getApplicationContext());
					 List<DropdownData> resList = sd.GetYearList();
					 final CharSequence[] descList = new CharSequence[resList.size()];
					 for(int i = 0; i < resList.size(); i++){
						 descList[i] = resList.get(i).getDescription();
					 }
					 
					 AlertDialog.Builder builder = new AlertDialog.Builder(QuoteVehicleActivity.this);
					 builder.setTitle("Year");
					 builder.setItems(descList, new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int which) {
							 _txtYear.setText(descList[which]);
							 _vin.requestFocus();
						 }
					 });
					 builder.show();
				 }
			 }} );        
		 
		 antiLockBrakes.setOnFocusChangeListener(new OnFocusChangeListener() {
			 public void onFocusChange(View arg0, boolean arg1) {
				 if(arg1){
					 SpinnerData sd = new SpinnerData(getApplicationContext());
					 List<DropdownData> resList = sd.GetAntiLockBrakeList();
					 final CharSequence[] descList = new CharSequence[resList.size()];
					 for(int i = 0; i < resList.size(); i++){
						 descList[i] = resList.get(i).getDescription();
					 }

					 AlertDialog.Builder builder = new AlertDialog.Builder(QuoteVehicleActivity.this);
					 builder.setTitle("Anti-Lock Brake");
					 builder.setItems(descList, new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int which) {
							 _antiLockBrakes.setText(descList[which]);
							 _vin.requestFocus();
						 }
					 });
					 builder.show();
				 }
			 }} );
		 
		 passiveRestraints.setOnFocusChangeListener(new OnFocusChangeListener() {
			 public void onFocusChange(View arg0, boolean arg1) {
				 if(arg1){
					 SpinnerData sd = new SpinnerData(getApplicationContext());
					 List<DropdownData> resList = sd.GetPassiveRestraintList();
					 final CharSequence[] descList = new CharSequence[resList.size()];
					 for(int i = 0; i < resList.size(); i++){
						 descList[i] = resList.get(i).getDescription();
					 }

					 AlertDialog.Builder builder = new AlertDialog.Builder(QuoteVehicleActivity.this);
					 builder.setTitle("Passive Restraints");
					 builder.setItems(descList, new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int which) {
							 _passiveRestraints.setText(descList[which]);
							 _vin.requestFocus();
						 }
					 });
					 builder.show();
				 }
			 }} );
		 
		 antiTheftDevice.setOnFocusChangeListener(new OnFocusChangeListener() {
			 public void onFocusChange(View arg0, boolean arg1) {
				 if(arg1){
					 SpinnerData sd = new SpinnerData(getApplicationContext());
					 List<DropdownData> resList = sd.GetAntiTheftDeviceList();
					 final CharSequence[] descList = new CharSequence[resList.size()];
					 for(int i = 0; i < resList.size(); i++){
						 descList[i] = resList.get(i).getDescription();
					 }

					 AlertDialog.Builder builder = new AlertDialog.Builder(QuoteVehicleActivity.this);
					 builder.setTitle("Anti-Theft Device");
					 builder.setItems(descList, new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int which) {
							 _antiTheftDevice.setText(descList[which]);
							 _vin.requestFocus();
						 }
					 });
					 builder.show();
				 }
			 }} );
		 
		 vehicleUsage.setOnFocusChangeListener(new OnFocusChangeListener() {
			 public void onFocusChange(View arg0, boolean arg1) {
				 if(arg1){
					 SpinnerData sd = new SpinnerData(getApplicationContext());
					 List<DropdownData> resList = sd.GetVehicleUsageList();
					 final CharSequence[] descList = new CharSequence[resList.size()];
					 for(int i = 0; i < resList.size(); i++){
						 descList[i] = resList.get(i).getDescription();
					 }

					 AlertDialog.Builder builder = new AlertDialog.Builder(QuoteVehicleActivity.this);
					 builder.setTitle("Vehicle Usage");
					 builder.setItems(descList, new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int which) {
							 _vehicleUsage.setText(descList[which]);
							 _vin.requestFocus();
						 }
					 });
					 builder.show();
				 }
			 }} );
		 
		 workWeek.setOnFocusChangeListener(new OnFocusChangeListener() {
			 public void onFocusChange(View arg0, boolean arg1) {
				 if(arg1){
					 SpinnerData sd = new SpinnerData(getApplicationContext());
					 List<DropdownData> resList = sd.GetWorkWeekList();
					 final CharSequence[] descList = new CharSequence[resList.size()];
					 for(int i = 0; i < resList.size(); i++){
						 descList[i] = resList.get(i).getDescription();
					 }

					 AlertDialog.Builder builder = new AlertDialog.Builder(QuoteVehicleActivity.this);
					 builder.setTitle("Work Week");
					 builder.setItems(descList, new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface dialog, int which) {
							 _workWeek.setText(descList[which]);
							 _vin.requestFocus();
						 }
					 });
					 builder.show();
				 }
			 }} );

		 //Load QuoteApplicant data
		 int id = gv.getVehicleID();
		 if(id != -1){
			 QuoteVehicle qa = db.getQuoteVehicleByID(gv.getVehicleID());
			 buttonSave.setText(getString(R.string.button_quoteSaveVehicleChanges));
			 
			 if(qa.carpool.equals("No")){
	        		((RadioButton)findViewById(R.id.rdNo)).setChecked(true);}
	        	if(qa.carpool.equals("Yes")){
	        		((RadioButton)findViewById(R.id.rdYes)).setChecked(true);
	        	}
	        	
			 ((EditText) findViewById(R.id.txtVIN)).setText(qa.VIN);
			 ((EditText) findViewById(R.id.txtVehicleType)).setText(qa.vehicleType);
			 ((EditText) findViewById(R.id.txtYear)).setText(qa.year);
			 ((EditText) findViewById(R.id.txtMake)).setText(qa.make);
			 ((EditText) findViewById(R.id.txtModel)).setText(qa.model);
			 ((EditText) findViewById(R.id.txtAntiLockBrakes)).setText(qa.antiLockBrakes);
			 ((EditText) findViewById(R.id.txtPassiveRestraint)).setText(qa.passiveRestraints);
			 ((EditText) findViewById(R.id.txtAntiTheft)).setText(qa.antiTheftDevice);
			 ((EditText) findViewById(R.id.txtVehicleUsage)).setText(qa.vehicleUsage);
			 ((EditText) findViewById(R.id.txtAnnualMileage)).setText(qa.annualMileage);
			 ((EditText) findViewById(R.id.txtMilesWork)).setText(qa.milesToWork);
			 ((EditText) findViewById(R.id.txtWorkWeek)).setText(qa.workWeek);
			 ((EditText) findViewById(R.id.txtGaragingZipCode)).setText(qa.garagingZipCode);
			 ((EditText) findViewById(R.id.txtSplitCity)).setText(qa.splitCity);
		 }
		 else{
			 buttonSave.setText(getString(R.string.button_quoteSaveNewVehicle));
		 }
		 
	 }
				
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnSaveNext:
				if(_txtYear.getText().toString().equals("") || _make.getText().toString().equals("") || _model.getText().toString().equals(""))
				{
					_txtYear.setError("Required");
					_make.setError("Required");
					_model.setError("Required");
					showMessage("Please fill in required fields");
					break;
				}
				try{
				DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
				GlobalVariables gv = GlobalVariables.getInstance();
				if(gv.getVehicleID() == -1){
					QuoteVehicle qa = new QuoteVehicle();
					qa.quoteID = gv.getQuoteID();
					qa.annualMileage = ((EditText)findViewById(R.id.txtAnnualMileage)).getText().toString();
					qa.antiLockBrakes = ((EditText)findViewById(R.id.txtAntiLockBrakes)).getText().toString();
					qa.antiTheftDevice = ((EditText)findViewById(R.id.txtAntiTheft)).getText().toString();
					
					RadioGroup rg = (RadioGroup) findViewById(R.id.Carpool);
			        String selectedDependentValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
			    	qa.carpool = selectedDependentValue;
					
			    	qa.garagingZipCode = ((EditText)findViewById(R.id.txtGaragingZipCode)).getText().toString();
					qa.make = ((EditText)findViewById(R.id.txtMake)).getText().toString();
					qa.milesToWork = ((EditText)findViewById(R.id.txtMilesWork)).getText().toString();
					qa.model = ((EditText)findViewById(R.id.txtModel)).getText().toString();
					qa.passiveRestraints = ((EditText)findViewById(R.id.txtPassiveRestraint)).getText().toString();
					qa.splitCity = ((EditText)findViewById(R.id.txtSplitCity)).getText().toString();			
					qa.vehicleType = ((EditText)findViewById(R.id.txtVehicleType)).getText().toString();	
					qa.vehicleUsage = ((EditText)findViewById(R.id.txtVehicleUsage)).getText().toString();	
					qa.VIN = ((EditText)findViewById(R.id.txtVIN)).getText().toString();	
					qa.workWeek = ((EditText)findViewById(R.id.txtWorkWeek)).getText().toString();	
					qa.year = ((EditText)findViewById(R.id.txtYear)).getText().toString();	
					db.createQuoteVehicle(qa);
				}
				else{
					QuoteVehicle qa = new QuoteVehicle();
					qa.id = gv.getVehicleID();
					qa.quoteID = gv.getQuoteID();
					qa.annualMileage = ((EditText)findViewById(R.id.txtAnnualMileage)).getText().toString();
					qa.antiLockBrakes = ((EditText)findViewById(R.id.txtAntiLockBrakes)).getText().toString();
					qa.antiTheftDevice = ((EditText)findViewById(R.id.txtAntiTheft)).getText().toString();
					
					RadioGroup rg = (RadioGroup) findViewById(R.id.Carpool);
			        String selectedDependentValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
			    	qa.carpool = selectedDependentValue;
					
					qa.garagingZipCode = ((EditText)findViewById(R.id.txtGaragingZipCode)).getText().toString();
					qa.make = ((EditText)findViewById(R.id.txtMake)).getText().toString();
					qa.milesToWork = ((EditText)findViewById(R.id.txtMilesWork)).getText().toString();
					qa.model = ((EditText)findViewById(R.id.txtModel)).getText().toString();
					qa.passiveRestraints = ((EditText)findViewById(R.id.txtPassiveRestraint)).getText().toString();
					qa.splitCity = ((EditText)findViewById(R.id.txtSplitCity)).getText().toString();			
					qa.vehicleType = ((EditText)findViewById(R.id.txtVehicleType)).getText().toString();	
					qa.vehicleUsage = ((EditText)findViewById(R.id.txtVehicleUsage)).getText().toString();	
					qa.VIN = ((EditText)findViewById(R.id.txtVIN)).getText().toString();	
					qa.workWeek = ((EditText)findViewById(R.id.txtWorkWeek)).getText().toString();	
					qa.year = ((EditText)findViewById(R.id.txtYear)).getText().toString();		
					
					db.updateQuoteVehicle(qa);											
				}
	            gv.setTabID(3);
	            Intent intent = new Intent(QuoteVehicleActivity.this, QuoteTabActivity.class); startActivity(intent);
				}
				catch(Exception e){
					showMessage("Error saving vehicle. Please try again later.");
				}
	            break;
	        case R.id.btnCheckVIN:
	        	CheckVIN task = new CheckVIN();
	        	EditText VIN = (EditText) findViewById(R.id.txtVIN);
	        	task.execute(VIN.getText().toString());
	            break;
	        case R.id.btnCancel:
	        	AlertDialog.Builder builder1 = new AlertDialog.Builder(QuoteVehicleActivity.this);
	            builder1.setMessage("Are you sure you want to cancel?");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Yes",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	GlobalVariables gv = GlobalVariables.getInstance();
	    	        	gv.setTabID(3);
	    	            Intent intent = new Intent(QuoteVehicleActivity.this, QuoteTabActivity.class); startActivity(intent);
	                }
	            });
	            builder1.setNegativeButton("No",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            
	            AlertDialog alert11 = builder1.create();
	            alert11.show();        
	        	break;
	        case R.id.txtState:
//	        	final CharSequence[] states = GlobalVariables.getInstance().GetStateList();
	//
//	        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//	        	builder.setTitle("Pick a State");
//	        	builder.setItems(GlobalVariables.getInstance().GetStateList(), new DialogInterface.OnClickListener() {
//		               public void onClick(DialogInterface dialog, int which) {
//		               _state.setText(states[which]);
//		           }
//	        	});
//	        	builder.show();
	        	break;
	        case R.id.txtResidency:
//	        	SpinnerData sd = new SpinnerData(getActivity());
//	        	List<DropdownData> resList = sd.GetResidencyList();
//	        	final CharSequence[] descList = new CharSequence[resList.size()];
//	        	for(int i = 0; i < resList.size(); i++){
//	        		descList[i] = resList.get(i).getDescription();
//	        	}
//	        	
//	        	AlertDialog.Builder builderResidency = new AlertDialog.Builder(getActivity());
//	        	builderResidency.setTitle("Residency");
//	        	builderResidency.setItems(descList, new DialogInterface.OnClickListener() {
//		               public void onClick(DialogInterface dialog, int which) {
//		               _residency.setText(descList[which]);
//		               _firstName.requestFocus();
//		           }
//	        	});
//	        	builderResidency.show();
	        	break;
	        }
		}
		
		public class CheckVIN extends AsyncTask<String, Void, String>{
	    	protected String doInBackground(String... params) {
	    		HttpParams httpParameters = new BasicHttpParams();
	    		DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);
	    		
	    		int timeout = GlobalVariables.getInstance().getConnectionTimeout();
	    		    		    		
	    		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), timeout);
	    		
	    		String postURL = GlobalVariables.getInstance().getServerName() + "/users.svc/checkvin/";
	    		postURL = postURL.concat(params[0]);
	    		HttpGet httpGet = new HttpGet(postURL);
	    		// Depends on your web service
	    		httpGet.setHeader("Content-type", "application/json");

	    		InputStream inputStream = null;
	    		String result = null;
	    		try {
	    		    HttpResponse response = httpclient.execute(httpGet);           
	    		    HttpEntity entity = response.getEntity();

	    		    inputStream = entity.getContent();
	    		    // json is UTF-8 by default
	    		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
	    		    StringBuilder sb = new StringBuilder();

	    		    String line = null;
	    		    while ((line = reader.readLine()) != null)
	    		    {
	    		        sb.append(line + "\n");
	    		    }
	    		    result = sb.toString();
	    		} 
	    		catch (ConnectTimeoutException e){
	    			showMessage("Cannot connect to server. Please try again later.");
	    		}
	    		catch (Exception e) { 
	    			showMessage("Cannot connect to server. Please try again later.");
	    		}
	    		finally {
	    		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
	    		}
	            return result;
	        }

	        protected void onProgressUpdate(Integer... progress) {
	            //setProgressPercent(progress[0]);
	        }

	        protected void onPostExecute(String result) {
	        	//TextView t = (TextView) findViewById(R.id.txtUsername);
			    //t.setText(result);
	        	if(result == null){
			    	return;
			    }
			    
			    JSONObject jObject = null;
				try {
					jObject = new JSONObject(result);
					//String responseString = jObject.getString("GetAnnualIncomeListResult");
					//String r = responseString + "f";
					JSONArray jArray = jObject.getJSONArray("CheckVINResult");
					for (int i=0; i < jArray.length(); i++)
					{
					    try {
					        JSONObject oneObject = jArray.getJSONObject(i);
					        // Pulling items from the array
					        String make = oneObject.getString("Make");
					        String model = oneObject.getString("Model");
					        String year = oneObject.getString("Year");
					        String returnCode = oneObject.getString("returnCode");
					        
					        if(returnCode.equals("0")){
					        	_make.setText(make);
					        	_model.setText(model);
					        	_txtYear.setText(year);
					        }
					        else{
					        	showMessage("Invalid VIN");
					        }
					        
					    } catch (JSONException e) {
					        // Oops
					    }
					}
				} catch (JSONException e) {
					
				}
				finally{
					//db.closeDB();
					//db.close();
				}

			
	    }
						
	}
		
		@Override
		public void onFocusChange(View arg0, boolean arg1) {
			int controlID = arg0.getId();
			switch (controlID) {
			case R.id.txtDateBirth:
				
			break;
			}
		}
		
		public void showMessage(final String message)
	    {
	        runOnUiThread(new Runnable() {
	            public void run()
	            {
	            	//Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
	            	AlertDialog.Builder builder1 = new AlertDialog.Builder(QuoteVehicleActivity.this);
		            builder1.setMessage(message);
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Ok",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            });
		            
		            AlertDialog alert11 = builder1.create();
		            alert11.show();
	            }
	        });
	    }

}
