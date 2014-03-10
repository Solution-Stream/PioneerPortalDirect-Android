package com.pioneer.portaldirect;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;

import com.pioneer.sqlite.helper.DatabaseHelper;
import com.pioneer.sqlite.model.DropdownData;

public class SpinnerData {

	Context _context;
	
	public SpinnerData(Context context) {
		_context = context;
	}
	
	public List<DropdownData> GetResidencyList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("BUILDING_USE", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetIncomeLevelList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("AnnualIncomeList", "DESC");		
		return data;
	}
	
	public List<DropdownData> GetOccupationList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("OCCUPATION_CODE", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetRelationApplicantList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("RELATION_TO_APPL", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetVehicleTypeList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("VEH_TYPE", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetYearList(){
		Calendar cal = Calendar.getInstance();
	    String timezoneID = TimeZone.getDefault().getID();
	    cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
	    final int year=cal.get(Calendar.YEAR);
		List<DropdownData> data = new ArrayList<DropdownData>();	
		
		for(int i = year + 1; i >= 1886; i--){
			DropdownData d = new DropdownData();
			d.setDescription(String.valueOf(i));
			d.setCode(String.valueOf(i));
			data.add(d);
		}
		return data;
	}
	
	public List<DropdownData> GetAntiLockBrakeList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("ANTI_LOCK_BRAKES", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetPassiveRestraintList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("PASSIVE_RESTRAINT", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetAntiTheftDeviceList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("ANTI_THEFT", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetVehicleUsageList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("VEH_USE", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetWorkWeekList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("DAYS_PER_WEEK", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetPropertyDamageList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("PROPERTY_DAMAGE", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetUnderinsuredLimitList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("UNDERINSD_LIMIT", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetUninsuredLimitList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("UNINSURED_LIMIT", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetMiniTortList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("MINI_TORT", "ASC");		
		return data;
	}
	
	public List<DropdownData> GetPropertyProtectionList(){
		List<DropdownData> data = DatabaseHelper.getInstance(_context).getAllDropdownData("PROPERTY_PROTECT", "ASC");		
		return data;
	}
	
	
	
	
}
