package com.tfg.entities;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Comparator;
import java.util.logging.Level;

import com.tfg.utils.Crypto;
import com.tfg.utils.LogManager;

public class User implements Account{

	String address;
	PrivateKey privateKey;
	PublicKey publicKey;
	double amount;
	String nickname;
	Blockchain blockchain = Blockchain.getInstance();

	public User(double amount, String nickname) {
		KeyPair keyPair = Crypto.getKeyPair();
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();
		this.amount = amount;
		this.nickname = nickname;
		this.address = Crypto.getMessageDigest().digest(this.publicKey.toString().getBytes(StandardCharsets.UTF_8)).toString();
		LogManager.write(Level.INFO, "Created account for " + nickname + " with address -> " + this.getAddress()
				+ " and amount -> " + this.getAmount());
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
		return address;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void updateInfo(Blockchain blockchain) {
		this.blockchain = blockchain;
	}
	
	public static Comparator<User> usersComparator = new Comparator<User>() {

		@Override
		public int compare(User u1, User u2) {
			double amount1 = u1.getAmount();
			double amount2 = u2.getAmount();
			Double result = amount1 - amount2; 
			return result.intValue();
		}
		
	};

}
