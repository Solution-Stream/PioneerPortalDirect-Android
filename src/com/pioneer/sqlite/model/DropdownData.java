package com.pioneer.sqlite.model;

public class DropdownData {

	int id;
	String description;
	String code;
	String name;

	// constructors
	public DropdownData() {

	}

	public DropdownData(String _description, String _code, String _name) {
		this.description = _description;
		this.code = _code;
		this.name = _name;
	}

	public DropdownData(int id, String _description, String _code, String _name) {
		this.id = id;
		this.description = _description;
		this.code = _code;
		this.name = _name;
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
	
	public void setName(String _name) {
		this.name = _name;
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
	
	public String getName() {
		return this.name;
	}
}

