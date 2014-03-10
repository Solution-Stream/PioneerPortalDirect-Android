package com.pioneer.sqlite.model;

public class QuoteVehicle {

	public int id;
	public String quoteID;
	public String annualMileage;
	public String antiLockBrakes;
	public String antiTheftDevice;
	public String assignedDriverID;
	public String assignedDriverName;
	public String carpool;
	public String completed;
	public String garagingZipCode;
	public String make;
	public String milesToWork;
	public String model;
	public String passiveRestraints;
	public String splitCity;
	public String vehicleID;
	public String vehicleType;
	public String vehicleTypeCode;
	public String vehicleUsage;
	public String VIN;
	public String workWeek;
	public String year;

	// constructors
	public QuoteVehicle() {

	}

	public QuoteVehicle(
			int _id,
		    String _quoteID,
		    String _annualMileage,
			String _antiLockBrakes,
			String _antiTheftDevice,
			String _assignedDriverID,
			String _assignedDriverName,
			String _carpool,
			String _completed,
			String _garagingZipCode,
			String _make,
			String _milesToWork,
			String _model,
			String _passiveRestraints,
			String _splitCity,
			String _vehicleID,
			String _vehicleType,
			String _vehicleTypeCode,
			String _vehicleUsuage,
			String _VIN,
			String _workWeek,
			String _year
			) {
		this.id = _id;
		this.quoteID = _quoteID;
		this.annualMileage = _annualMileage;
		this.antiLockBrakes = _antiLockBrakes;
		this.antiTheftDevice = _antiTheftDevice;
		this.assignedDriverID = _assignedDriverID;
		this.assignedDriverName = _assignedDriverName;
		this.carpool = _carpool;
		this.completed = _completed;
		this.garagingZipCode = _garagingZipCode;
		this.make = _make;
		this.milesToWork = _milesToWork;
		this.model = _model;
		this.passiveRestraints = _passiveRestraints;
		this.splitCity = _splitCity;
		this.vehicleID = _vehicleID;
		this.vehicleType = _vehicleType;
		this.vehicleTypeCode = _vehicleTypeCode;
		this.vehicleUsage = _vehicleUsuage;
		this.VIN = _VIN;
		this.workWeek = _workWeek;
		this.year = _year;
	}
}

