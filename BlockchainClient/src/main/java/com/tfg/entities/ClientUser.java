package com.tfg.entities;

public class ClientUser {

	
	double amount;
	String nickname;
	
	public ClientUser() {}

	public ClientUser(double amount, String nickname) {
		this.amount = amount;
		this.nickname = nickname;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;

	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
