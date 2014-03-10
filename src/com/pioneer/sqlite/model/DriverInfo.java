package com.pioneer.sqlite.model;

public class DriverInfo {

	int id;
	String description;
	String code;

	// constructors
	public DriverInfo() {

	}

	public DriverInfo(String _description, String _code) {
		this.description = _description;
		this.code = _code;
	}

	public DriverInfo(int id, String _description, String _code) {
		this.id = id;
		this.description = _description;
		this.code = _code;
	}

	// setter
	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String _description) {
		this.description = _description;
	}
	
	public void setCode(String _code) {
		this.code = _code;
	}

	// getter
	public int getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}
	
	public String getCode() {
		return this.code;
	}
}

