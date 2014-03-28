package com.pioneer.sqlite.helper;

import com.pioneer.sqlite.model.DropdownData;
import com.pioneer.sqlite.model.DriverInfo;
import com.pioneer.sqlite.model.VehicleList;
import com.pioneer.sqlite.model.Quotes;
import com.pioneer.sqlite.model.QuoteVehicle;
import com.pioneer.sqlite.model.QuoteDriver;
import com.pioneer.sqlite.model.QuoteCoverages;
import com.pioneer.sqlite.model.QuoteApplicant;
//import info.androidhive.sqlite.model.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static DatabaseHelper sInstance = null;
	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 14;

	// Database Name
	private static final String DATABASE_NAME = "PortalDirect.db";

	// Table Names
	private static final String TABLE_DropdownData = "dropdownData";
	private static final String TABLE_QuoteApplicant = "QuoteApplicant";
	private static final String TABLE_Quotes = "Quotes";
	private static final String TABLE_QuoteDriver = "QuoteDriver";
	private static final String TABLE_QuoteVehicle = "QuoteVehicle";
	private static final String TABLE_QuoteCoverages = "QuoteCoverages";
	
	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";

	// NOTES Table - column names
	private static final String KEY_Description = "description";
	private static final String KEY_Code = "code";
	private static final String KEY_Name = "name";

	// QuoteApplicant Table - column names
	private static final String KEY_Address = "address";
	private static final String KEY_City = "city";
	private static final String KEY_Completed = "completed";
	private static final String KEY_DateBirth = "dateBirth";
	private static final String KEY_Email = "email";
	private static final String KEY_FirstName = "firstName";
	private static final String KEY_LastName = "lastName";
	private static final String KEY_Middle = "middle";
	private static final String KEY_QuoteID = "quoteID";
	private static final String KEY_ResidencyType = "residencyType";
	private static final String KEY_SSN = "ssn";
	private static final String KEY_State = "state";
	private static final String KEY_Zip = "zip";
	
	// QuoteDriver Table - column names
	private static final String KEY_Dependents = "dependents";
	private static final String KEY_DriverID = "driverID";
	private static final String KEY_Gender = "gender";
	private static final String KEY_IncomeLevel = "incomeLevel";
	private static final String KEY_LicenseNum = "licenseNum";
	private static final String KEY_LicenseState = "licenseState";
	private static final String KEY_MaritalStatus = "maritalStatus";
	private static final String KEY_Occupation = "occupation";
	private static final String KEY_RelationApplicant = "relationApplicant";
	private static final String KEY_MiddleInitial = "middleInitial";
	
	// QuoteVehicle Table - column names
	private static final String KEY_AnnualMileage = "annualMileage";
	private static final String KEY_AntiLockBrakes = "antiLockBrakes";
	private static final String KEY_AntiTheftDevice = "antiTheftDevice";
	private static final String KEY_AssignedDriverID = "assignedDriverID";
	private static final String KEY_AssignedDriverName = "assignedDriverName";
	private static final String KEY_Carpool = "carpool";
	private static final String KEY_GaragingZipCode = "garagingZipCode";
	private static final String KEY_Make = "make";
	private static final String KEY_MilesToWork = "milesToWork";
	private static final String KEY_Model = "model";
	private static final String KEY_PassiveRestraints = "passiveRestraints";
	private static final String KEY_SplitCity = "splitCity";
	private static final String KEY_VehicleType = "vehicleType";
	private static final String KEY_VehicleTypeCode = "vehicleTypeCode";
	private static final String KEY_VehicleUsage = "vehicleUsage";
	private static final String KEY_VIN = "vin";
	private static final String KEY_WorkWeek = "workWeek";
	private static final String KEY_Year = "year";
	
	// QuoteCoverages Table - column names
	private static final String KEY_BodilyInjury = "bodilyInjury";
	private static final String KEY_MedicalProvider = "medicalProvider";
	private static final String KEY_MiniTort = "miniTort";
	private static final String KEY_PersonalInjuryProtection = "personalInjuryProtection";
	private static final String KEY_PropertyDamage = "propertyDamage";
	private static final String KEY_PropertyProtection = "propertyProtection";
	private static final String KEY_UninsuredMotorist = "uninsuredMotorist";
	private static final String KEY_UnderInsuredMotorist = "underinsuredMotorist";
	
	//Quotes Table - column names
	private static final String KEY_QuoteName = "quoteName";
	private static final String KEY_QuoteStatus = "quoteStatus";
	private static final String KEY_QuoteSubmitted = "quoteSubmitted";
	
		
	private static final String CREATE_TABLE_DropdownData = "CREATE TABLE "
			+ TABLE_DropdownData + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Description
			+ " TEXT," + KEY_Code + " INTEGER," + KEY_Name + " TEXT," + KEY_CREATED_AT
			+ " DATETIME" + ")";
	
	private static final String CREATE_TABLE_QuoteApplicant = "CREATE TABLE "
			+ TABLE_QuoteApplicant + "(" + 
			"id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
			"quoteID" + " TEXT," + 
			"address" + " TEXT," +
			"city" + " TEXT," +
			"completed" + " TEXT," +
			"dateBirth" + " TEXT," +
			"email" + " TEXT," +
			"firstName" + " TEXT," +
			"lastName" + " TEXT," +
			"middle" + " TEXT," +
			"residencyType" + " TEXT," +
			"ssn" + " TEXT," +
			"state" + " TEXT," +
			"zip" + " TEXT" + ")";
	
	private static final String CREATE_TABLE_QuoteDriver = "CREATE TABLE "
			+ TABLE_QuoteDriver + "(" + 
			"id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
			"quoteID" + " TEXT," + 
			"dateBirth" + " TEXT," +
			"dependents" + " TEXT," +
			"driverID" + " TEXT," +
			"firstName" + " TEXT," +
			"gender" + " TEXT," +
			"incomeLevel" + " TEXT," +
			"lastName" + " TEXT," +
			"licenseNum" + " TEXT," +
			"licenseState" + " TEXT," +
			"maritalStatus" + " TEXT," +
			"middleInitial" + " TEXT," +
			"relationApplicant" + " TEXT," +
			"occupation" + " TEXT" + ")";
	
	private static final String CREATE_TABLE_Quotes = "CREATE TABLE "
			+ TABLE_Quotes + "(" + 
			"id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
			"quoteID" + " TEXT," + 
			"quoteName" + " TEXT," +
			"quoteStatus" + " TEXT," +
			"quoteSubmitted" + " TEXT" + ")";
			
	private static final String CREATE_TABLE_QuoteVehicle = "CREATE TABLE "
			+ TABLE_QuoteVehicle + "(" + 
			"id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
			"quoteID" + " TEXT," + 
			"annualMileage" + " TEXT," +
			"antiLockBrakes" + " TEXT," +
			"antiTheftDevice" + " TEXT," +
			"assignedDriverID" + " TEXT," +
			"assignedDriverName" + " TEXT," +
			"carpool" + " TEXT," +
			"completed" + " TEXT," +
			"garagingZipCode" + " TEXT," +
			"make" + " TEXT," +
			"milesToWork" + " TEXT," +
			"model" + " TEXT," +
			"passiveRestraints" + " TEXT," +
			"splitCity" + " TEXT" +
			"vehicleID" + " TEXT," +
			"vehicleType" + " TEXT," +
			"vehicleTypeCode" + " TEXT," +
			"vehicleUsage" + " TEXT," +
			"vin" + " TEXT," +
			"workWeek" + " TEXT," +
			"year" + " TEXT" + ")";
	
	private static final String CREATE_TABLE_QuoteCoverages = "CREATE TABLE "
			+ TABLE_QuoteCoverages + "(" + 
			"id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
			"quoteID" + " TEXT," + 
			"bodilyInjury" + " TEXT," +
			"medicalProvider" + " TEXT," +
			"miniTort" + " TEXT," +
			"personalInjuryProtection" + " TEXT," +
			"propertyDamage" + " TEXT," +
			"propertyProtection" + " TEXT," +
			"underinsuredMotorist" + " TEXT," +
			"uninsuredMotorist" + " TEXT" + ")";
			
			

	// Tag table create statement
//	private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
//			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
//			+ KEY_CREATED_AT + " DATETIME" + ")";

	// todo_tag table create statement
//	private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
//			+ TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
//			+ KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
//			+ KEY_CREATED_AT + " DATETIME" + ")";
	public static DatabaseHelper getInstance(Context context) {
	      
	    // Use the application context, which will ensure that you 
	    // don't accidentally leak an Activity's context.
	    // See this article for more information: http://bit.ly/6LRzfx
	    if (sInstance == null) {
	      sInstance = new DatabaseHelper(context.getApplicationContext());
	    }
	    return sInstance;
	  }

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_DropdownData);
		db.execSQL(CREATE_TABLE_QuoteApplicant);
		db.execSQL(CREATE_TABLE_QuoteDriver);
		db.execSQL(CREATE_TABLE_Quotes);
		db.execSQL(CREATE_TABLE_QuoteVehicle);
		db.execSQL(CREATE_TABLE_QuoteCoverages);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DropdownData);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QuoteApplicant);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QuoteDriver);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Quotes);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QuoteVehicle);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QuoteCoverages);
		// create new tables
		onCreate(db);
	}

	//-----------------------Insert Objects-------------------------------
//	public long createAnnualIncome(AnnualIncome todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_AnnualIncome, null, values);
//
//		return row_id;
//	}
//	
//	public long createAntiLockBrake(AntiLockBrake todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_AntiLockBrake, null, values);
//
//		return row_id;
//	}
//	
//	public long createDaysWeek(DaysWeek todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_DaysWeek, null, values);
//
//		return row_id;
//	}
//	
//	public long createEmploymentStatus(DriverInfo todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_EmploymentStatus, null, values);
//
//		return row_id;
//	}
//	
//	public long createMiniTort(VehicleList todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_MiniTort, null, values);
//
//		return row_id;
//	}
//	
//	public long createOccupations(Occupations todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_Occupations, null, values);
//
//		return row_id;
//	}
//	
//	public long createPassiveRestraint(Quotes todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_PassiveRestraint, null, values);
//
//		return row_id;
//	}
//	
//	public long createPropertyDamage(QuoteVehicle todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_PropertyDamage, null, values);
//
//		return row_id;
//	}
//	
//	public long createPropertyProtection(QuoteDriver todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_PropertyProtection, null, values);
//
//		return row_id;
//	}
//	
//	public long createUnderInsuredMotorist(QuoteCoverages todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_UnderinsuredMotorist, null, values);
//
//		return row_id;
//	}
//	
//	public long createUninsuredMotorist(QuoteApplicant todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_UninsuredMotorist, null, values);
//
//		return row_id;
//	}
//	
//	public long createVehicleType(VehicleType todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_Description, todo.getDescription());
//		values.put(KEY_Code, todo.getCode());
//		values.put(KEY_CREATED_AT, getDateTime());
//
//		long row_id = db.insert(TABLE_VehicleType, null, values);
//
//		return row_id;
//	}
	
	public long createDropdownData(DropdownData todo) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_Description, todo.getDescription());
		values.put(KEY_Code, todo.getCode());
		values.put(KEY_Name, todo.getName());
		values.put(KEY_CREATED_AT, getDateTime());

		long row_id = db.insert(TABLE_DropdownData, null, values);
		
		db.setTransactionSuccessful();
		return row_id;
		}
		catch(Exception e){
			return 0;
		}
		finally {
			db.endTransaction();
			db.close();
		}
	}
	
	public long createQuote(Quotes todo) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_QuoteID, todo.quoteID);
		values.put(KEY_QuoteName, todo.quoteName);
		values.put(KEY_QuoteStatus, todo.quoteStatus);
		values.put(KEY_QuoteSubmitted, todo.quoteSubmitted);

		long row_id = db.insertOrThrow(TABLE_Quotes, null, values);
		
		db.setTransactionSuccessful();
		return row_id;
		}
		catch(Exception e){
			String error = e.getMessage();
			error = error + "l";
			return 0;
		}
		finally {
			db.endTransaction();
			db.close();
		}
	}
	
	public String createQuoteApplicant(QuoteApplicant todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_Address, todo.address);
		values.put(KEY_City, todo.city);
		values.put(KEY_Completed, todo.completed);
		values.put(KEY_DateBirth, todo.dateBirth);
		values.put(KEY_Email, todo.email);
		values.put(KEY_FirstName, todo.firstName);
		values.put(KEY_LastName, todo.lastName);
		values.put(KEY_Middle, todo.middle);
		values.put(KEY_QuoteID, todo.quoteId);
		values.put(KEY_ResidencyType, todo.residencyType);
		values.put(KEY_SSN, todo.ssn);
		values.put(KEY_State, todo.state);
		values.put(KEY_Zip, todo.zip);		

		long _row_id = db.insertOrThrow(TABLE_QuoteApplicant, "quoteID", values);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	
	public String createQuoteVehicle(QuoteVehicle todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_AnnualMileage, todo.annualMileage);
		values.put(KEY_AntiLockBrakes, todo.antiLockBrakes);
		values.put(KEY_AntiTheftDevice, todo.antiTheftDevice);
		values.put(KEY_AssignedDriverID, todo.assignedDriverID);
		values.put(KEY_AssignedDriverName, todo.assignedDriverName);
		values.put(KEY_Carpool, todo.carpool);
		values.put(KEY_Completed, todo.completed);
		values.put(KEY_GaragingZipCode, todo.garagingZipCode);
		values.put(KEY_Make, todo.make);
		values.put(KEY_MilesToWork, todo.milesToWork);
		values.put(KEY_Model, todo.model);
		values.put(KEY_PassiveRestraints, todo.passiveRestraints);
		values.put(KEY_QuoteID, todo.quoteID);	
		values.put(KEY_SplitCity, todo.splitCity);
		values.put(KEY_VehicleType, todo.vehicleType);
		values.put(KEY_VehicleTypeCode, todo.vehicleTypeCode);
		values.put(KEY_VehicleUsage, todo.vehicleUsage);
		values.put(KEY_VIN, todo.VIN);
		values.put(KEY_WorkWeek, todo.workWeek);
		values.put(KEY_Year, todo.year);		

		long _row_id = db.insert(TABLE_QuoteVehicle, null, values);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	
	public String createQuoteDriver(QuoteDriver todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_DateBirth, todo.dateBirth);
		values.put(KEY_Dependents, todo.dependents);
		values.put(KEY_DriverID, todo.driverID);
		values.put(KEY_FirstName, todo.firstName);
		values.put(KEY_Gender, todo.gender);
		values.put(KEY_IncomeLevel, todo.incomeLevel);
		values.put(KEY_LastName, todo.lastName);
		values.put(KEY_LicenseNum, todo.licenseNum);
		values.put(KEY_LicenseState, todo.licenseState);
		values.put(KEY_MaritalStatus, todo.maritalStatus);
		values.put(KEY_Occupation, todo.occupation);
		values.put(KEY_QuoteID, todo.quoteID);
		values.put(KEY_RelationApplicant, todo.relationApplicant);		

		long _row_id = db.insertOrThrow(TABLE_QuoteDriver, "quoteID", values);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	
	public String createQuoteCoverages(QuoteCoverages todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_BodilyInjury, todo.bodilyInjury);
		values.put(KEY_MedicalProvider, todo.medicalProvider);
		values.put(KEY_MiniTort, todo.miniTort);
		values.put(KEY_PersonalInjuryProtection, todo.personaInjuryProtection);
		values.put(KEY_PropertyDamage, todo.propertyDamage);
		values.put(KEY_PropertyProtection, todo.propertyProtection);
		values.put(KEY_QuoteID, todo.quoteID);
		values.put(KEY_UninsuredMotorist, todo.uninsuredMotorist);
		values.put(KEY_UnderInsuredMotorist, todo.underinsuredMotorist);

		long _row_id = db.insertOrThrow(TABLE_QuoteCoverages, "quoteID", values);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	//------------------------------- Get Object-----------------------------------------------------
	
	public List<DropdownData> getAllDropdownData(String name, String sorting) {
		List<DropdownData> DropdownDatas = new ArrayList<DropdownData>();
		String selectQuery = "SELECT  * FROM " + TABLE_DropdownData + " where name = '" + name + "' order by description " + sorting;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				DropdownData td = new DropdownData();
				td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				td.setDescription((c.getString(c.getColumnIndex(KEY_Description))));
				td.setCode(c.getString(c.getColumnIndex(KEY_Code)));
				td.setName((c.getString(c.getColumnIndex(KEY_Name))));

				DropdownDatas.add(td);
			} while (c.moveToNext());
		}
		
		closeDB();

		return DropdownDatas;
	}
	
	public QuoteApplicant getQuoteApplicantData(String id) {
		String selectQuery = "SELECT  * FROM " + TABLE_QuoteApplicant + " where quoteID = '" + id + "'";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		
		QuoteApplicant qa = new QuoteApplicant();
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {						
				qa.address = c.getString(c.getColumnIndex(KEY_Address));
				qa.city = c.getString(c.getColumnIndex(KEY_City));
				qa.dateBirth = c.getString(c.getColumnIndex(KEY_DateBirth));
				qa.email = c.getString(c.getColumnIndex(KEY_Email));
				qa.firstName = c.getString(c.getColumnIndex(KEY_FirstName));
				qa.lastName = c.getString(c.getColumnIndex(KEY_LastName));
				qa.middle = c.getString(c.getColumnIndex(KEY_Middle));
				qa.residencyType = c.getString(c.getColumnIndex(KEY_ResidencyType));
				qa.ssn = c.getString(c.getColumnIndex(KEY_SSN));
				qa.state = c.getString(c.getColumnIndex(KEY_State));
				qa.zip = c.getString(c.getColumnIndex(KEY_Zip));
			} while (c.moveToNext());
		}
		
		closeDB();

		return qa;
	}
	
	public QuoteDriver getQuoteDriverData(String id) {
		String selectQuery = "SELECT  * FROM " + TABLE_QuoteDriver + " where quoteID = '" + id + "'";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		
		QuoteDriver qa = new QuoteDriver();
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {						
				qa.dateBirth = c.getString(c.getColumnIndex(KEY_DateBirth));
				qa.dependents = c.getString(c.getColumnIndex(KEY_Dependents));
				qa.driverID = c.getString(c.getColumnIndex(KEY_DriverID));
				qa.firstName = c.getString(c.getColumnIndex(KEY_FirstName));
				qa.gender = c.getString(c.getColumnIndex(KEY_Gender));
				qa.incomeLevel = c.getString(c.getColumnIndex(KEY_IncomeLevel));
				qa.lastName = c.getString(c.getColumnIndex(KEY_LastName));
				qa.licenseNum = c.getString(c.getColumnIndex(KEY_LicenseNum));
				qa.licenseState = c.getString(c.getColumnIndex(KEY_LicenseState));
				qa.maritalStatus = c.getString(c.getColumnIndex(KEY_MaritalStatus));
				qa.middleInitial = c.getString(c.getColumnIndex(KEY_MiddleInitial));
				qa.occupation = c.getString(c.getColumnIndex(KEY_Occupation));
				qa.quoteID = c.getString(c.getColumnIndex(KEY_QuoteID));
				qa.relationApplicant = c.getString(c.getColumnIndex(KEY_RelationApplicant));
			} while (c.moveToNext());
		}
		
		if(qa.dependents == null){qa.dependents = "";}
		if(qa.maritalStatus == null){qa.maritalStatus = "";}	
		if(qa.gender == null){qa.gender = "";}
				
		closeDB();

		return qa;
	}
	
	public QuoteVehicle getQuoteVehicleByID(int id) {
		QuoteVehicle qa = new QuoteVehicle();
		try{
		String selectQuery = "SELECT  * FROM " + TABLE_QuoteVehicle + " where id = " + String.valueOf(id);

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
				
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {			
				qa.id = c.getInt(c.getColumnIndex("id"));
				qa.annualMileage = c.getString(c.getColumnIndex(KEY_AnnualMileage));
				qa.antiLockBrakes = c.getString(c.getColumnIndex(KEY_AntiLockBrakes));
				qa.antiTheftDevice = c.getString(c.getColumnIndex(KEY_AntiTheftDevice));
				qa.assignedDriverID = c.getString(c.getColumnIndex(KEY_AssignedDriverID));
				qa.assignedDriverName = c.getString(c.getColumnIndex(KEY_AssignedDriverName));
				qa.carpool = c.getString(c.getColumnIndex(KEY_Carpool));
				qa.completed = c.getString(c.getColumnIndex(KEY_Completed));
				qa.garagingZipCode = c.getString(c.getColumnIndex(KEY_GaragingZipCode));
				qa.make = c.getString(c.getColumnIndex(KEY_Make));
				qa.milesToWork = c.getString(c.getColumnIndex(KEY_MilesToWork));
				qa.model = c.getString(c.getColumnIndex(KEY_Model));
				qa.passiveRestraints = c.getString(c.getColumnIndex(KEY_PassiveRestraints));
				qa.quoteID = c.getString(c.getColumnIndex(KEY_QuoteID));
				qa.splitCity = c.getString(c.getColumnIndex(KEY_SplitCity));
				qa.vehicleType = c.getString(c.getColumnIndex(KEY_VehicleType));
				qa.vehicleTypeCode = c.getString(c.getColumnIndex(KEY_VehicleTypeCode));
				qa.vehicleUsage = c.getString(c.getColumnIndex(KEY_VehicleUsage));
				qa.VIN = c.getString(c.getColumnIndex(KEY_VIN));
				qa.workWeek = c.getString(c.getColumnIndex(KEY_WorkWeek));
				qa.year = c.getString(c.getColumnIndex(KEY_Year));
			} while (c.moveToNext());
		}
		
		if(qa.carpool == null){
			qa.carpool = "";
		}
		
		closeDB();
		}
		catch(Exception e)
		{
			qa.id = -1;
		}

		return qa;
	}
	
	public String[] getTabIconDataForQuote(String quoteID){
		String returnString = "";
		try{
			String selectQuery1 = "SELECT * FROM " + TABLE_QuoteApplicant + " where quoteID = '" + quoteID + "'";
			String selectQuery2 = "SELECT * FROM " + TABLE_QuoteDriver + " where quoteID = '" + quoteID + "'";
			String selectQuery3 = "SELECT * FROM " + TABLE_QuoteVehicle + " where quoteID = '" + quoteID + "'";
			String selectQuery4 = "SELECT * FROM " + TABLE_QuoteCoverages + " where quoteID = '" + quoteID + "'";

			Log.e(LOG, selectQuery1);

			SQLiteDatabase db = this.getReadableDatabase();
			
			//QuoteApplicant
			Cursor c1 = db.rawQuery(selectQuery1, null);
					
			if(c1.getCount() > 0){
				returnString = returnString + "1,";
			}
			else{
				returnString = returnString + "0,";
			}
			
			//QuoteDriver
			Cursor c2 = db.rawQuery(selectQuery2, null);
			
			if(c2.getCount() > 0){
				returnString = returnString + "1,";
			}
			else{
				returnString = returnString + "0,";
			}
			
			//QuoteVehicle
			Cursor c3 = db.rawQuery(selectQuery3, null);
			
			if(c3.getCount() > 0){
				returnString = returnString + "1,";
			}
			else{
				returnString = returnString + "0,";
			}
			
			//QuoteCoverages
			Cursor c4 = db.rawQuery(selectQuery4, null);
			
			if(c4.getCount() > 0){
				returnString = returnString + "1,";
			}
			else{
				returnString = returnString + "0,";
			}
					
			closeDB();
			}
			catch(Exception e)
			{
				returnString = e.getMessage();
			}
		
			String[] aString = returnString.split(",");

			return aString;
	}
	
	public QuoteCoverages getQuoteCoverages(String quoteID) {
		QuoteCoverages qa = new QuoteCoverages();
		try{
		String selectQuery = "SELECT  * FROM " + TABLE_QuoteCoverages + " where quoteID = '" + quoteID + "'";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
				
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {			
				qa.id = c.getInt(c.getColumnIndex("id"));
				qa.bodilyInjury = c.getString(c.getColumnIndex(KEY_BodilyInjury));
				qa.medicalProvider = c.getString(c.getColumnIndex(KEY_MedicalProvider));
				qa.miniTort = c.getString(c.getColumnIndex(KEY_MiniTort));
				qa.personaInjuryProtection = c.getString(c.getColumnIndex(KEY_PersonalInjuryProtection));
				qa.propertyDamage = c.getString(c.getColumnIndex(KEY_PropertyDamage));
				qa.propertyProtection = c.getString(c.getColumnIndex(KEY_PropertyProtection));
				qa.quoteID = c.getString(c.getColumnIndex(KEY_QuoteID));
				qa.uninsuredMotorist = c.getString(c.getColumnIndex(KEY_UninsuredMotorist));
				qa.underinsuredMotorist = c.getString(c.getColumnIndex(KEY_UnderInsuredMotorist));
			} while (c.moveToNext());
		}		
				
		closeDB();
		}
		catch(Exception e)
		{
			qa.id = -1;
		}

		return qa;
	}
	
	public List<QuoteVehicle> getAllQuoteVehicles(String quoteID) {
		List<QuoteVehicle> quoteVehicles = new ArrayList<QuoteVehicle>();
		String selectQuery = "SELECT  * FROM " + TABLE_QuoteVehicle + " where quoteID = '" + quoteID + "' order by year";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				QuoteVehicle qa = new QuoteVehicle();
				qa.id = c.getInt(c.getColumnIndex("id"));
				qa.annualMileage = c.getString(c.getColumnIndex(KEY_AnnualMileage));
				qa.antiLockBrakes = c.getString(c.getColumnIndex(KEY_AntiLockBrakes));
				qa.antiTheftDevice = c.getString(c.getColumnIndex(KEY_AntiTheftDevice));
				qa.assignedDriverID = c.getString(c.getColumnIndex(KEY_AssignedDriverID));
				qa.assignedDriverName = c.getString(c.getColumnIndex(KEY_AssignedDriverName));
				qa.carpool = c.getString(c.getColumnIndex(KEY_Carpool));
				qa.completed = c.getString(c.getColumnIndex(KEY_Completed));
				qa.garagingZipCode = c.getString(c.getColumnIndex(KEY_GaragingZipCode));
				qa.make = c.getString(c.getColumnIndex(KEY_Make));
				qa.milesToWork = c.getString(c.getColumnIndex(KEY_MilesToWork));
				qa.model = c.getString(c.getColumnIndex(KEY_Model));
				qa.passiveRestraints = c.getString(c.getColumnIndex(KEY_PassiveRestraints));
				qa.quoteID = c.getString(c.getColumnIndex(KEY_QuoteID));
				qa.splitCity = c.getString(c.getColumnIndex(KEY_SplitCity));
				qa.vehicleType = c.getString(c.getColumnIndex(KEY_VehicleType));
				qa.vehicleTypeCode = c.getString(c.getColumnIndex(KEY_VehicleTypeCode));
				qa.vehicleUsage = c.getString(c.getColumnIndex(KEY_VehicleUsage));
				qa.VIN = c.getString(c.getColumnIndex(KEY_VIN));
				qa.workWeek = c.getString(c.getColumnIndex(KEY_WorkWeek));
				qa.year = c.getString(c.getColumnIndex(KEY_Year));

				quoteVehicles.add(qa);
			} while (c.moveToNext());
		}
		
		closeDB();

		return quoteVehicles;
	}
	
	public List<Quotes> getAllQuotes() {
		List<Quotes> quotes = new ArrayList<Quotes>();
		String selectQuery = "SELECT  * FROM " + TABLE_Quotes + " order by quoteName";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Quotes td = new Quotes();
				td.quoteID = c.getInt(c.getColumnIndex(KEY_ID));
				td.quoteName = c.getString(c.getColumnIndex(KEY_QuoteName));
				td.quoteStatus = c.getString(c.getColumnIndex(KEY_QuoteStatus));
				td.quoteSubmitted = c.getString(c.getColumnIndex(KEY_QuoteSubmitted));

				quotes.add(td);
			} while (c.moveToNext());
		}
		
		closeDB();

		return quotes;
	}
	
	public int getQuoteCount() {
		int count = -100;
		try{
			String countQuery = "SELECT  * FROM " + TABLE_Quotes;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);

			count = cursor.getCount();

			if(cursor != null){
				cursor.close();
			}		
		}
		catch(Exception e){
			count = -200;
		}
				
		return count;
	}
	
	public int getQuoteDriverCount(String quoteID) {
		int count = -100;
		try{
			String countQuery = "SELECT  * FROM " + TABLE_QuoteDriver + " WHERE quoteID =" + quoteID;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);

			count = cursor.getCount();

			if(cursor != null){
				cursor.close();
			}		
		}
		catch(Exception e){
			count = -200;
		}
				
		return count;
	}
	
	public int getQuoteCoveragesCount(String quoteID) {
		int count = -100;
		try{
			String countQuery = "SELECT  * FROM " + TABLE_QuoteCoverages + " WHERE quoteID =" + quoteID;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);

			count = cursor.getCount();

			if(cursor != null){
				cursor.close();
			}		
		}
		catch(Exception e){
			count = -200;
		}
				
		return count;
	}
	
	public int getQuoteVehicleCountForQuote(String quoteID) {
		int count = -100;
		try{
			String countQuery = "SELECT  * FROM " + TABLE_QuoteVehicle + " WHERE quoteID ='" + quoteID + "'";
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);

			count = cursor.getCount();

			if(cursor != null){
				cursor.close();
			}		
		}
		catch(Exception e){
			count = -200;
		}
				
		return count;
	}
	
	public int getQuoteApplicantCount(String quoteID) {
		int count = -100;
		try{
			String countQuery = "SELECT  * FROM " + TABLE_QuoteApplicant + " WHERE quoteID =" + quoteID;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);

			count = cursor.getCount();

			if(cursor != null){
				cursor.close();
			}		
		}
		catch(Exception e){
			count = -200;
		}
				
		return count;
	}
	
	public int getQuoteVehicleCount(String quoteID) {
		int count = -100;
		try{
			String countQuery = "SELECT  * FROM " + TABLE_QuoteVehicle + " WHERE quoteID =" + quoteID;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);

			count = cursor.getCount();

			if(cursor != null){
				cursor.close();
			}		
		}
		catch(Exception e){
			count = -200;
		}
				
		return count;
	}
		
	
	//-------------------------- Update Objects-----------------------------------------------
	public String updateQuoteApplicant(QuoteApplicant todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_Address, todo.address);
		values.put(KEY_City, todo.city);
		values.put(KEY_Completed, todo.completed);
		values.put(KEY_DateBirth, todo.dateBirth);
		values.put(KEY_Email, todo.email);
		values.put(KEY_FirstName, todo.firstName);
		values.put(KEY_LastName, todo.lastName);
		values.put(KEY_Middle, todo.middle);
		values.put(KEY_QuoteID, todo.quoteId);
		values.put(KEY_ResidencyType, todo.residencyType);
		values.put(KEY_SSN, todo.ssn);
		values.put(KEY_State, todo.state);
		values.put(KEY_Zip, todo.zip);		

		long _row_id = db.update(TABLE_QuoteApplicant, values, "quoteID='" + todo.quoteId + "'", null);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	
	public String updateQuoteCoverages(QuoteCoverages todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_BodilyInjury, todo.bodilyInjury);
		values.put(KEY_MedicalProvider, todo.medicalProvider);
		values.put(KEY_MiniTort, todo.miniTort);
		values.put(KEY_PersonalInjuryProtection, todo.personaInjuryProtection);
		values.put(KEY_PropertyDamage, todo.propertyDamage);
		values.put(KEY_PropertyProtection, todo.propertyProtection);
		values.put(KEY_UninsuredMotorist, todo.uninsuredMotorist);
		values.put(KEY_UnderInsuredMotorist, todo.underinsuredMotorist);

		long _row_id = db.update(TABLE_QuoteCoverages, values, "quoteID='" + todo.quoteID + "'", null);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	
	public String updateQuoteDriver(QuoteDriver todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_DateBirth, todo.dateBirth);
		values.put(KEY_Dependents, todo.dependents);
		values.put(KEY_DriverID, todo.driverID);
		values.put(KEY_FirstName, todo.firstName);
		values.put(KEY_Gender, todo.gender);
		values.put(KEY_IncomeLevel, todo.incomeLevel);
		values.put(KEY_LastName, todo.lastName);
		values.put(KEY_LicenseNum, todo.licenseNum);
		values.put(KEY_LicenseState, todo.licenseState);
		values.put(KEY_MaritalStatus, todo.maritalStatus);
		values.put(KEY_MiddleInitial, todo.middleInitial);
		values.put(KEY_Occupation, todo.occupation);
		values.put(KEY_RelationApplicant, todo.relationApplicant);		

		long _row_id = db.update(TABLE_QuoteDriver, values, "quoteID='" + todo.quoteID + "'", null);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	
	public String updateQuoteVehicle(QuoteVehicle todo) {
		//try{
		SQLiteDatabase db = this.getWritableDatabase();
		String row_id = "-100";

		//db.beginTransaction();
		try{
		ContentValues values = new ContentValues();
		values.put(KEY_AnnualMileage, todo.annualMileage);
		values.put(KEY_AntiLockBrakes, todo.antiLockBrakes);
		values.put(KEY_AntiTheftDevice, todo.antiTheftDevice);
		values.put(KEY_AssignedDriverID, todo.assignedDriverID);
		values.put(KEY_AssignedDriverName, todo.assignedDriverName);
		values.put(KEY_Carpool, todo.carpool);
		values.put(KEY_Completed, todo.completed);
		values.put(KEY_GaragingZipCode, todo.garagingZipCode);
		values.put(KEY_Make, todo.make);
		values.put(KEY_MilesToWork, todo.milesToWork);
		values.put(KEY_Model, todo.model);
		values.put(KEY_PassiveRestraints, todo.passiveRestraints);
		values.put(KEY_SplitCity, todo.splitCity);
		values.put(KEY_VehicleType, todo.vehicleType);
		values.put(KEY_VehicleTypeCode, todo.vehicleTypeCode);
		values.put(KEY_VehicleUsage, todo.vehicleUsage);
		values.put(KEY_VIN, todo.VIN);
		values.put(KEY_WorkWeek, todo.workWeek);
		values.put(KEY_Year, todo.year);		

		long _row_id = db.update(TABLE_QuoteVehicle, values, "id=" + todo.id, null);
		//db.setTransactionSuccessful();
		row_id = String.valueOf(_row_id);
		}
		catch(Exception e){
			row_id = e.getMessage();
		}
		finally {
			//db.endTransaction();
		//db.close();
		}
		return row_id;
	}
	

	//-------------------------- Delete Objects-----------------------------------------------
	
	public void deleteAllDropdownData() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DropdownData, KEY_ID + " <> ?", new String[] {"0"});
	}
	
		
	
	
	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
}
