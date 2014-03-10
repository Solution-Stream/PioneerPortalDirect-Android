package com.pioneer.portaldirect;

import com.pioneer.sqlite.helper.DatabaseHelper;

public class GlobalVariables {
	private static GlobalVariables _instance = null;
	private int _connectionTimeout = 3000;
	private String _serverName = "http://10.200.50.62";
	private DatabaseHelper _helper;
	private String _QuoteID;
	private int _tabID;
	private boolean _showQuoteDoesNotExist;
	private int _VehicleID = -1;
	
	private GlobalVariables() {
		
	}
	
	public static GlobalVariables getInstance() {
	    if (_instance == null)
	        _instance = new GlobalVariables();
	    return _instance;
	}
	
	public int getConnectionTimeout() {
	    return _connectionTimeout;
	}
	
	public boolean getShowQuoteDoesNotExist(){
		return _showQuoteDoesNotExist;
	}
	
	public int getVehicleID(){
		return _VehicleID;
	}
	
	public String getServerName(){
		return _serverName;
	}
	
	public String getQuoteID(){
		return _QuoteID;
	}
	
	public int getTabID(){
		return _tabID;
	}
	
	public DatabaseHelper getDatabaseHelper(){
		return _helper;
	}
	
	public void setConnectionTimeout(int value) {
		_connectionTimeout = value;
	}
	
	public void setQuoteID(String value){
		_QuoteID = value;
	}
	
	public void setTabID(int value){
		_tabID = value;
	}
		
	public void setVehicleID(int value){
		_VehicleID = value;
	}
	
	public void setQuoteDoesNotExist(boolean value){
		_showQuoteDoesNotExist = value;
	}
	
	public void setDatabaseHelper(DatabaseHelper helper){
		_helper = helper;
	}
	
	public CharSequence[] GetStateList(){
		final CharSequence[] states = 
					{"Alabama", "Alaska", "Arizona", "Arkansas", "California",

                     "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",

                     "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",

                     "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",

                     "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",

                     "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",

                     "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",

                     "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin",

                     "Wyoming"};
		return states;
	}
	
	
}