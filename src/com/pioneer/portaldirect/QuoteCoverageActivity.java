package com.pioneer.portaldirect;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.DropdownData;
import com.pioneer.sqlite.model.QuoteApplicant;
import com.pioneer.sqlite.model.QuoteCoverages;
import com.pioneer.sqlite.model.Quotes;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class QuoteCoverageActivity extends Fragment implements OnClickListener, OnFocusChangeListener{

	private View _rootView;
	private EditText _propertyDamage;
	private EditText _propertyProtection;
	private EditText _uninsuredLimit;
	private EditText _underinsuredLimit;
	private EditText _miniTort;
	private EditText _bodilyInjury;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quote_coverage, container, false);
        _rootView = rootView;
        
        Calendar cal = Calendar.getInstance();
        String timezoneID = TimeZone.getDefault().getID();
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        final int year=cal.get(Calendar.YEAR);
        
        DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
        GlobalVariables gv = GlobalVariables.getInstance();
        
        Button b = (Button) rootView.findViewById(R.id.btnSaveNext);
        b.setOnClickListener(this);
        
        EditText bodilyInjury = (EditText) rootView.findViewById(R.id.txtBodilyInjury);
        _bodilyInjury = bodilyInjury;
                                
        EditText propertyDamage = (EditText) rootView.findViewById(R.id.txtPropertyDamage);
        propertyDamage.setOnFocusChangeListener(this);
        _propertyDamage = propertyDamage;
        
        EditText propertyProtection = (EditText) rootView.findViewById(R.id.txtPropertyProtection);
        propertyProtection.setOnFocusChangeListener(this);
        _propertyProtection = propertyProtection;
        
        EditText uninsuredLimit = (EditText) rootView.findViewById(R.id.txtUninsuredMotoristLimit);
        uninsuredLimit.setOnFocusChangeListener(this);
        _uninsuredLimit = uninsuredLimit;
        
        EditText underinsuredLimit = (EditText) rootView.findViewById(R.id.txtUnderinsuredLimit);
        underinsuredLimit.setOnFocusChangeListener(this);
        _underinsuredLimit = underinsuredLimit;
        
        EditText miniTort = (EditText) rootView.findViewById(R.id.txtMiniTort);
        miniTort.setOnFocusChangeListener(this);
        _miniTort = miniTort;
        
        propertyDamage.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetPropertyDamageList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderOccupation = new AlertDialog.Builder(getActivity());
                	builderOccupation.setTitle("Property Damage");
                	builderOccupation.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _propertyDamage.setText(descList[which]);
        	               _bodilyInjury.requestFocus();
        	           }
                	});
                	builderOccupation.show();
            	}
            }} );      
        
        propertyProtection.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetPropertyProtectionList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderOccupation = new AlertDialog.Builder(getActivity());
                	builderOccupation.setTitle("Property Protection");
                	builderOccupation.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _propertyProtection.setText(descList[which]);
        	               _bodilyInjury.requestFocus();
        	           }
                	});
                	builderOccupation.show();
            	}
            }} );   
        
        uninsuredLimit.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetUninsuredLimitList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderOccupation = new AlertDialog.Builder(getActivity());
                	builderOccupation.setTitle("Uninsured Motorist Limit");
                	builderOccupation.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _uninsuredLimit.setText(descList[which]);
        	               _bodilyInjury.requestFocus();
        	           }
                	});
                	builderOccupation.show();
            	}
            }} );  
        
        underinsuredLimit.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetUnderinsuredLimitList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderOccupation = new AlertDialog.Builder(getActivity());
                	builderOccupation.setTitle("Underinsured Motorist Limit");
                	builderOccupation.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _underinsuredLimit.setText(descList[which]);
        	               _bodilyInjury.requestFocus();
        	           }
                	});
                	builderOccupation.show();
            	}
            }} );  
        
        miniTort.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetMiniTortList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderOccupation = new AlertDialog.Builder(getActivity());
                	builderOccupation.setTitle("Mini Tort");
                	builderOccupation.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _miniTort.setText(descList[which]);
        	               _bodilyInjury.requestFocus();
        	           }
                	});
                	builderOccupation.show();
            	}
            }} );  
                
        
        //Load QuoteCoverage data
        String id = gv.getQuoteID();
        if(!id.equals("")){
        	QuoteCoverages qa = db.getQuoteCoverages(id);
        	((EditText) rootView.findViewById(R.id.txtBodilyInjury)).setText(qa.bodilyInjury);
        	((EditText) rootView.findViewById(R.id.txtMedicalProvider)).setText(qa.medicalProvider);
        	((EditText) rootView.findViewById(R.id.txtPersonalInjuryProtection)).setText(qa.personaInjuryProtection);
        	((EditText) rootView.findViewById(R.id.txtPropertyProtection)).setText(qa.propertyProtection);
        	((EditText) rootView.findViewById(R.id.txtPropertyDamage)).setText(qa.propertyDamage);
        	((EditText) rootView.findViewById(R.id.txtUninsuredMotoristLimit)).setText(qa.uninsuredMotorist);
        	((EditText) rootView.findViewById(R.id.txtUnderinsuredLimit)).setText(qa.underinsuredMotorist);
        	((EditText) rootView.findViewById(R.id.txtMiniTort)).setText(qa.miniTort);
        }
        
        return rootView;
    }
	
	
	@Override
	public void onClick(View v) {
		Calendar cal = Calendar.getInstance();
        String timezoneID = TimeZone.getDefault().getID();
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        int year=cal.get(Calendar.YEAR);
        
		switch (v.getId()) {
		case R.id.btnSaveNext:
			if(_bodilyInjury.getText().toString().equals("") || _propertyDamage.getText().toString().equals(""))
			{
				_bodilyInjury.setError("Required");
				_propertyDamage.setError("Required");
				showMessage("Please fill in required fields");
				break;
			}
			Date todayDate = new Date();
			DatabaseHelper db = DatabaseHelper.getInstance(this.getView().getContext());
			GlobalVariables gv = GlobalVariables.getInstance();			
						
			QuoteCoverages qa = new QuoteCoverages();
			qa.quoteID = gv.getQuoteID();
			qa.bodilyInjury = ((EditText)_rootView.findViewById(R.id.txtBodilyInjury)).getText().toString();
			qa.medicalProvider = ((EditText)_rootView.findViewById(R.id.txtMedicalProvider)).getText().toString();
			qa.miniTort = ((EditText)_rootView.findViewById(R.id.txtMiniTort)).getText().toString();
			qa.personaInjuryProtection = ((EditText)_rootView.findViewById(R.id.txtPersonalInjuryProtection)).getText().toString();
			qa.propertyDamage = ((EditText)_rootView.findViewById(R.id.txtPropertyDamage)).getText().toString();
			qa.propertyProtection = ((EditText)_rootView.findViewById(R.id.txtPropertyProtection)).getText().toString();
			qa.uninsuredMotorist = ((EditText)_rootView.findViewById(R.id.txtUninsuredMotoristLimit)).getText().toString();
			qa.underinsuredMotorist = ((EditText)_rootView.findViewById(R.id.txtUnderinsuredLimit)).getText().toString();
			
			if(db.getQuoteCoveragesCount(gv.getQuoteID().toString()) == 0){
				db.createQuoteCoverages(qa);
			}
			else{
				db.updateQuoteCoverages(qa);
			}						
			
            gv.setTabID(5);
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
