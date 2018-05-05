package com.tfg.entities;

public class Owner implements Account{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double amount;
	private static Owner instance;
	
	/**
	 * Constructor
	 * @param amount
	 */
	private Owner(double amount) {this.amount = amount;}
	
	/**
	 * Patrón Singleton, solamente puede existir una cuenta de este tipo
	 * @return La única instancia
	 */
	public static Owner getInstance() {
		if(instance == null) {
			instance = new Owner(1000);
		}
		return instance;
	}

	@Override
	public double getAmount() {
		return (double)Math.round(amount * 100d) / 100d;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = (double)Math.round(amount * 100d) / 100d;
	}

	@Override
	public String getAddress() {
		return "Owner";
	}
	
	public String getNickname() {
		return "Owner";
	}
	
	
}
