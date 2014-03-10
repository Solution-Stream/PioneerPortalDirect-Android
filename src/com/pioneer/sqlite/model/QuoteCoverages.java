package com.pioneer.sqlite.model;

public class QuoteCoverages {

	public int id;
	public String quoteID;
	public String bodilyInjury;
	public String medicalProvider;
	public String miniTort;
	public String personaInjuryProtection;
	public String propertyDamage;
	public String propertyProtection;
	public String uninsuredMotorist;
	public String underinsuredMotorist;
	
	// constructors
	public QuoteCoverages() {

	}

	public QuoteCoverages(String _quoteID, String _bodilyInjury, String _medicalProvider, String _miniTort, String _personaInjuryProtection, String _propertyDamage, String _propertyProtection, String _uninsuredMotorist, String _underinsuredMotorist) {
		this.quoteID = _quoteID;
		this.bodilyInjury = _bodilyInjury;
		this.medicalProvider = _medicalProvider;
		this.miniTort = _miniTort;
		this.personaInjuryProtection = _personaInjuryProtection;
		this.propertyDamage = _propertyDamage;
		this.propertyProtection = _propertyProtection;
		this.uninsuredMotorist = _uninsuredMotorist;
		this.underinsuredMotorist = _underinsuredMotorist;
	}

	
	public QuoteCoverages getQuoteCoverages(){
		QuoteCoverages qa = new QuoteCoverages();
		qa.bodilyInjury = this.bodilyInjury;
		qa.medicalProvider = this.medicalProvider;
		qa.miniTort = this.miniTort;
		qa.personaInjuryProtection = this.personaInjuryProtection;
		qa.propertyDamage = this.propertyDamage;
		qa.propertyProtection = this.propertyProtection;
		qa.quoteID = this.quoteID;
		qa.uninsuredMotorist = this.uninsuredMotorist;
		qa.underinsuredMotorist = this.underinsuredMotorist;
		qa.id = this.id;
		
		return qa;
	}
}