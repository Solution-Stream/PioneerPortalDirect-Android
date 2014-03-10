package com.pioneer.portaldirect;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.DropdownData;
import com.pioneer.sqlite.model.QuoteApplicant;
import com.pioneer.sqlite.model.Quotes;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


public class QuoteApplicantActivity extends Fragment implements OnClickListener, OnFocusChangeListener{

	private EditText _dateBirth;
	private EditText _state;
	private EditText _residency;
	private EditText _SSN;
	private EditText _firstName;
	private EditText _lastName;
	private View _rootView;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		//(ActionBarActivity)activity).getSupportActionBar().setSubtitle(R.string.subtitle);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quote_applicant, container, false);
        _rootView = rootView;
        
        Calendar cal = Calendar.getInstance();
        String timezoneID = TimeZone.getDefault().getID();
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        final int year=cal.get(Calendar.YEAR);
        
        DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
        GlobalVariables gv = GlobalVariables.getInstance();
        
        Button b = (Button) rootView.findViewById(R.id.btnSaveNext);
        b.setOnClickListener(this);
                                
        EditText dateBirth = (EditText) rootView.findViewById(R.id.txtDateBirth);
        dateBirth.setOnFocusChangeListener(this);
        _dateBirth = dateBirth;
        
        EditText state = (EditText) rootView.findViewById(R.id.txtState);
        state.setOnFocusChangeListener(this);
        _state = state;
        
        dateBirth.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            	DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener, year - 20, 6, 15);
                dialog.show();
            	}
            }} );
        
        state.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		final CharSequence[] states = GlobalVariables.getInstance().GetStateList();            		
            		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            		builder.setTitle("Pick a State");
            		builder.setItems(GlobalVariables.getInstance().GetStateList(), new DialogInterface.OnClickListener() {
            			public void onClick(DialogInterface dialog, int which) {
            				_state.setText(states[which]);
            			}
            		});
            		builder.show();
            	}
            }} );        
          
        EditText SSN = (EditText) rootView.findViewById(R.id.txtSSN);
        SSN.setOnClickListener(this);
        _SSN = SSN;
        
        EditText firstName = (EditText) rootView.findViewById(R.id.txtFirstName);
        firstName.setOnClickListener(this);
        _firstName = firstName;
        
        EditText lastName = (EditText) rootView.findViewById(R.id.txtLastName);
        _lastName = lastName;
        
        EditText residency = (EditText) rootView.findViewById(R.id.txtResidency);
        residency.setOnFocusChangeListener(this);
        _residency = residency;
        
        residency.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetResidencyList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderResidency = new AlertDialog.Builder(getActivity());
                	builderResidency.setTitle("Residency");
                	builderResidency.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _residency.setText(descList[which]);
        	               _firstName.requestFocus();
        	           }
                	});
                	builderResidency.show();
            	}
            }} );
        
        //Load QuoteApplicant data
        String id = gv.getQuoteID();
        if(!id.equals("")){
        	QuoteApplicant qa = db.getQuoteApplicantData(id);
        	((EditText) rootView.findViewById(R.id.txtAddress)).setText(qa.address);
        	((EditText) rootView.findViewById(R.id.txtCity)).setText(qa.city);
        	((EditText) rootView.findViewById(R.id.txtDateBirth)).setText(qa.dateBirth);
        	((EditText) rootView.findViewById(R.id.txtEmail)).setText(qa.email);
        	((EditText) rootView.findViewById(R.id.txtFirstName)).setText(qa.firstName);
        	((EditText) rootView.findViewById(R.id.txtLastName)).setText(qa.lastName);
        	((EditText) rootView.findViewById(R.id.txtMiddleInitial)).setText(qa.middle);
        	((EditText) rootView.findViewById(R.id.txtResidency)).setText(qa.residencyType);
        	((EditText) rootView.findViewById(R.id.txtSSN)).setText(qa.ssn);
        	((EditText) rootView.findViewById(R.id.txtState)).setText(qa.state);
        	((EditText) rootView.findViewById(R.id.txtZip)).setText(qa.zip);
        }
        
        return rootView;
    }
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			//EditText dateBirth = (EditText) view.findViewById(R.id.txtDateBirth);	
			_dateBirth.setText(Integer.toString(selectedMonth) + "/" + Integer.toString(selectedDay) + "/" + Integer.toString(selectedYear));
			_SSN.requestFocus();
		}
	};
	
	@Override
	public void onClick(View v) {
		Calendar cal = Calendar.getInstance();
        String timezoneID = TimeZone.getDefault().getID();
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        int year=cal.get(Calendar.YEAR);
        
		switch (v.getId()) {
		case R.id.btnSaveNext:
			if(_lastName.getText().toString().equals("") || _firstName.getText().toString().equals(""))
			{
				_firstName.setError("Required");
				_lastName.setError("Required");
				showMessage("Please fill in required fields");
				break;
			}
			Date todayDate = new Date();
			DatabaseHelper db = DatabaseHelper.getInstance(this.getView().getContext());
			GlobalVariables gv = GlobalVariables.getInstance();
			if(gv.getQuoteID() == ""){
				Quotes q = new Quotes();
				q.quoteName = ((EditText)_rootView.findViewById(R.id.txtFirstName)).getText().toString() + " " + 
						((EditText)_rootView.findViewById(R.id.txtMiddleInitial)).getText().toString() + " " +
						((EditText)_rootView.findViewById(R.id.txtLastName)).getText().toString();
				q.quoteStatus = "Started";
				q.quoteSubmitted = todayDate.toString();
				long newQuoteID = db.createQuote(q);
				gv.setQuoteID(String.valueOf(newQuoteID));
						
				QuoteApplicant qa = new QuoteApplicant();
				qa.quoteId = gv.getQuoteID();
				qa.firstName = ((EditText)_rootView.findViewById(R.id.txtFirstName)).getText().toString();
				qa.middle = ((EditText)_rootView.findViewById(R.id.txtMiddleInitial)).getText().toString();
				qa.lastName = ((EditText)_rootView.findViewById(R.id.txtLastName)).getText().toString();
				qa.address = ((EditText)_rootView.findViewById(R.id.txtAddress)).getText().toString();
				qa.city = ((EditText)_rootView.findViewById(R.id.txtCity)).getText().toString();
				qa.state = ((EditText)_rootView.findViewById(R.id.txtState)).getText().toString();
				qa.zip = ((EditText)_rootView.findViewById(R.id.txtZip)).getText().toString();
				qa.dateBirth = ((EditText)_rootView.findViewById(R.id.txtDateBirth)).getText().toString();
				qa.ssn = ((EditText)_rootView.findViewById(R.id.txtSSN)).getText().toString();
				qa.email = ((EditText)_rootView.findViewById(R.id.txtEmail)).getText().toString();			
				qa.residencyType = ((EditText)_rootView.findViewById(R.id.txtResidency)).getText().toString();			
				db.createQuoteApplicant(qa);
			}
			else{
				QuoteApplicant qa = new QuoteApplicant();
				qa.quoteId = gv.getQuoteID();
				qa.firstName = ((EditText)_rootView.findViewById(R.id.txtFirstName)).getText().toString();
				qa.middle = ((EditText)_rootView.findViewById(R.id.txtMiddleInitial)).getText().toString();
				qa.lastName = ((EditText)_rootView.findViewById(R.id.txtLastName)).getText().toString();
				qa.address = ((EditText)_rootView.findViewById(R.id.txtAddress)).getText().toString();
				qa.city = ((EditText)_rootView.findViewById(R.id.txtCity)).getText().toString();
				qa.state = ((EditText)_rootView.findViewById(R.id.txtState)).getText().toString();
				qa.zip = ((EditText)_rootView.findViewById(R.id.txtZip)).getText().toString();
				qa.dateBirth = ((EditText)_rootView.findViewById(R.id.txtDateBirth)).getText().toString();
				qa.ssn = ((EditText)_rootView.findViewById(R.id.txtSSN)).getText().toString();
				qa.email = ((EditText)_rootView.findViewById(R.id.txtEmail)).getText().toString();			
				qa.residencyType = ((EditText)_rootView.findViewById(R.id.txtResidency)).getText().toString();	
				
				if(db.getQuoteApplicantCount(gv.getQuoteID().toString()) == 0){
					db.createQuoteApplicant(qa);
				}
				else{
					db.updateQuoteApplicant(qa);
				}						
			}
            gv.setTabID(2);
            Intent intent = new Intent(getActivity(), QuoteTabActivity.class); startActivity(intent);
            break;
        case R.id.txtDateBirth:
//        	DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener, year - 20, 6, 15);
//            dialog.show();
            break;
        case R.id.txtState:
//        	final CharSequence[] states = GlobalVariables.getInstance().GetStateList();
//
//        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        	builder.setTitle("Pick a State");
//        	builder.setItems(GlobalVariables.getInstance().GetStateList(), new DialogInterface.OnClickListener() {
//	               public void onClick(DialogInterface dialog, int which) {
//	               _state.setText(states[which]);
//	           }
//        	});
//        	builder.show();
        	break;
        case R.id.txtResidency:
//        	SpinnerData sd = new SpinnerData(getActivity());
//        	List<DropdownData> resList = sd.GetResidencyList();
//        	final CharSequence[] descList = new CharSequence[resList.size()];
//        	for(int i = 0; i < resList.size(); i++){
//        		descList[i] = resList.get(i).getDescription();
//        	}
//        	
//        	AlertDialog.Builder builderResidency = new AlertDialog.Builder(getActivity());
//        	builderResidency.setTitle("Residency");
//        	builderResidency.setItems(descList, new DialogInterface.OnClickListener() {
//	               public void onClick(DialogInterface dialog, int which) {
//	               _residency.setText(descList[which]);
//	               _firstName.requestFocus();
//	           }
//        	});
//        	builderResidency.show();
        	break;
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
        getActivity().runOnUiThread(new Runnable() {
            public void run()
            {
            	//Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            	AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
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
