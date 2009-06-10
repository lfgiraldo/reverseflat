package com.reverseFlat.common.valueobjects;

import java.io.Serializable;
import java.util.Date;

//import com.google.gwt.user.client.rpc.IsSerializable;

public class ChipsincomeVO implements Serializable
{
	private int userIduser;

	private Date date;

	private int chipsAmmount;

	private float moneyPaid;

	private String transactionNumber;

	private short chargeMethod;

	private long idIncome;

	public int getUserIduser()
	{
		return userIduser;
	}

	public void setUserIduser(int userIduser)
	{
		this.userIduser = userIduser;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public int getChipsAmmount()
	{
		return chipsAmmount;
	}

	public void setChipsAmmount(int chipsAmmount)
	{
		this.chipsAmmount = chipsAmmount;
	}

	public float getMoneyPaid()
	{
		return moneyPaid;
	}

	public void setMoneyPaid(float moneyPaid)
	{
		this.moneyPaid = moneyPaid;
	}

	public String getTransactionNumber()
	{
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber)
	{
		this.transactionNumber = transactionNumber;
	}

	public short getChargeMethod()
	{
		return chargeMethod;
	}

	public void setChargeMethod(short chargeMethod)
	{
		this.chargeMethod = chargeMethod;
	}

	public long getIdIncome()
	{
		return idIncome;
	}

	public void setIdIncome(long idIncome)
	{
		this.idIncome = idIncome;
	}
}
