package model;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;

import utils.LogManager;

public class User implements Account {

	String address;
	PrivateKey privateKey;
	PublicKey publicKey;
	double amount;
	MessageDigest m = getMessageDigest();
	String nickname;
	Blockchain blockchain = Blockchain.getInstance();

	public User(double amount, String nickname) {
		KeyPair keyPair = getKeyPair();
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();
		this.amount = amount;
		this.nickname = nickname;
		this.address = m.digest(this.publicKey.toString().getBytes(StandardCharsets.UTF_8)).toString();
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
	
	public String getNickName() {
		return nickname;
	}

	private MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			LogManager.write(Level.SEVERE, "Couldn´t get MessageDigest Instance");
			return null;
		}
	}

	private KeyPair getKeyPair() {
		try {
			return KeyPairGenerator.getInstance("RSA").generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			LogManager.write(Level.SEVERE, "Couldn´t get Key Pair Instance");
			return null;
		}
	}
	
	public void update (Blockchain blockchain) {
		this.blockchain = blockchain;
	}

}
