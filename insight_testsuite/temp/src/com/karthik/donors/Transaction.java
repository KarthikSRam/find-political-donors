package com.karthik.donors;

import java.util.Date;

public class Transaction {
	
	private String recepient_ID;
	private String zip_code;
	public String transaction_DT;
	public float transaction_AMT;
	public String other_ID;
	

	@Override
	public String toString() {
		return "Transaction [recepient_ID=" + recepient_ID + ", zip_code=" + zip_code + ", transaction_DT="
				+ transaction_DT + ", transaction_AMT=" + transaction_AMT + ", other_ID=" + other_ID + "]";
	}

	public Transaction(String cMTE_ID, String zIP_CODE, String transaction_DT, float transaction_AMT, String other_ID) {
		super();
		recepient_ID = cMTE_ID;
		zip_code = zIP_CODE;
		this.transaction_DT = transaction_DT;
		this.transaction_AMT = transaction_AMT;
		this.other_ID = other_ID;
	}
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public String getRecepient_ID() {
		return recepient_ID;
	}
	public void setRecepient_ID(String cMTE_ID) {
		recepient_ID = cMTE_ID;
	}
	public String getZIP_CODE() {
		return zip_code;
	}
	public void setZIP_CODE(String zIP_CODE) {
		zip_code = zIP_CODE;
	}
	public String getTransaction_DT() {
		return transaction_DT;
	}
	public void setTransaction_DT(String transaction_DT) {
		this.transaction_DT = transaction_DT;
	}
	public float getTransaction_AMT() {
		return transaction_AMT;
	}
	public void setTransaction_AMT(float transaction_AMT) {
		this.transaction_AMT = transaction_AMT;
	}
	public String getOther_ID() {
		return other_ID;
	}
	public void setOther_ID(String other_ID) {
		this.other_ID = other_ID;
	}
	

}
