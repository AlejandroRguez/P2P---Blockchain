package com.tfg.entities;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Comparator;

import com.tfg.utils.Crypto;

public class User implements Account{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	String address;
	PrivateKey privateKey;
	PublicKey publicKey;
	double amount;
	public String nickname;

	/**
	 * Constructor
	 * Genera automáticamente las claves y la dirección del usuario
	 * @param amount
	 * @param nickname
	 */
	public User(double amount, String nickname) {
		KeyPair keyPair = Crypto.getKeyPair();
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();
		this.amount = amount;
		this.nickname = nickname;
		this.address = Crypto.getStringHash(this.publicKey.toString());
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
		return address;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Comparador de usuarios por importe en sus cuentas
	 * Actualmente no se usa, pero sería útil en caso de que se decidiese utilizar el algoritmo de prueba de participación
	 */
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
