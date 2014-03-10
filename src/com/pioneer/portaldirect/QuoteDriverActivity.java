package com.pioneer.portaldirect;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.DropdownData;
import com.pioneer.sqlite.model.QuoteDriver;
import com.pioneer.sqlite.model.Quotes;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuoteDriverActivity extends Fragment implements OnClickListener, OnFocusChangeListener{

	private EditText _dateBirth;
	private EditText _firstName;
	private EditText _incomeLevel;
	private EditText _lastName;
	private EditText _licenseNum;
	private EditText _licenseState;
	private EditText _middleInitial;
	private EditText _occupation;
	private EditText _relationApplicant;
	private RadioButton _rdMale;
	private View _rootView;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		//(ActionBarActivity)activity).getSupportActionBar().setSubtitle(R.string.subtitle);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quote_driver, container, false);
        _rootView = rootView;
        
        try{
        
        Calendar cal = Calendar.getInstance();
        String timezoneID = TimeZone.getDefault().getID();
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        final int year=cal.get(Calendar.YEAR);
        
        DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
        GlobalVariables gv = GlobalVariables.getInstance();
        
        //Redirect to Applicant tab if quote does not exist
        if(gv.getShowQuoteDoesNotExist()){
        	gv.setQuoteDoesNotExist(false);
        	getActivity().runOnUiThread(new Runnable() {
                public void run()
                {
                	//Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                	AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
    	            builder1.setMessage(R.string.quoteApplicantRedirectMessage);
    	            builder1.setCancelable(true);
    	            builder1.setPositiveButton("Ok",
    	                    new DialogInterface.OnClickListener() {
    	                public void onClick(DialogInterface dialog, int id) {
    	                    dialog.cancel();
    	                    GlobalVariables gv = GlobalVariables.getInstance();
    	                    gv.setTabID(0);
    	                    Intent intent = new Intent(getActivity(), QuoteTabActivity.class); startActivity(intent);
    	                }
    	            });
    	            
    	            AlertDialog alert11 = builder1.create();
    	            alert11.show();
                }
            });        	
        }
        
        Button b = (Button) rootView.findViewById(R.id.btnSaveNext);
        b.setOnClickListener(this);
        
        RadioButton rdMale = (RadioButton) rootView.findViewById(R.id.rdMale);
        _rdMale = rdMale;
        
        EditText firstName = (EditText) rootView.findViewById(R.id.txtFirstName);
        _firstName = firstName;
        
        EditText lastName = (EditText) rootView.findViewById(R.id.txtLastName);
        _lastName = lastName;
                                
        EditText dateBirth = (EditText) rootView.findViewById(R.id.txtDateBirth);
        dateBirth.setOnFocusChangeListener(this);
        _dateBirth = dateBirth;
        
        EditText incomeLevel = (EditText) rootView.findViewById(R.id.txtIncomeLevel);
        incomeLevel.setOnFocusChangeListener(this);
        _incomeLevel = incomeLevel;
        
        EditText licenseState = (EditText) rootView.findViewById(R.id.txtLicenseState);
        licenseState.setOnFocusChangeListener(this);
        _licenseState = licenseState;
        
        EditText licenseNum = (EditText) rootView.findViewById(R.id.txtLicenseNum);
        licenseNum.setOnFocusChangeListener(this);
        _licenseNum = licenseNum;
        
        EditText occupation = (EditText) rootView.findViewById(R.id.txtOccupation);
        occupation.setOnFocusChangeListener(this);
        _occupation = occupation;
        
        EditText relationApplicant = (EditText) rootView.findViewById(R.id.txtRelationApplicant);
        relationApplicant.setOnFocusChangeListener(this);
        _relationApplicant = relationApplicant;
        
        occupation.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetOccupationList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderOccupation = new AlertDialog.Builder(getActivity());
                	builderOccupation.setTitle("Occupation");
                	builderOccupation.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _occupation.setText(descList[which]);
        	               _firstName.requestFocus();
        	           }
                	});
                	builderOccupation.show();
            	}
            }} );
        
        relationApplicant.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetRelationApplicantList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderRelationApplicant = new AlertDialog.Builder(getActivity());
                	builderRelationApplicant.setTitle("Applicant Relation");
                	builderRelationApplicant.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _relationApplicant.setText(descList[which]);
        	               _firstName.requestFocus();
        	           }
                	});
                	builderRelationApplicant.show();
            	}
            }} );
        
        incomeLevel.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		SpinnerData sd = new SpinnerData(getActivity());
                	List<DropdownData> resList = sd.GetIncomeLevelList();
                	final CharSequence[] descList = new CharSequence[resList.size()];
                	for(int i = 0; i < resList.size(); i++){
                		descList[i] = resList.get(i).getDescription();
                	}
                	
                	AlertDialog.Builder builderIncomeLevel = new AlertDialog.Builder(getActivity());
                	builderIncomeLevel.setTitle("Annual Income");
                	builderIncomeLevel.setItems(descList, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _incomeLevel.setText(descList[which]);
        	               _firstName.requestFocus();
        	           }
                	});
                	builderIncomeLevel.show();
            	}
            }} );
        
        dateBirth.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            	DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener, year - 20, 6, 15);
                dialog.show();
            	}
            }} );
        
        licenseState.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
            	if(arg1){
            		final CharSequence[] states = GlobalVariables.getInstance().GetStateList();

                	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                	builder.setTitle("Pick a State");
                	builder.setItems(GlobalVariables.getInstance().GetStateList(), new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               _licenseState.setText(states[which]);
        	               _licenseNum.requestFocus();
        	           }
                	});
                	builder.show();
            	}
            }} );
                
       
              
        //Load QuoteApplicant data
        String id = gv.getQuoteID();
        if(!id.equals("")){
        	QuoteDriver qa = db.getQuoteDriverData(id); 

        	if(qa.dependents.equals("Yes")){
        		((RadioButton)rootView.findViewById(R.id.rdYes)).setChecked(true);}
        	if(qa.dependents.equals("No")){
        		((RadioButton)rootView.findViewById(R.id.rdNo)).setChecked(true);
        	}

        	if(qa.gender.equals("Male")){
        		((RadioButton)rootView.findViewById(R.id.rdMale)).setChecked(true);}
        	if(qa.gender.equals("Female")){
        		((RadioButton)rootView.findViewById(R.id.rdFemale)).setChecked(true);
        	}

        	if(qa.maritalStatus.equals("Married")){
        		((RadioButton)rootView.findViewById(R.id.rdMarried)).setChecked(true);}
        	if(qa.maritalStatus.equals("Single")){
        		((RadioButton)rootView.findViewById(R.id.rdSingle)).setChecked(true);
        	}

        	((EditText) rootView.findViewById(R.id.txtDateBirth)).setText(qa.dateBirth);
        	((EditText) rootView.findViewById(R.id.txtFirstName)).setText(qa.firstName);
        	((EditText) rootView.findViewById(R.id.txtIncomeLevel)).setText(qa.incomeLevel);
        	((EditText) rootView.findViewById(R.id.txtLastName)).setText(qa.lastName);
        	((EditText) rootView.findViewById(R.id.txtLicenseNum)).setText(qa.licenseNum);
        	((EditText) rootView.findViewById(R.id.txtLicenseState)).setText(qa.licenseState);
        	((EditText) rootView.findViewById(R.id.txtMiddleInitial)).setText(qa.middleInitial);
        	((EditText) rootView.findViewById(R.id.txtOccupation)).setText(qa.occupation);
        	((EditText) rootView.findViewById(R.id.txtRelationApplicant)).setText(qa.relationApplicant);  
        }
        }
        catch(Exception e){
        	showMessage("Error loading driver info");
        }
        
        return rootView;
    }
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			//EditText dateBirth = (EditText) view.findViewById(R.id.txtDateBirth);	
			_dateBirth.setText(Integer.toString(selectedMonth) + "/" + Integer.toString(selectedDay) + "/" + Integer.toString(selectedYear));
			_rdMale.requestFocus();
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
			if(db.getQuoteDriverCount(gv.getQuoteID()) == 0){
				QuoteDriver qa = new QuoteDriver();
				qa.quoteID = gv.getQuoteID();
				qa.firstName = ((EditText)_rootView.findViewById(R.id.txtFirstName)).getText().toString();
				qa.middleInitial = ((EditText)_rootView.findViewById(R.id.txtMiddleInitial)).getText().toString();
				qa.lastName = ((EditText)_rootView.findViewById(R.id.txtLastName)).getText().toString();
				
				RadioGroup rg = (RadioGroup) _rootView.findViewById(R.id.Dependents);
		        String selectedDependentValue = ((RadioButton)_rootView.findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
		    	qa.dependents = selectedDependentValue;
				
		    	RadioGroup rg2 = (RadioGroup) _rootView.findViewById(R.id.Gender);
		        String selectedGenderValue = ((RadioButton)_rootView.findViewById(rg2.getCheckedRadioButtonId() )).getText().toString();
		    	qa.gender = selectedGenderValue;
		    	
				qa.incomeLevel = ((EditText)_rootView.findViewById(R.id.txtIncomeLevel)).getText().toString();
				qa.licenseNum = ((EditText)_rootView.findViewById(R.id.txtLicenseNum)).getText().toString();
				qa.licenseState = ((EditText)_rootView.findViewById(R.id.txtLicenseState)).getText().toString();
				
				RadioGroup rg3 = (RadioGroup) _rootView.findViewById(R.id.MaritalStatus);
		        String selectedMaritalStatusValue = ((RadioButton)_rootView.findViewById(rg3.getCheckedRadioButtonId() )).getText().toString();
		    	qa.maritalStatus = selectedMaritalStatusValue;
				
		    	qa.occupation = ((EditText)_rootView.findViewById(R.id.txtOccupation)).getText().toString();			
				qa.relationApplicant = ((EditText)_rootView.findViewById(R.id.txtRelationApplicant)).getText().toString();		
				db.createQuoteDriver(qa);
			}
			else{
				QuoteDriver qa = new QuoteDriver();
				qa.quoteID = gv.getQuoteID();
				qa.firstName = ((EditText)_rootView.findViewById(R.id.txtFirstName)).getText().toString();
				qa.middleInitial = ((EditText)_rootView.findViewById(R.id.txtMiddleInitial)).getText().toString();
				qa.lastName = ((EditText)_rootView.findViewById(R.id.txtLastName)).getText().toString();
				qa.dateBirth = ((EditText)_rootView.findViewById(R.id.txtDateBirth)).getText().toString();
				
				RadioGroup rg = (RadioGroup) _rootView.findViewById(R.id.Dependents);
		        String selectedDependentValue = ((RadioButton)_rootView.findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
		    	qa.dependents = selectedDependentValue;
				
		    	RadioGroup rg2 = (RadioGroup) _rootView.findViewById(R.id.Gender);
		        String selectedGenderValue = ((RadioButton)_rootView.findViewById(rg2.getCheckedRadioButtonId() )).getText().toString();
		    	qa.gender = selectedGenderValue;
		    	
				qa.incomeLevel = ((EditText)_rootView.findViewById(R.id.txtIncomeLevel)).getText().toString();
				qa.licenseNum = ((EditText)_rootView.findViewById(R.id.txtLicenseNum)).getText().toString();
				qa.licenseState = ((EditText)_rootView.findViewById(R.id.txtLicenseState)).getText().toString();
				
				RadioGroup rg3 = (RadioGroup) _rootView.findViewById(R.id.MaritalStatus);
		        String selectedMaritalStatusValue = ((RadioButton)_rootView.findViewById(rg3.getCheckedRadioButtonId() )).getText().toString();
		    	qa.maritalStatus = selectedMaritalStatusValue;
				
		    	qa.occupation = ((EditText)_rootView.findViewById(R.id.txtOccupation)).getText().toString();			
				qa.relationApplicant = ((EditText)_rootView.findViewById(R.id.txtRelationApplicant)).getText().toString();			
				
				db.updateQuoteDriver(qa);
			}
            gv.setTabID(3);
            Intent intent = new Intent(getActivity(), QuoteTabActivity.class); startActivity(intent);
            break;
        case R.id.txtDateBirth:
        	DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener, year - 20, 6, 15);
            dialog.show();
            break;
        case R.id.txtIncomeLevel:
//        	SpinnerData sd = new SpinnerData(getActivity());
//        	List<DropdownData> resList = sd.GetIncomeLevelList();
//        	final CharSequence[] descList = new CharSequence[resList.size()];
//        	for(int i = 0; i < resList.size(); i++){
//        		descList[i] = resList.get(i).getDescription();
//        	}
//        	
//        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        	builder.setTitle("Income Level");
//        	builder.setItems(descList, new DialogInterface.OnClickListener() {
//	               public void onClick(DialogInterface dialog, int which) {
//	               _incomeLevel.setText(descList[which]);
//	               _firstName.requestFocus();
//	           }
//        	});
//        	builder.show();
        	break;
        case R.id.txtOccupation:
//        	SpinnerData sdOccupation = new SpinnerData(getActivity());
//        	List<DropdownData> resListOccupation = sdOccupation.GetOccupationList();
//        	final CharSequence[] descListOccupation = new CharSequence[resListOccupation.size()];
//        	for(int i = 0; i < resListOccupation.size(); i++){
//        		descListOccupation[i] = resListOccupation.get(i).getDescription();
//        	}
//        	
//        	AlertDialog.Builder builderOccupation = new AlertDialog.Builder(getActivity());
//        	builderOccupation.setTitle("Occupation");
//        	builderOccupation.setItems(descListOccupation, new DialogInterface.OnClickListener() {
//	               public void onClick(DialogInterface dialog, int which) {
//	               _occupation.setText(descListOccupation[which]);
//	               _firstName.requestFocus();
//	           }
//        	});
//        	builderOccupation.show();
        	break;
        case R.id.txtLicenseState:
        	final CharSequence[] states = GlobalVariables.getInstance().GetStateList();

        	AlertDialog.Builder builderState = new AlertDialog.Builder(getActivity());
        	builderState.setTitle("Pick a State");
        	builderState.setItems(GlobalVariables.getInstance().GetStateList(), new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               _licenseState.setText(states[which]);
	           }
        	});
        	builderState.show();
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
