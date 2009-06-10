package com.reverseFlat.ejb.commons.valueobjects;

import java.io.Serializable;


public class CreditCardServer implements Serializable {

	private String creditCardType;
	private String creditCardNumber;
	private String expdateMonth;
	private String expdateYear;
	private String cvv2Number;
	private String idCombo;
	private String currency;

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getExpdateMonth() {
		return expdateMonth;
	}

	public void setExpdateMonth(String expdateMonth) {
		this.expdateMonth = expdateMonth;
	}

	public String getExpdateYear() {
		return expdateYear;
	}

	public void setExpdateYear(String expdateYear) {
		this.expdateYear = expdateYear;
	}

	public String getCvv2Number() {
		return cvv2Number;
	}

	public void setCvv2Number(String cvv2Number) {
		this.cvv2Number = cvv2Number;
	}

	public String getIdCombo() {
		return idCombo;
	}

	public void setIdCombo(String idCombo) {
		this.idCombo = idCombo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
