package com.karthik.donors;

import java.util.ArrayList;

public class CharityEvent {
	
	private String recepient_ID;
	public String transaction_DT;
	public ArrayList<Float> contributions;
	public String getRecepient_ID() {
		return recepient_ID;
	}
	public void setRecepient_ID(String recepient_ID) {
		this.recepient_ID = recepient_ID;
	}
	public String getTransaction_DT() {
		return transaction_DT;
	}
	public void setTransaction_DT(String transaction_DT) {
		this.transaction_DT = transaction_DT;
	}
	public ArrayList<Float> getContributions() {
		return contributions;
	}
	public void setContributions(ArrayList<Float> contributions) {
		this.contributions = contributions;
	}
	
	public CharityEvent()
	{
		
	}
	public CharityEvent(String recepient_ID, String transaction_DT, ArrayList<Float> contributions) {
		super();
		this.recepient_ID = recepient_ID;
		this.transaction_DT = transaction_DT;
		this.contributions = contributions;
	}
	
	

}
