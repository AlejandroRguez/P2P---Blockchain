package model;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;

import utils.Crypto;
import utils.LogManager;

public class User implements Account {

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
	
	public String getNickName() {
		return nickname;
	}
	
	public void updateInfo(Blockchain blockchain) {
		this.blockchain = blockchain;
	}

}
