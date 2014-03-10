package com.pioneer.sqlite.model;

public class QuoteDriver {

	public int id;
	public String quoteID;
	public String dateBirth;
	public String dependents;
	public String driverID;
	public String firstName;
	public String gender;
	public String incomeLevel;
	public String lastName;
	public String licenseNum;
	public String licenseState;
	public String maritalStatus;
	public String middleInitial;
	public String occupation;
	public String relationApplicant;

	// constructors
	public QuoteDriver() {

	}

	public QuoteDriver(
			int id,
		    String _quoteID,
			String _dateBirth,
			String _dependents,
			String _driverID,
			String _firstName,
			String _gender,
			String _incomeLevel,
			String _lastName,
			String _licenseNum,
			String _licenseState,
			String _maritalStatus,
			String _middleInitial,
			String _occupation,
			String _relationApplicant
			) {
		this.quoteID = _quoteID;
		this.dateBirth = _dateBirth;
		this.dependents = _dependents;
		this.driverID = _driverID;
		this.firstName = _firstName;
		this.gender = _gender;
		this.incomeLevel = _incomeLevel;
		this.lastName = _lastName;
		this.licenseNum = _licenseNum;
		this.licenseState = _licenseState;
		this.maritalStatus = _maritalStatus;
		this.middleInitial = _middleInitial;
		this.occupation = _occupation;
		this.relationApplicant = _relationApplicant;
	}

//	public QuoteDriver getQuoteDriver(){
//		QuoteApplicant qa = new QuoteApplicant();
//		qa.address = this.address;
//		qa.city = this.city;
//		qa.completed = this.completed;
//		qa.dateBirth = this.dateBirth;
//		qa.email = this.email;
//		qa.firstName = this.firstName;
//		qa.lastName = this.lastName;
//		qa.middle = this.middle;
//		qa.quoteId = this.quoteId;
//		qa.residencyType = this.residencyType;
//		qa.ssn = this.ssn;
//		qa.state = this.state;
//		qa.zip = this.zip;
//		
//		return qa;
//	}
}

