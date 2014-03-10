package com.pioneer.sqlite.model;

public class Quotes {

	public int id;
	public int quoteID;
	public String quoteName;
	public String quoteStatus;
	public String quoteSubmitted;

	// constructors
	public Quotes() {

	}

	public Quotes(int _id, int _quoteID, String _quoteName, String _quoteStatus, String _quoteSubmitted) {
		this.id = _id;
		this.quoteID = _quoteID;
		this.quoteName = _quoteName;
		this.quoteStatus = _quoteStatus;
		this.quoteSubmitted = _quoteSubmitted;
	}

	
	
	public Quotes getQuote() {
		Quotes q = new Quotes();
		q.id = id;
		q.quoteID = quoteID;
		q.quoteName = quoteName;
		q.quoteStatus = quoteStatus;
		q.quoteSubmitted = quoteSubmitted;
		return q;
	}
}

