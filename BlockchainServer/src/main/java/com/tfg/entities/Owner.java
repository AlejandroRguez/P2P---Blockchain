package com.tfg.entities;

public class Owner implements Account {
	

	private double amount;
	private static Owner instance;
	
	private Owner(double amount) {this.amount = amount;}
	
	public static Owner getInstance() {
		if(instance == null) {
			instance = new Owner(1000);
		}
		return instance;
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String getAddress() {
		return "Owner";
	}
	
	public String getNickname() {
		return "Owner";
	}
	
	
}
