package com.pioneer.sqlite.model;

public class QuoteApplicant {
	public int id;
	public String quoteId;
	public String address;
	public String city;
	public String completed;
	public String dateBirth;
	public String email;
	public String firstName;
	public String lastName;
	public String middle;
	public String residencyType;
	public String ssn;
	public String state;
	public String zip;

	// constructors
	public QuoteApplicant() {

	}

	public QuoteApplicant(
    int id,
    String _quoteId,
	String _address,
	String _city,
	String _completed,
	String _dateBirth,
	String _email,
	String _firstName,
	String _lastName,
	String _middle,
	String _residencyType,
	String _ssn,
	String _state,
	String _zip) {
		this.quoteId = _quoteId;
		this.address = _address;
		this.city = _city;
		this.completed = _completed;
		this.dateBirth = _dateBirth;
		this.email = _email;
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.middle = _middle;
		this.residencyType = _residencyType;
		this.ssn = _ssn;
		this.state = _state;
		this.zip = _zip;
	}

//	public QuoteApplicant(int id, String _description, String _code) {
//		this.id = id;
//		this.description = _description;
//		this.code = _code;
//	}

//	// setter
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public void setDescription(String _description) {
//		this.description = _description;
//	}
//	
//	public void setCode(String _code) {
//		this.code = _code;
//	}

	// getter
//	public int getId() {
//		return this.id;
//	}
//
//	public String getDescription() {
//		return this.description;
//	}
//	
//	public String getCode() {
//		return this.code;
//	}
	
	public QuoteApplicant getQuoteApplicant(){
		QuoteApplicant qa = new QuoteApplicant();
		qa.address = this.address;
		qa.city = this.city;
		qa.completed = this.completed;
		qa.dateBirth = this.dateBirth;
		qa.email = this.email;
		qa.firstName = this.firstName;
		qa.lastName = this.lastName;
		qa.middle = this.middle;
		qa.quoteId = this.quoteId;
		qa.residencyType = this.residencyType;
		qa.ssn = this.ssn;
		qa.state = this.state;
		qa.zip = this.zip;
		
		return qa;
	}
}

